package genetic.crossover;

import genetic.representation.ChromosomePair;

public interface Crossover {
    ChromosomePair crossover(ChromosomePair chromosomePair);
}
