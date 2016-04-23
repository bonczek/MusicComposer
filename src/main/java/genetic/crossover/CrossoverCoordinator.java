package genetic.crossover;

import genetic.GeneticGuard;
import genetic.representation.ChromosomePair;

public class CrossoverCoordinator {

    private final GeneticGuard guard;

    private Crossover crossoverFunction;

    public CrossoverCoordinator(GeneticGuard geneticGuard, Crossover crossoverFunction) {
        this.guard = geneticGuard;
        this.crossoverFunction = crossoverFunction;
    }

    public ChromosomePair crossoverWithProbability(ChromosomePair chromosomePair) {
        if (guard.permitOperation()) {
            return chromosomePair.crossover(crossoverFunction);
        } else {
            return chromosomePair;
        }
    }
}
