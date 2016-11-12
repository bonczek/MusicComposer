package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Gene;
import genetic.representation.GeneConstants;

import java.util.Random;

public class SimpleMutation extends GeneticMutation {


    public SimpleMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        int geneIndex = randomGenerator.nextInt(chromosome.getSize());
        int geneValue = randomGenerator.nextInt(GeneConstants.MAX_MIDI_VALUE.value() + 3) - 2;
        chromosome.setGene(geneIndex, new Gene(geneValue));
        return chromosome;
    }
}
