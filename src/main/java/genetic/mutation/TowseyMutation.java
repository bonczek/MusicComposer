package genetic.mutation;

import genetic.representation.Chromosome;
import music.Harmony;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TowseyMutation extends GeneticMutation {

    private List<GeneticMutation> mutationList = new ArrayList<>();

    public TowseyMutation(double mutationRate, Random randomGenerator, Harmony scale) {
        super(mutationRate, randomGenerator);
        mutationList.add(new IntervalMutation(mutationRate, randomGenerator));
        mutationList.add(new RhytmMutation(mutationRate, randomGenerator));
        mutationList.add(new ScaleMutation(mutationRate, randomGenerator, scale));
//        mutationList.add(new PitchRangeMutation(mutationRate, randomGenerator));
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        int mutationIndex = randomGenerator.nextInt(mutationList.size());
        mutationList.get(mutationIndex).mutateChromosome(chromosome);
    }
}
