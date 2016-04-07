package music.analysis.towsey.statistics;

import music.Note;
import music.Sound;

public class DissonanceStatistic extends TowseyStatistic {

    private Sound previousNote = null;

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            if (previousNote == null) {
                previousNote = sound;
            } else {
                countDissonantInterval(sound);
                previousNote = sound;
            }
        }
    }

    private void countDissonantInterval(Sound sound) {
        Integer interval = previousNote.interval(sound);
        if (interval.equals(6) || interval.equals(11) || Math.abs(interval) > 13) {
            numerator++;
        }
        denominator++;
    }
}
