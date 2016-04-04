package genetic.initial;

import genetic.representation.Chromosome;

import java.util.List;

public abstract class InitialPopulationGenerator {

    public static final int NOTES_IN_MEASURE = 16;

    protected int populationSize;

    protected int numbersOfMeasures;

    public InitialPopulationGenerator(int populationSize, int numbersOfMeasures) {
        this.populationSize = populationSize;
        this.numbersOfMeasures = numbersOfMeasures;
    }

    /**
     * Create initial population to start genetic algorithm.
     *
     * @return initial population of chromosomes
     */
    public abstract List<Chromosome> generatePopulation();
}
