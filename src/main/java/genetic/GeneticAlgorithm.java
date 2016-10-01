package genetic;

import genetic.fitness.function.FitnessFunction;
import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.util.Converter;
import javafx.concurrent.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

public class GeneticAlgorithm extends Task<Chromosome> {

    private final int numberOfIterations;
    private InitialPopulationGenerator initialGenerator;
    private NewPopulationGenerator populationGenerator;
    private FitnessFunction fitnessFunction;
    private String fitnessFunctionReport;
    private Map<Integer, Integer> iterationsMaxResults = new HashMap<>();

    public GeneticAlgorithm(InitialPopulationGenerator initialGenerator, NewPopulationGenerator populationGenerator,
                            FitnessFunction fitnessFunction, int numberOfIterations) {
        this.initialGenerator = initialGenerator;
        this.populationGenerator = populationGenerator;
        this.fitnessFunction = fitnessFunction;
        this.numberOfIterations = numberOfIterations;
    }

    public Map<Integer, Integer> getIterationsMaxResults() {
        return iterationsMaxResults;
    }

    @Override
    protected Chromosome call() throws Exception {
        List<Chromosome> population = initialGenerator.generatePopulation();

        int iteration = 0;
        while (nextPopulation(iteration)) {
            fitnessFunction.calculateFitness(population);
            OptionalInt max = population.stream().mapToInt(c -> c.getFitness().getFitnessValue()).max();
            if (max.isPresent()) {
                iterationsMaxResults.put(iteration, max.getAsInt());
            }
            population = populationGenerator.generateNewPopulation(population);
            iteration++;
            this.updateProgress(iteration, numberOfIterations);
        }

        fitnessFunction.calculateFitness(population);
//        population.forEach(c -> System.out.println(String.format("%s: %d", c.toString(), c.getFitness().getFitnessValue())));
//        humanReadable(population);
        Optional<Chromosome> theBestChromosome = population.stream().max((a, b) -> a.getFitness().getFitnessValue().compareTo(b
                .getFitness().getFitnessValue()));
        if (theBestChromosome.isPresent()) {
            fitnessFunctionReport = fitnessFunction.createFitnessReport(theBestChromosome.get());
            return theBestChromosome.get();
        } else {
            throw new RuntimeException("Failed to find composition with max value");
        }
    }

    public String getFitnessFunctionReport() {
        return fitnessFunctionReport;
    }

    private boolean nextPopulation(int iteration) {
        return iteration <= numberOfIterations;
    }

    private void humanReadable(List<Chromosome> population) {
        for (Chromosome chromosome : population) {
            String formatted = Converter.humanReadable(chromosome);
            System.out.println(String.format("%s: %d", formatted, chromosome.getFitness().getFitnessValue()));
        }
    }


}
