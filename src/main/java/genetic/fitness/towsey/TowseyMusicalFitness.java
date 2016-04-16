package genetic.fitness.towsey;

import genetic.fitness.MusicalFitnessFunction;
import music.Harmony;
import music.Note;
import music.analysis.towsey.statistics.DissonanceStatistic;
import music.analysis.towsey.statistics.NonScaleStatistic;
import music.analysis.towsey.statistics.PitchVarietyStatistic;
import music.analysis.towsey.statistics.RhythmVarietyStatistic;
import music.analysis.towsey.statistics.RhythmicRangeStatistic;
import music.analysis.towsey.statistics.TowseyStatistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TowseyMusicalFitness extends MusicalFitnessFunction {

    private Map<TowseyStatistic, Double> statisticsExpectedMap = new HashMap<>();

    public TowseyMusicalFitness(Harmony scale, int numbersOfMeasures) {
        statisticsExpectedMap.put(new PitchVarietyStatistic(), 0.27);
        statisticsExpectedMap.put(new DissonanceStatistic(), 0.01);
        statisticsExpectedMap.put(new RhythmVarietyStatistic(16), 0.24);
        statisticsExpectedMap.put(new RhythmicRangeStatistic(16), 0.32);
        statisticsExpectedMap.put(new NonScaleStatistic(numbersOfMeasures, scale), 0.1);
        //statisticsExpectedMap.put(new PitchRangeStatistic(24), 0.5);
    }

    @Override
    protected Integer rateMelody(List<Note> noteList) {
        statisticsExpectedMap.keySet().stream().forEach(TowseyStatistic::clear);
        for (Note note : noteList) {
            statisticsExpectedMap.keySet().stream().forEach(statistic -> statistic.processNote(note));
        }

        List<Double> statisticDifferences = statisticsExpectedMap.entrySet().stream().map(entry -> Math.abs(entry
                .getKey().getResult() - entry.getValue())).collect(Collectors.toList());
        List<Double> rewards = statisticDifferences.stream().map(value -> 1000 * Math.cos(Math.PI * value)).collect
                (Collectors.toList());
        Double rewardSum = rewards.stream().mapToDouble(d -> d).sum();
        return rewardSum.intValue();
    }
}
