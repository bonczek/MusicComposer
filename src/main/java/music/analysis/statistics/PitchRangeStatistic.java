package music.analysis.statistics;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

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
