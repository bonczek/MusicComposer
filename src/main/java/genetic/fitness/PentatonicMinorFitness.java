package genetic.fitness;

import genetic.representation.Chromosome;
import genetic.representation.Note;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by adam on 13.01.16.
 */
public class PentatonicMinorFitness implements FitnessFunction {

    private Set<Integer> pentatonicScale;

    private Set<Integer> minorRestOfScale;

    public PentatonicMinorFitness(Note primeNote) {
        int scaleValue = primeNote.value() % 12;
        this.pentatonicScale = new HashSet<>(Arrays.asList(scaleValue, (scaleValue + 3) % 12, (scaleValue + 5) % 12, (scaleValue + 7) % 12, (scaleValue + 10) % 12));
        this.minorRestOfScale = new HashSet<>(Arrays.asList((scaleValue + 2) % 12, (scaleValue + 8) % 12));
    }

    @Override
    public void calculateFitness(List<Chromosome> population) {
        population.forEach(c -> c.setFitness(rateChromosome(c)));
    }

    public Integer rateChromosome(Chromosome chromosome) {
        return chromosome.getGenesValues().stream().mapToInt(this::rateNote).sum();
    }

    private Integer rateNote(int noteValue) {
        //MINOR_SCALE = {0, 2, 3, 5, 7, 8, 10}
        //PENTATONIC_SCALE = {0, 3, 5, 7, 10}
        if (noteValue == -1 || noteValue == -2) {
            return 5 * 100;
        }
        int scaleValue = noteValue % 12;
        if (pentatonicScale.contains(scaleValue)) {
            return 20 * 100;
        } else if (minorRestOfScale.contains(scaleValue)) {
            return 10 * 100;
        } else {
            return 0;
        }
    }
}
