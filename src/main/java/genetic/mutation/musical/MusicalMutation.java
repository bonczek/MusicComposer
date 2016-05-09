package genetic.mutation.musical;

import genetic.mutation.GeneticMutation;
import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.notes.Note;

import java.util.List;
import java.util.Random;

public abstract class MusicalMutation extends GeneticMutation {

    public MusicalMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        List<Note> noteList = Converter.fromChromosome(chromosome);
        mutateNotes(noteList);
        return Converter.fromNotes(noteList, chromosome.getSize());
    }

    protected abstract void mutateNotes(List<Note> noteList);
}
