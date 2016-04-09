package genetic.fitness.towsey;

import genetic.fitness.FitnessFunction;
import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.Note;
import music.analysis.towsey.statistics.DissonanceStatistic;
import music.analysis.towsey.statistics.PitchVarietyStatistic;
import music.analysis.towsey.statistics.RhythmVarietyStatistic;
import music.analysis.towsey.statistics.RhythmicRangeStatistic;
import music.analysis.towsey.statistics.TowseyStatistic;

import java.util.ArrayList;
import java.util.List;

public class TowseyMusicalFitness implements FitnessFunction {

    private List<TowseyStatistic> statistics = new ArrayList<>();

    public TowseyMusicalFitness() {
        statistics.add(new PitchVarietyStatistic());
        statistics.add(new DissonanceStatistic());
        statistics.add(new RhythmVarietyStatistic(16));
        statistics.add(new RhythmicRangeStatistic(16));
    }

    @Override
    public void calculateFitness(List<Chromosome> population) {
        population.stream().forEach(c -> c.setFitness(rateChromosome(c)));
    }

    @Override
    public Integer rateChromosome(Chromosome chromosome) {
        List<Note> noteList = Converter.fromChromosome(chromosome);
        for (Note note : noteList) {
            statistics.stream().forEach(statistic -> statistic.processNote(note));
        }

        statistics.stream().mapToDouble(TowseyStatistic::getResult).sum();
        //@todo how to rate statistical approach?
        return 0;
    }
}
