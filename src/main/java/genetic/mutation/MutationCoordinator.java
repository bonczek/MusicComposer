package genetic.mutation;

import genetic.GeneticGuard;
import genetic.representation.Chromosome;

public class MutationCoordinator {

    private final GeneticGuard guard;

    private Mutation mutation;

    public MutationCoordinator(GeneticGuard geneticGuard, Mutation mutation) {
        this.guard = geneticGuard;
        this.mutation = mutation;
    }

    public Chromosome mutateWithProbability(Chromosome chromosome) {
        if (guard.permitOperation()) {
            return chromosome.mutate(mutation);
        } else {
            return chromosome;
        }
    }
}
