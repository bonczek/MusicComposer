package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class PitchRangeMutation extends GeneticMutation {


    public PitchRangeMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        Chromosome mutatingChromosome = new Chromosome(chromosome.getPart(0, chromosome.getSize()));
        int lowestPitch = Integer.MAX_VALUE;
        int highestPitch = Integer.MIN_VALUE;
        int lowestPitchIndex = Integer.MIN_VALUE;
        int highestPitchIndex = Integer.MAX_VALUE;
        for (int i = 0; i < mutatingChromosome.getSize(); i++) {
            if (mutatingChromosome.getGene(i).getValue() < lowestPitch) {
                lowestPitch = mutatingChromosome.getGene(i).getValue();
                lowestPitchIndex = i;
            }
            if (mutatingChromosome.getGene(i).getValue() > highestPitch) {
                highestPitch = mutatingChromosome.getGene(i).getValue();
                highestPitchIndex = i;
            }
        }
        if (lowestPitchIndex >= 0 && highestPitchIndex < mutatingChromosome.getSize()) {
            if (randomGenerator.nextBoolean()) {
                if (highestPitch > 12) {
                    mutatingChromosome.getGene(highestPitchIndex).setValue(highestPitch - 12);
                }
            } else {
                if (lowestPitch < Constants.MAX_MIDI_VALUE.value() - 12) {
                    mutatingChromosome.getGene(lowestPitchIndex).setValue(lowestPitch + 12);
                }
            }
        }
        return mutatingChromosome;
    }
}
