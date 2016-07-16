package music.analysis.feature.processor.statistics;

import music.notes.Note;

import java.util.HashSet;
import java.util.Set;

/**
 * Statistic that analyze variety of rhythm values used in melody. <br/>
 * Numerator: number of distinct rhythm values <br/>
 * Denominator: given possible number of rhythm values <br/><br/>
 * <p>
 * Assumptions: <br/>
 * 0.0  - rhythm values are often repeated in further fragments. <br/>
 * 1.0 - each rhythm value is different in melody. <br/>
 * <p>
 * Warning! Greater values might cause trend in GA to choose longer notes.
 */
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
        rhythmValuesSet.clear();
    }

    public int getRhythmSetSize() {
        return rhythmValuesSet.size();
    }
}
