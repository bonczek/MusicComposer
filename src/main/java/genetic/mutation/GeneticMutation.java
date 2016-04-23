package genetic.mutation;

import java.util.Random;

public abstract class GeneticMutation implements Mutation {

    protected Random randomGenerator;

    public GeneticMutation(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

}
