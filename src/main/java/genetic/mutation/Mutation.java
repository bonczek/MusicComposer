package genetic.mutation;

import genetic.representation.Chromosome;

public interface Mutation {

    Chromosome mutate(Chromosome chromosome);
}
