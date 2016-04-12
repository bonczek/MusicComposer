package genetic.fitness;

import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.Note;

import java.util.List;

public abstract class MusicalFitnessFunction extends FitnessFunction {

    @Override
    protected Integer rateChromosome(Chromosome chromosome) {
        return rateMelody(Converter.fromChromosome(chromosome));
    }

    protected abstract Integer rateMelody(List<Note> noteList);
}
