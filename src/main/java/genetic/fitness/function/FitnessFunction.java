package genetic.fitness.function;

import genetic.fitness.Fitness;
import genetic.representation.Chromosome;

import java.util.List;

public abstract class FitnessFunction {

    /**
     * Calculate fitness value for each chromosome.
     *
     * @param population given chromosomes
     */
    public void calculateFitness(List<Chromosome> population) {
        population.stream().forEach(c -> c.setFitness(rateChromosome(c)));
    }

    public abstract Fitness rateChromosome(Chromosome chromosome);

    public abstract String createFitnessReport(Chromosome chromosome);
}
