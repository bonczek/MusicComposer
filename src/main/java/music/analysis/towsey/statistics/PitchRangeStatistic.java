package music.analysis.towsey.statistics;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

public class PitchRangeStatistic extends StatisticCounter<Integer> {

    private int lowestPitch = Integer.MAX_VALUE;

    private int highestPitch = Integer.MIN_VALUE;

    public PitchRangeStatistic() {
        super(0, Pitch.MAX_MIDI_VALUE);
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
            if (numerator > denominator) {
                numerator = denominator;
            }
        }
    }

    @Override
    public void clear() {
        numerator = 0;
    }
}
