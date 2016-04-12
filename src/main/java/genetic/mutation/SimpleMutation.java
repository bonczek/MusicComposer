package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class SimpleMutation extends GeneticMutation {

    private final Random geneGenerator;

    public SimpleMutation(double mutationRate, Random random) {
        super(mutationRate);
        this.geneGenerator = random;
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        int geneIndex = geneGenerator.nextInt(chromosome.getSize());
        int geneValue = geneGenerator.nextInt(Constants.MAX_MIDI_VALUE.value() + 3) - 2;
        chromosome.getGene(geneIndex).setValue(geneValue);
    }
}
