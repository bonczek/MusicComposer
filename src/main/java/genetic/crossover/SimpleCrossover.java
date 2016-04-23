package genetic.crossover;

import genetic.representation.Chromosome;
import genetic.representation.ChromosomePair;
import genetic.representation.Gene;

import java.util.List;
import java.util.Random;

public class SimpleCrossover extends GeneticCrossover {

    public SimpleCrossover(Random numberGenerator) {
        super(numberGenerator);
    }

    @Override
    public ChromosomePair crossover(ChromosomePair chromosomePair) {
        return replaceChromosomesParts(chromosomePair.getFirst(), chromosomePair.getSecond());
    }

    private ChromosomePair replaceChromosomesParts(Chromosome first, Chromosome second) {
        int chromosomeLength = first.getSize();
        int crossoverIndex = randomGenerator.nextInt(chromosomeLength - 2) + 1;

        List<Gene> firstRightPart = first.getPart(crossoverIndex, chromosomeLength);
        List<Gene> secondRightPart = second.getPart(crossoverIndex, chromosomeLength);

        first.setPart(secondRightPart, crossoverIndex, chromosomeLength);
        second.setPart(firstRightPart, crossoverIndex, chromosomeLength);
        return new ChromosomePair(first, second);
    }


}
