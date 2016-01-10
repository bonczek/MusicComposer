package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Gene;

import java.util.List;
import java.util.Random;

/**
 * Created by adam on 09.01.16.
 */
public class SimpleMutation extends GeneticMutation {

    private final Random randomIndex;
    private final List<Integer> availableValues;

    public SimpleMutation(double mutationRate, Random random, List<Integer> availableValues) {
        super(mutationRate);
        this.randomIndex = random;
        this.availableValues = availableValues;
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        int geneIndex = randomIndex.nextInt(chromosome.getSize());
        int valueIndex = randomIndex.nextInt(availableValues.size());
        chromosome.setGene(geneIndex, new Gene(availableValues.get(valueIndex)));
    }
}
