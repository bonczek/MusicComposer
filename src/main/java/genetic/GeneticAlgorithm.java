package genetic;

import genetic.fitness.FitnessFunction;
import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.representation.Note;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Chromosome> population = initialGenerator.generatePopulation(32, 320);

        int iteration = 0;
        while (nextPopulation(iteration)) {
            fitnessFunction.calculateFitness(population);
            population = populationGenerator.generateNewPopulation(population);
            iteration++;
        }

        population.forEach(c -> System.out.println(String.format("%s: %d",
                c.toString(), fitnessFunction.rateChromosome(c))));
        humanReadable(population);
    }

    private boolean nextPopulation(int iteration) {
        return iteration <= 3;
    }

    private void humanReadable(List<Chromosome> population) {
        Map<Integer, Note> notesMap = new HashMap<>();
        for (Note n : Note.values()) {
            notesMap.put(n.value(), n);
        }

        for (Chromosome chromosome : population) {
            String formatted = chromosome.getGenesValues().stream().map(val -> notesMap.get(val).name()).collect(Collectors.joining("|"));
            System.out.println(String.format("%s: %d", formatted, fitnessFunction.rateChromosome(chromosome)));
        }
    }
}
