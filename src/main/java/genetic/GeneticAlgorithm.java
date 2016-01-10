package genetic;

import genetic.initial.InitialPopulationGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public class GeneticAlgorithm {

    private InitialPopulationGenerator initialGenerator;

    private NewPopulationGenerator populationGenerator;

    private FitnessFunction fitnessFunction;

    public GeneticAlgorithm(InitialPopulationGenerator initialGenerator, NewPopulationGenerator populationGenerator, FitnessFunction fitnessFunction) {
        this.initialGenerator = initialGenerator;
        this.populationGenerator = populationGenerator;
        this.fitnessFunction = fitnessFunction;
    }

    public void run() {
        //initial population
        List<Chromosome> population = initialGenerator.generatePopulation(16, 32, new ArrayList<>());

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
