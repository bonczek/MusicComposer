package genetic.crossover;

import genetic.representation.Chromosome;
import genetic.representation.Gene;

import java.util.List;
import java.util.Random;

/**
 * Created by adam on 09.01.16.
 */
public class SimpleCrossover extends GeneticCrossover {

    private final Random numberGenerator;

    public SimpleCrossover(double crossoverRate, Random numberGenerator) {
        super(crossoverRate);
        this.numberGenerator = numberGenerator;
    }

    @Override
    public void crossOverChromosomes(List<Chromosome> chromosomes) {
        if (!chromosomes.isEmpty() && chromosomes.size() % 2 == 0) {
            for (int i = 0; i < chromosomes.size() / 2; i++) {
                replaceChromosomesParts(chromosomes.get(2 * i), chromosomes.get(2 * i + 1));
            }
        } else {
            System.out.println("Wrong size or empty");
        }
    }

    private void replaceChromosomesParts(Chromosome first, Chromosome second) {
        int chromosomeLength = first.getSize();
        int crossoverIndex = numberGenerator.nextInt(chromosomeLength - 2) + 1;

        List<Gene> firstRightPart = first.getPart(crossoverIndex, chromosomeLength);
        List<Gene> secondRightPart = second.getPart(crossoverIndex, chromosomeLength);

        first.setPart(secondRightPart, crossoverIndex, chromosomeLength);
        second.setPart(firstRightPart, crossoverIndex, chromosomeLength);
    }
}
