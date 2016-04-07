package music.analysis.towsey.statistics;

import music.Note;
import music.Sound;

import java.util.HashSet;
import java.util.Set;

public class PitchVarietyStatistic extends TowseyStatistic {

    private Set<Integer> midiValuesSet = new HashSet<>();

    public PitchVarietyStatistic(int denominator) {
        super(denominator);
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            int midiValue = ((Sound) note).getMidiValue();
            if (!midiValuesSet.contains(midiValue)) {
                numerator++;
                midiValuesSet.add(midiValue);
            }
        }
    }
}
