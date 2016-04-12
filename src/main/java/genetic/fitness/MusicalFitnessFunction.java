package genetic.fitness;

import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.Note;

import java.util.List;

public abstract class MusicalFitnessFunction implements FitnessFunction {

    @Override
    public void calculateFitness(List<Chromosome> population) {
        population.stream().forEach(c -> c.setFitness(rateChromosome(c)));
    }

    @Override
    public Integer rateChromosome(Chromosome chromosome) {
        return rateMelody(Converter.fromChromosome(chromosome));
    }

    public abstract Integer rateMelody(List<Note> noteList);
}
