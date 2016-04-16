package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class PitchRangeMutation extends GeneticMutation {


    public PitchRangeMutation(double mutationRate, Random randomGenerator) {
        super(mutationRate, randomGenerator);
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        int lowestPitch = Integer.MAX_VALUE;
        int highestPitch = Integer.MIN_VALUE;
        int lowestPitchIndex = Integer.MIN_VALUE;
        int highestPitchIndex = Integer.MAX_VALUE;
        for (int i = 0; i < chromosome.getSize(); i++) {
            if (chromosome.getGene(i).getValue() < lowestPitch) {
                lowestPitch = chromosome.getGene(i).getValue();
                lowestPitchIndex = i;
            }
            if (chromosome.getGene(i).getValue() > highestPitch) {
                highestPitch = chromosome.getGene(i).getValue();
                highestPitchIndex = i;
            }
        }
        if (lowestPitchIndex >= 0 && highestPitchIndex < chromosome.getSize()) {
            if (randomGenerator.nextBoolean()) {
                if (highestPitch > 12) {
                    chromosome.getGene(highestPitchIndex).setValue(highestPitch - 12);
                }
            } else {
                if (lowestPitch < Constants.MAX_MIDI_VALUE.value() - 12) {
                    chromosome.getGene(lowestPitchIndex).setValue(lowestPitch + 12);
                }
            }
        }
    }
}
