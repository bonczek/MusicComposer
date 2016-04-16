package genetic.mutation;

import genetic.fitness.towsey.TowseyMusicalFitness;
import genetic.representation.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TowseyMutation extends GeneticMutation {

    private List<GeneticMutation> mutationList = new ArrayList<>();

    public TowseyMutation(double mutationRate, Random randomGenerator) {
        super(mutationRate, randomGenerator);
        mutationList.add(new IntervalMutation(mutationRate, randomGenerator));
        mutationList.add(new RhytmMutation(mutationRate, randomGenerator));
//        mutationList.add(new PitchRangeMutation(mutationRate, randomGenerator));
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        TowseyMusicalFitness fitness = new TowseyMusicalFitness();
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(chromosome);
        fitness.calculateFitness(chromosomes);

        int mutationIndex = randomGenerator.nextInt(mutationList.size());
        mutationList.get(mutationIndex).mutateChromosome(chromosome);

    }
}
