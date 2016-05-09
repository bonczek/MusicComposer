package genetic.mutation;

import genetic.mutation.musical.DissonantIntervalsMutation;
import genetic.mutation.musical.NoteLengthMutation;
import genetic.mutation.musical.RestDensityMutation;
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
        mutationList.add(new DissonantIntervalsMutation(randomGenerator));
        mutationList.add(new NoteLengthMutation(randomGenerator));
        mutationList.add(new RestDensityMutation(randomGenerator));
        mutationList.add(new ScaleMutation(randomGenerator, scale));
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        int mutationIndex = randomGenerator.nextInt(mutationList.size());
        return mutationList.get(mutationIndex).mutate(chromosome);
    }
}
