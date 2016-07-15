package genetic.representation;

import genetic.crossover.Crossover;
import genetic.fitness.type.SimpleReward;

public class ChromosomePair {

    private Chromosome first;
    private Chromosome second;

    public ChromosomePair(Chromosome first, Chromosome second) {
        this.first = first;
        this.second = second;
    }

    public Chromosome getFirst() {
        return first;
    }

    public Chromosome getSecond() {
        return second;
    }

    public ChromosomePair crossover(Crossover crossoverFunction) {
        first.setFitness(new SimpleReward());
        second.setFitness(new SimpleReward());
        return crossoverFunction.crossover(this);
    }
}
