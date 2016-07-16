package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class RhythmMutation extends GeneticMutation {

    private final int wholeNoteGenesLength;

    public RhythmMutation(Random randomGenerator, int wholeNoteGenesLength) {
        super(randomGenerator);
        this.wholeNoteGenesLength = wholeNoteGenesLength;
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        int numberOfExtensionValues = randomGenerator.nextInt(wholeNoteGenesLength);
        int mutationIndex = randomGenerator.nextInt(chromosome.getSize() - numberOfExtensionValues);
        for (int i = mutationIndex; i < mutationIndex + numberOfExtensionValues; i++) {
            chromosome.getGene(i).setValue(Constants.TENUTO.value());
        }
        return chromosome;
    }
}
