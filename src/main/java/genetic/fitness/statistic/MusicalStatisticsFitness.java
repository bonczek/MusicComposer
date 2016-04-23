package genetic.fitness.statistic;

import genetic.fitness.Fitness;
import genetic.fitness.MusicalFitnessFunction;
import music.Harmony;
import music.analysis.towsey.statistics.DissonanceStatistic;
import music.analysis.towsey.statistics.NonScaleStatistic;
import music.analysis.towsey.statistics.PitchRangeStatistic;
import music.analysis.towsey.statistics.PitchVarietyStatistic;
import music.analysis.towsey.statistics.RhythmVarietyStatistic;
import music.analysis.towsey.statistics.RhythmicRangeStatistic;
import music.analysis.towsey.statistics.TowseyStatistic;
import music.notes.Note;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicalStatisticsFitness extends MusicalFitnessFunction {

    private Map<Metric, TowseyStatistic> statistics = new HashMap<>();

    public MusicalStatisticsFitness(Harmony scale, int numbersOfMeasures) {
        double rewardWeight = 1000.0;
        statistics.put(new Metric(new MetricUnit(Statistic.PITCH_VARIETY, 0.27, rewardWeight)), new PitchVarietyStatistic());
        statistics.put(new Metric(new MetricUnit(Statistic.DISSONANCE_RATING, 0.01, rewardWeight)), new
                DissonanceStatistic());
        statistics.put(new Metric(new MetricUnit(Statistic.RHYTHM_VARIETY, 0.24, rewardWeight)), new
                RhythmVarietyStatistic(16));
        statistics.put(new Metric(new MetricUnit(Statistic.RHYTHM_RANGE, 0.32, rewardWeight)), new
                RhythmicRangeStatistic(16));
        statistics.put(new Metric(new MetricUnit(Statistic.NON_SCALE_RATING, 0.1, rewardWeight)), new
                NonScaleStatistic(numbersOfMeasures, scale));
        statistics.put(new Metric(new MetricUnit(Statistic.PITCH_RANGE, 0.5, rewardWeight)), new
                PitchRangeStatistic(24));
    }

    @Override
    protected Fitness rateMelody(List<Note> noteList) {
        statistics.values().stream().forEach(TowseyStatistic::clear);
        for (Note note : noteList) {
            statistics.values().stream().forEach(statistic -> statistic.processNote(note));
        }

        statistics.entrySet().forEach(entry -> entry.getKey().calculateReward(entry.getValue().getResult()));
        StatisticFitness fitness = new StatisticFitness();
        statistics.keySet().forEach(fitness::addMetricValues);
        return fitness;
    }
}
