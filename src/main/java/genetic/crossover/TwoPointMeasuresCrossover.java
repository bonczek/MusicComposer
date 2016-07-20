package genetic.crossover;

import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.representation.ChromosomePair;
import genetic.representation.Gene;

import java.util.List;
import java.util.Random;

public class TwoPointMeasuresCrossover extends GeneticCrossover {

    private static final int MINIMUM_CHROMOSOME_LENGTH = 3 * InitialPopulationGenerator.NOTES_IN_MEASURE;

    private static final int MEASURE_SIZE = InitialPopulationGenerator.NOTES_IN_MEASURE;

    public TwoPointMeasuresCrossover(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    public ChromosomePair crossover(ChromosomePair chromosomePair) throws IllegalArgumentException {
        if (chromosomePair.getFirst().getSize() != chromosomePair.getSecond().getSize()) {
            throw new IllegalArgumentException("Different size of chromosomes to crossover");
        } else {
            return replaceChromosomeParts(chromosomePair.getFirst(), chromosomePair.getSecond());
        }
    }

    private ChromosomePair replaceChromosomeParts(Chromosome first, Chromosome second) throws IllegalArgumentException {
        if (first.getSize() < MINIMUM_CHROMOSOME_LENGTH || second.getSize() < MINIMUM_CHROMOSOME_LENGTH) {
            throw new IllegalArgumentException("Chromosomes are too short for this type of crossover");
        } else {
            int availableCrossoverPoints = (first.getSize() / MEASURE_SIZE) - 3;
            int crossoverIndex = (randomGenerator.nextInt(availableCrossoverPoints) + 1) * MEASURE_SIZE;
            int lastMeasureStartIndex = first.getSize() - MEASURE_SIZE;

            List<Gene> firstRightPart = first.getPart(crossoverIndex, lastMeasureStartIndex);
            List<Gene> secondRightPart = second.getPart(crossoverIndex, lastMeasureStartIndex);

            first.setPart(secondRightPart, crossoverIndex, lastMeasureStartIndex);
            second.setPart(firstRightPart, crossoverIndex, lastMeasureStartIndex);
            return new ChromosomePair(first, second);
        }
    }
}
