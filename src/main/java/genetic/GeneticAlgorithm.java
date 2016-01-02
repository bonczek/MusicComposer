package genetic;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public class GeneticAlgorithm {

    private final InitialPopulationGenerator initialGenerator;

    private final NewPopulationGenerator populationGenerator;

    private final FitnessFunction fitnessFunction;

    public GeneticAlgorithm(InitialPopulationGenerator initialGenerator, NewPopulationGenerator populationGenerator, FitnessFunction fitnessFunction) {
        this.initialGenerator = initialGenerator;
        this.populationGenerator = populationGenerator;
        this.fitnessFunction = fitnessFunction;
    }

    public <T extends Object> void run() {
        //initial population
        List<Chromosome<T>> population = initialGenerator.generatePopulation(16);

        int iteration = 0;
        while (nextPopulation(iteration)) {
            List<Integer> fitnessValues = fitnessFunction.calculateFitness(population);
            population = populationGenerator.generateNewPopulation(population, fitnessValues);
            iteration++;
        }
    }

    private boolean nextPopulation(int iteration) {
        return iteration <= 10;
    }
}
