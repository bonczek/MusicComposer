package music.analysis.towsey.statistics;

import music.Note;
import music.Sound;

import java.util.HashSet;
import java.util.Set;

public class PitchVarietyStatistic extends TowseyStatistic {

    private Set<Integer> midiValuesSet = new HashSet<>();

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
}
