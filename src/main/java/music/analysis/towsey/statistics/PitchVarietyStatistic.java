package music.analysis.towsey.statistics;

import music.notes.Note;
import music.notes.Sound;

import java.util.HashSet;
import java.util.Set;

public class PitchVarietyStatistic extends StatisticCounter<Integer> {

    private Set<Integer> midiValuesSet = new HashSet<>();

    public PitchVarietyStatistic() {
        super(0, 0);
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            int midiValue = ((Sound) note).getPitch().getMidiValue();
            if (!midiValuesSet.contains(midiValue)) {
                numerator++;
                midiValuesSet.add(midiValue);
            }
            denominator++;
        }
    }

    @Override
    public void clear() {
        numerator = 0;
        denominator = 0;
    }
}
