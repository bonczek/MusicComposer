package genetic.crossover;

import java.util.Random;

public abstract class GeneticCrossover implements Crossover {

    protected Random randomGenerator;

    public GeneticCrossover(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
}
