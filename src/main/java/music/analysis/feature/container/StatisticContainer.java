package music.analysis.feature.container;

import genetic.fitness.Fitness;
import genetic.fitness.calculator.FitnessCalculator;
import genetic.fitness.statistic.StatisticFitness;
import music.analysis.feature.type.StatisticalFeature;
import music.notes.Note;

import java.util.List;

public class StatisticContainer implements FeatureContainer {

    private final FitnessCalculator<StatisticalFeature> fitnessCalculator;
    private List<StatisticalFeature> statistics;

    public StatisticContainer(List<StatisticalFeature> statistics, FitnessCalculator<StatisticalFeature> fitnessCalculator) {
        this.statistics = statistics;
        this.fitnessCalculator = fitnessCalculator;
    }

    @Override
    public void applyFeatureProcessors(List<Note> melodyLine) {
        statistics.forEach(r -> r.getNoteProcessor().clear());
        for (Note note : melodyLine) {
            statistics.stream().forEach(r -> r.getNoteProcessor().processNote(note));
        }
    }

    @Override
    public Fitness getRewardSum() {
        StatisticFitness statFitness = new StatisticFitness(fitnessCalculator);
        statistics.forEach(statFitness::addMetricValues);
        return statFitness;
    }
}
