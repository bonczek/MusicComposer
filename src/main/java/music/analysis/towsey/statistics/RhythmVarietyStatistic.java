package music.analysis.towsey.statistics;

import music.Note;

import java.util.HashSet;
import java.util.Set;

public class RhythmVarietyStatistic extends TowseyStatistic {

    private Set<Double> rhythmValuesSet = new HashSet<>();

    public RhythmVarietyStatistic(int denominator) {
        super(denominator);
    }


    @Override
    public void processNote(Note note) {
        if (!rhythmValuesSet.contains(note.getRhythmValue())) {
            numerator++;
            rhythmValuesSet.add(note.getRhythmValue());
        }
    }
}
