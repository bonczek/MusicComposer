package genetic.crossover;

import genetic.Chromosome;
import genetic.GeneticGuard;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public abstract class GeneticCrossover<T extends Object> {

    private final GeneticGuard guard;

    public GeneticCrossover(double crossoverRate) {
        guard = new GeneticGuard(crossoverRate);
    }

    /**
     * Apply cross over operation with probability specified in a constructor.
     *
     * @param chromosomes cross over candidates
     */
    public void crossOver(List<Chromosome<T>> chromosomes) {
        if (guard.permitOperation()) {
            crossOverChromosomes(chromosomes);
        }
    }

    /**
     * Cross over given chromosomes.
     *
     * @param chromosomes pair of chromosomes to cross over
     */
    protected abstract void crossOverChromosomes(List<Chromosome<T>> chromosomes);
}
