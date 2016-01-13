package genetic.fitness;

import genetic.representation.Chromosome;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public interface FitnessFunction {

    /**
     * Calculate fitness value for each chromosome.
     *
     * @param population given chromosomes
     */
    public void calculateFitness(List<Chromosome> population);

    public Integer rateChromosome(Chromosome chromosome);
}
