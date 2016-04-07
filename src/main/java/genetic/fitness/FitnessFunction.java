package genetic.fitness;

import genetic.representation.Chromosome;

import java.util.List;

public interface FitnessFunction {

    /**
     * Calculate fitness value for each chromosome.
     *
     * @param population given chromosomes
     */
    public void calculateFitness(List<Chromosome> population);

    public Integer rateChromosome(Chromosome chromosome);
}
