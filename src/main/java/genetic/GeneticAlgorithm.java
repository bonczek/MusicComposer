package genetic;

import genetic.fitness.function.FitnessFunction;
import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.util.Converter;

import java.util.List;
import java.util.Optional;

public class GeneticAlgorithm {

    private final int numberOfIterations;
    private InitialPopulationGenerator initialGenerator;
    private NewPopulationGenerator populationGenerator;
    private FitnessFunction fitnessFunction;
    private String fitnessFunctionReport;

    public GeneticAlgorithm(InitialPopulationGenerator initialGenerator, NewPopulationGenerator populationGenerator,
                            FitnessFunction fitnessFunction, int numberOfIterations) {
        this.initialGenerator = initialGenerator;
        this.populationGenerator = populationGenerator;
        this.fitnessFunction = fitnessFunction;
        this.numberOfIterations = numberOfIterations;
    }

    public String getFitnessFunctionReport() {
        return fitnessFunctionReport;
    }

    public Chromosome run() throws RuntimeException {
        List<Chromosome> population = initialGenerator.generatePopulation();

        int iteration = 0;
        while (nextPopulation(iteration)) {
            fitnessFunction.calculateFitness(population);
            population = populationGenerator.generateNewPopulation(population);
            iteration++;
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
