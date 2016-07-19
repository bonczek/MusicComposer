package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class RhythmMutation extends GeneticMutation {

    static final int NUMBER_OF_MIDI_VALUES = 128;

    static final int MAX_NEW_SOUNDS = 8;

    private final int wholeNoteGenesLength;

    //@todo finish description and naming
    public RhythmMutation(Random randomGenerator, int wholeNoteGenesLength) {
        super(randomGenerator);
        this.wholeNoteGenesLength = wholeNoteGenesLength / 4;
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        if (randomGenerator.nextBoolean()) {
            increaseLargerRhythmicValuesAppearances(chromosome);
        } else {
            putRandomNewSounds(chromosome);
        }
        return chromosome;
    }

    private void increaseLargerRhythmicValuesAppearances(Chromosome chromosome) {
        int numberOfExtensionValues = randomGenerator.nextInt(wholeNoteGenesLength);
        int mutationIndex = randomGenerator.nextInt(chromosome.getSize() - numberOfExtensionValues);
        for (int i = mutationIndex; i < mutationIndex + numberOfExtensionValues; i++) {
            chromosome.getGene(i).setValue(Constants.TENUTO.value());
        }
    }

    private void putRandomNewSounds(Chromosome chromosome) {
        int numberOfSounds = randomGenerator.nextInt(MAX_NEW_SOUNDS);
        for (int i = 0; i < numberOfSounds; i++) {
            int mutationIndex = randomGenerator.nextInt(chromosome.getSize());
            chromosome.getGene(mutationIndex).setValue(randomGenerator.nextInt(NUMBER_OF_MIDI_VALUES));
        }
    }
}
