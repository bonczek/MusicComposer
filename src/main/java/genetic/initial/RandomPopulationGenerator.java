package genetic.initial;

import genetic.representation.Chromosome;
import genetic.representation.Gene;
import genetic.representation.GeneConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPopulationGenerator extends InitialPopulationGenerator {

    private final Random numberGenerator;

    //@todo too many parameters
    public RandomPopulationGenerator(int populationSize, int numbersOfMeasures, Random numberGenerator) {
        super(populationSize, numbersOfMeasures);
        this.numberGenerator = numberGenerator;
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
            int randomGeneValue = numberGenerator.nextInt(GeneConstants.MAX_MIDI_VALUE.value() + 3) - 2;
            genes.add(new Gene(randomGeneValue));
        }
        return new Chromosome(genes);
    }
}
