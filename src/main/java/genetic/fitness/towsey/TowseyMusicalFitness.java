package genetic.fitness.towsey;

import genetic.fitness.MusicalFitnessFunction;
import music.Note;
import music.analysis.towsey.statistics.DissonanceStatistic;
import music.analysis.towsey.statistics.PitchVarietyStatistic;
import music.analysis.towsey.statistics.RhythmVarietyStatistic;
import music.analysis.towsey.statistics.RhythmicRangeStatistic;
import music.analysis.towsey.statistics.TowseyStatistic;

import java.util.ArrayList;
import java.util.List;

public class TowseyMusicalFitness extends MusicalFitnessFunction {

    private List<TowseyStatistic> statistics = new ArrayList<>();

    public TowseyMusicalFitness() {
        statistics.add(new PitchVarietyStatistic());
        statistics.add(new DissonanceStatistic());
        statistics.add(new RhythmVarietyStatistic(16));
        statistics.add(new RhythmicRangeStatistic(16));
    }

    @Override
    protected Integer rateMelody(List<Note> noteList) {
        for (Note note : noteList) {
            statistics.stream().forEach(statistic -> statistic.processNote(note));
        }

        statistics.stream().mapToDouble(TowseyStatistic::getResult).sum();
        //@todo how to rate statistical approach?
        return 0;
    }
}
