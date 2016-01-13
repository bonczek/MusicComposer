package genetic.initial;

import genetic.representation.Chromosome;
import genetic.representation.Gene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adam on 10.01.16.
 */
public class RandomPopulationGenerator implements InitialPopulationGenerator {

    private final Random numberGenerator;

    private final List<Integer> availableValues;

    public RandomPopulationGenerator(Random numberGenerator, List<Integer> availableValues) {
        this.numberGenerator = numberGenerator;
        this.availableValues = availableValues;
    }

    @Override
    public List<Chromosome> generatePopulation(int size, int chromosomeLength) {
        List<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            population.add(generateChromosome(chromosomeLength));
        }
        return population;
    }

    private Chromosome generateChromosome(int chromosomeLength) {
        List<Gene> genes = new ArrayList<>();
        for (int j = 0; j < chromosomeLength; j++) {
            int randomIndex = numberGenerator.nextInt(availableValues.size());
            genes.add(new Gene(availableValues.get(randomIndex).shortValue()));
        }
        return new Chromosome(genes);
    }
}
