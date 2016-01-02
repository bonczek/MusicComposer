package genetic;

import java.util.List;
import java.util.Map;

/**
 * Created by adam on 02.01.16.
 */
interface FitnessFunction {

    /**
     * Calculate fitness value for each chromosome.
     *
     * @param population given chromosomes
     * @param <T>        type of gen representation
     * @return
     */
    public <T extends Object> Map<Chromosome<T>, Integer> calculateFitness(List<Chromosome<T>> population);
}
