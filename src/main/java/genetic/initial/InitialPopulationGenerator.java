package genetic.initial;

import genetic.representation.Chromosome;

import java.util.List;

public interface InitialPopulationGenerator {

    int NOTES_IN_MEASURE = 16;

    /**
     * Create initial population to start genetic algorithm.
     *
     * @return initial population of chromosomes
     */
    List<Chromosome> generatePopulation(int size, int chromosomeLength);
}
