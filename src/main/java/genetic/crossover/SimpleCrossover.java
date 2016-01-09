package genetic.crossover;

import genetic.Chromosome;

import java.util.List;

/**
 * Created by adam on 09.01.16.
 */
public class SimpleCrossover<T extends Object> extends GeneticCrossover<T> {

    public SimpleCrossover(double crossoverRate) {
        super(crossoverRate);
    }

    @Override
    public void crossOverChromosomes(List<Chromosome<T>> chromosomes) {
        if (!chromosomes.isEmpty() && chromosomes.size() == 2) {
            replaceHalfChromosomes(chromosomes.get(0), chromosomes.get(1));
        } else {
            System.out.println("Wrong size or empty");
        }
    }

    private void replaceHalfChromosomes(Chromosome<T> first, Chromosome<T> second) {
        int chromosomeLength = first.getSize();
        int half = chromosomeLength / 2;

        List<T> firstRightHalf = first.getPart(half, chromosomeLength);
        List<T> secondRightHalf = second.getPart(half, chromosomeLength);

        first.setPart(secondRightHalf, half, chromosomeLength);
        second.setPart(firstRightHalf, half, chromosomeLength);
    }
}
