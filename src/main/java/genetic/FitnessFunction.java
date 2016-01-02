package genetic;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
interface FitnessFunction {

    /**
     * Calculate fitness value for each chromosome.
     *
     * @param population given chromosomes
     * @param <T>        type of gen representation
     * @return list of fitness values corresponding to chromosome population
     */
    public <T extends Object> List<Integer> calculateFitness(List<Chromosome<T>> population);
}
