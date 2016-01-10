package genetic.crossover;

import genetic.representation.Chromosome;
import genetic.representation.Gene;

import java.util.List;

/**
 * Created by adam on 09.01.16.
 */
public class SimpleCrossover extends GeneticCrossover {

    public SimpleCrossover(double crossoverRate) {
        super(crossoverRate);
    }

    @Override
    public void crossOverChromosomes(List<Chromosome> chromosomes) {
        if (!chromosomes.isEmpty() && chromosomes.size() == 2) {
            replaceHalfChromosomes(chromosomes.get(0), chromosomes.get(1));
        } else {
            System.out.println("Wrong size or empty");
        }
    }

    private void replaceHalfChromosomes(Chromosome first, Chromosome second) {
        int chromosomeLength = first.getSize();
        int half = chromosomeLength / 2;

        List<Gene> firstRightHalf = first.getPart(half, chromosomeLength);
        List<Gene> secondRightHalf = second.getPart(half, chromosomeLength);

        first.setPart(secondRightHalf, half, chromosomeLength);
        second.setPart(firstRightHalf, half, chromosomeLength);
    }
}
