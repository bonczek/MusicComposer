package genetic;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public interface InitialPopulationGenerator {

    /**
     * Create initial population to start genetic algorithm.
     *
     * @return initial population of chromosomes
     */
    <T extends Object> List<Chromosome<T>> generatePopulation(int size);
}
