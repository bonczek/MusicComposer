package genetic.mutation;

import genetic.GeneticGuard;
import genetic.representation.Chromosome;

import java.util.List;
import java.util.Random;

/**
 * Created by adam on 02.01.16.
 */
public abstract class GeneticMutation {

    private final GeneticGuard guard;

    protected Random randomGenerator;

    public GeneticMutation(double mutationRate, Random randomGenerator) {
        guard = new GeneticGuard(mutationRate);
        this.randomGenerator = randomGenerator;
    }

    /**
     * Apply mutation operation with probability specified in a constructor.
     *
     * @param chromosomes mutation candidates
     */
    public void mutate(List<Chromosome> chromosomes) {
        for (Chromosome chromosome : chromosomes) {
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
    protected abstract void mutateChromosome(Chromosome chromosome);

}
