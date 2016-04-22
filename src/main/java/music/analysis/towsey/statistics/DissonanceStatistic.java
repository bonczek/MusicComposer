package music.analysis.towsey.statistics;

import music.Interval;
import music.Note;
import music.Pitch;
import music.PitchInterval;
import music.Sound;

public class DissonanceStatistic extends TowseyStatistic {

    private Pitch previousNote = null;

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            if (previousNote == null) {
                previousNote = sound.getPitch();
            } else {
                countDissonantInterval(sound);
                previousNote = sound.getPitch();
            }
        }
    }

    private void countDissonantInterval(Sound sound) {
        Interval interval = new Interval(previousNote, sound.getPitch());
        if (interval.moreThanOctave() || interval.getPitchInterval().equals(PitchInterval.TRITONE) ||
                interval.getPitchInterval().equals(PitchInterval.MAJOR_SEVENTH)) {
            numerator++;
        }
        denominator++;
    }
}
