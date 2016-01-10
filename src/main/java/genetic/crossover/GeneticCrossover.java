package genetic.crossover;

import genetic.GeneticGuard;
import genetic.representation.Chromosome;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public abstract class GeneticCrossover {

    private final GeneticGuard guard;

    public GeneticCrossover(double crossoverRate) {
        guard = new GeneticGuard(crossoverRate);
    }

    /**
     * Apply cross over operation with probability specified in a constructor.
     *
     * @param chromosomes cross over candidates
     */
    public void crossOver(List<Chromosome> chromosomes) {
        if (guard.permitOperation()) {
            crossOverChromosomes(chromosomes);
        }
    }

    /**
     * Cross over given chromosomes.
     *
     * @param chromosomes pair of chromosomes to cross over
     */
    protected abstract void crossOverChromosomes(List<Chromosome> chromosomes);
}
