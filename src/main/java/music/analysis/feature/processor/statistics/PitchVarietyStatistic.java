package music.analysis.feature.processor.statistics;

import music.notes.Note;
import music.notes.Sound;

import java.util.HashSet;
import java.util.Set;

/**
 * Statistic that analyze variety of pitches used in melody. <br/>
 * Numerator: number of distinct pitches <br/>
 * Denominator: number of sound notes <br/>
 * <p>
 * Result near 0.0 value means that used pitches are often repeated in further fragments.
 * On the other side result near 1.0 value means that each pitch is different in melody.
 * <p>
 * Warning! Greater values might cause trend in GA to choose longer notes.
 */
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
        midiValuesSet.clear();
    }

    public int getPitchSetSize() {
        return midiValuesSet.size();
    }
}
