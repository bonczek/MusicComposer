package genetic.mutation;

import genetic.Chromosome;
import genetic.GeneticGuard;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public abstract class GeneticMutation<T extends Object> {

    private final GeneticGuard guard;

    public GeneticMutation(double mutationRate) {
        guard = new GeneticGuard(mutationRate);
    }

    /**
     * Apply mutation operation with probability specified in a constructor.
     *
     * @param chromosomes mutation candidates
     */
    public void mutate(List<Chromosome<T>> chromosomes) {
        for (Chromosome<T> chromosome : chromosomes) {
            if (guard.permitOperation()) {
                mutateChromosome(chromosome);
            }
        }
    }

    /**
     * Mutate chromosome.
     *
     * @param chromosome single chromosome to mutate
     */
    protected abstract void mutateChromosome(Chromosome<T> chromosome);

}
