package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class RhythmMutation extends GeneticMutation {

    static final int NUMBER_OF_MIDI_VALUES = 128;

    private final int maxExtensionValues;

    public RhythmMutation(Random randomGenerator, int maxExtensionValues) {
        super(randomGenerator);
        this.maxExtensionValues = maxExtensionValues;
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        if (randomGenerator.nextBoolean()) {
            increaseLargerRhythmicValuesAppearances(chromosome);
        } else {
            putRandomNewSound(chromosome);
        }
        return chromosome;
    }

    private void increaseLargerRhythmicValuesAppearances(Chromosome chromosome) {
        int numberOfExtensionValues = randomGenerator.nextInt(maxExtensionValues);
        int mutationIndex = randomGenerator.nextInt(chromosome.getSize() - numberOfExtensionValues);
        for (int i = mutationIndex; i < mutationIndex + numberOfExtensionValues; i++) {
            chromosome.getGene(i).setValue(Constants.TENUTO.value());
        }
    }

    private void putRandomNewSound(Chromosome chromosome) {
        int mutationIndex = randomGenerator.nextInt(chromosome.getSize());
        chromosome.getGene(mutationIndex).setValue(randomGenerator.nextInt(NUMBER_OF_MIDI_VALUES));
    }
}
