package music.analysis.feature.processor.statistics;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

/**
 * Analyze range of pitches used in melody.
 * <ul>
 * <li>Numerator: highest pitch - lowest pitch</li>
 * <li>Denominator: max midi value (127)</li>
 * </ul>
 * Assumptions:
 * <ul>
 * <li>0.0 - melody has only one pitch value for each note</li>
 * <li>1.0 - melody has lowest pitch value 0 and highest pitch value 127</li>
 * </ul>
 */
public class PitchRangeStatistic extends StatisticCounter<Integer> {

    private int lowestPitch = Integer.MAX_VALUE;
    private int highestPitch = Integer.MIN_VALUE;

    public PitchRangeStatistic() {
        super(0, Pitch.MAX_MIDI_VALUE);
    }

    public int getLowestPitch() {
        return lowestPitch;
    }

    public int getHighestPitch() {
        return highestPitch;
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            if (sound.getPitch().getMidiValue() < lowestPitch) {
                lowestPitch = sound.getPitch().getMidiValue();
            }
            if (sound.getPitch().getMidiValue() > highestPitch) {
                highestPitch = sound.getPitch().getMidiValue();
            }
            numerator = highestPitch - lowestPitch;
        }
    }

    @Override
    public void clear() {
        numerator = 0;
        lowestPitch = Integer.MAX_VALUE;
        highestPitch = Integer.MIN_VALUE;
    }
}
