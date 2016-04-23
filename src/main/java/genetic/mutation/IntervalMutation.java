package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;
import music.notes.pitch.PitchInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class IntervalMutation extends GeneticMutation {

    private final List<PitchInterval> intervals = new ArrayList<>(Arrays.asList(PitchInterval.values()));

    public IntervalMutation(double mutationRate, Random indexChooser) {
        super(mutationRate, indexChooser);
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        int geneIndex = randomGenerator.nextInt(chromosome.getSize());
        int nextGenIndex = geneIndex + 1;
        while (geneIndex < chromosome.getSize() - 1 && nextGenIndex < chromosome.getSize()) {
            int firstGeneValue = chromosome.getGene(geneIndex).getValue();
            int secondGeneValue = chromosome.getGene(geneIndex + 1).getValue();
            if (firstGeneValue >= 0 && secondGeneValue >= 0) {
                int newValue;
                int intervalIndex = randomGenerator.nextInt(intervals.size());
                PitchInterval newInterval = intervals.get(intervalIndex);
                if (randomGenerator.nextBoolean()) {
                    newValue = firstGeneValue + newInterval.semitones();
                } else {
                    newValue = firstGeneValue - newInterval.semitones();
                }

                if (newValue > Constants.MAX_MIDI_VALUE.value()) {
                    newValue = firstGeneValue - newInterval.semitones();
                } else if (newValue < 0) {
                    newValue = firstGeneValue + newInterval.semitones();
                }
                chromosome.getGene(geneIndex + 1).setValue(newValue);
                break;
            } else if (firstGeneValue < 0) {
                geneIndex++;
                nextGenIndex++;
            } else if (secondGeneValue < 0) {
                nextGenIndex++;
            }
        }
    }
}

