package genetic.initial;

import genetic.representation.Chromosome;
import genetic.representation.Gene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPopulationGenerator extends InitialPopulationGenerator {

    private final Random numberGenerator;

    //@todo do I need them?
    private final List<Integer> availableValues;

    //@todo too many parameters
    public RandomPopulationGenerator(int populationSize, int numbersOfMeasures, Random numberGenerator, List<Integer>
            availableValues) {
        super(populationSize, numbersOfMeasures);
        this.numberGenerator = numberGenerator;
        this.availableValues = availableValues;
    }

    @Override
    public List<Chromosome> generatePopulation() {
        List<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(generateChromosome(numbersOfMeasures * InitialPopulationGenerator.NOTES_IN_MEASURE));
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
