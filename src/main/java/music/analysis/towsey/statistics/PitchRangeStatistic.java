package music.analysis.towsey.statistics;

import music.Note;
import music.Sound;

public class PitchRangeStatistic extends TowseyStatistic {

    private int lowestPitch = Integer.MAX_VALUE;

    private int highestPitch = Integer.MIN_VALUE;

    public PitchRangeStatistic(int denominator) {
        super(denominator);
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
}
