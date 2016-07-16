package genetic.mutation;

import genetic.initial.InitialPopulationGenerator;
import genetic.mutation.musical.NoteLengthMutation;
import genetic.mutation.musical.RestDensityMutation;
import genetic.mutation.musical.sound.IntervalMutation;
import genetic.mutation.musical.sound.TransposeMutation;
import genetic.representation.Chromosome;
import music.harmony.Harmony;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TowseyMutation extends GeneticMutation {

    private List<GeneticMutation> mutationList = new ArrayList<>();

    public TowseyMutation(Random randomGenerator, Harmony scale) {
        super(randomGenerator);
        mutationList.add(new IntervalMutation(randomGenerator));
        mutationList.add(new NoteLengthMutation(randomGenerator));
        mutationList.add(new RestDensityMutation(randomGenerator));
        mutationList.add(new TransposeMutation(randomGenerator));
        mutationList.add(new RhythmMutation(randomGenerator, InitialPopulationGenerator.NOTES_IN_MEASURE));
//        mutationList.add(new ScaleMutation(randomGenerator, scale));
//        mutationList.add(new OctaveMutation(randomGenerator));
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        int mutationIndex = randomGenerator.nextInt(mutationList.size());
        return mutationList.get(mutationIndex).mutate(chromosome);
    }
}
