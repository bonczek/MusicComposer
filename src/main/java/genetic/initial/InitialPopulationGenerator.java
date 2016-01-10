package genetic.initial;

import genetic.Chromosome;

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
    List<Chromosome> generatePopulation(int size, int chromosomeLength, List<Integer> availableValues);
}
