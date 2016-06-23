package genetic.fitness;

import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.notes.Note;

import java.util.List;

public abstract class MusicalFitnessFunction extends FitnessFunction {

    @Override
    protected Fitness rateChromosome(Chromosome chromosome) {
        return rateMelody(Converter.fromChromosome(chromosome));
    }

    public abstract Fitness rateMelody(List<Note> noteList);
}
