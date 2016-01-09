package genetic.mutation;

import genetic.Chromosome;

import java.util.List;
import java.util.Random;

/**
 * Created by adam on 09.01.16.
 */
public class SimpleMutation<T extends Object> extends GeneticMutation<T> {

    private final Random randomIndex;
    private final List<T> availableValues;

    public SimpleMutation(double mutationRate, Random random, List<T> availableValues) {
        super(mutationRate);
        this.randomIndex = random;
        this.availableValues = availableValues;
    }

    @Override
    protected void mutateChromosome(Chromosome<T> chromosome) {
        int geneIndex = randomIndex.nextInt(chromosome.getSize());
        int valueIndex = randomIndex.nextInt(availableValues.size());
        chromosome.setGene(geneIndex, availableValues.get(valueIndex));
    }
}
