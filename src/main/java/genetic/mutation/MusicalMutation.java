package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.notes.Note;

import java.util.List;
import java.util.Random;

public abstract class MusicalMutation extends GeneticMutation {

    public MusicalMutation(double mutationRate, Random randomGenerator) {
        super(mutationRate, randomGenerator);
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        List<Note> noteList = Converter.fromChromosome(chromosome);
        mutateNotes(noteList);
        Chromosome mutatedChromosome = Converter.fromNotes(noteList, chromosome.getSize());
        chromosome.setPart(mutatedChromosome.getPart(0, mutatedChromosome.getSize()), 0, chromosome.getSize());
    }

    protected abstract void mutateNotes(List<Note> noteList);
}
