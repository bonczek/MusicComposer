package music.analysis.towsey.statistics;

import music.notes.Note;

import java.util.HashSet;
import java.util.Set;

public class RhythmVarietyStatistic extends StatisticCounter<Integer> {

    private Set<Double> rhythmValuesSet = new HashSet<>();

    public RhythmVarietyStatistic(int denominator) {
        super(0, denominator);
    }

    @Override
    public void processNote(Note note) {
        if (!rhythmValuesSet.contains(note.getRhythmValue())) {
            numerator++;
            rhythmValuesSet.add(note.getRhythmValue());
        }
    }

    @Override
    public void clear() {
        numerator = 0;
    }
}
