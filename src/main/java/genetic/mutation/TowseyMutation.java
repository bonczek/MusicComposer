package genetic.mutation;

import genetic.mutation.musical.ScaleMutation;
import genetic.representation.Chromosome;
import music.Harmony;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TowseyMutation extends GeneticMutation {

    private List<GeneticMutation> mutationList = new ArrayList<>();

    public TowseyMutation(Random randomGenerator, Harmony scale) {
        super(randomGenerator);
        mutationList.add(new IntervalMutation(randomGenerator));
        mutationList.add(new RhythmMutation(randomGenerator));
        mutationList.add(new ScaleMutation(randomGenerator, scale));
        //mutationList.add(new PitchRangeMutation(mutationRate, randomGenerator));
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        int mutationIndex = randomGenerator.nextInt(mutationList.size());
        return mutationList.get(mutationIndex).mutate(chromosome);
    }
}
