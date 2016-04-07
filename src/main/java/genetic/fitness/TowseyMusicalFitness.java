package genetic.fitness;

import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.Note;

import java.util.List;

public class TowseyMusicalFitness implements FitnessFunction {

    @Override
    public void calculateFitness(List<Chromosome> population) {
        population.stream().forEach(c -> c.setFitness(rateChromosome(c)));
    }

    @Override
    public Integer rateChromosome(Chromosome chromosome) {
        List<Note> noteList = Converter.fromChromosome(chromosome);

        return 0;
    }
}
