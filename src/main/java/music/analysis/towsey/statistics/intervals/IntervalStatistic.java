package music.analysis.towsey.statistics.intervals;

import music.analysis.towsey.statistics.TowseyStatistic;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Interval;
import music.notes.pitch.Pitch;

public abstract class IntervalStatistic extends TowseyStatistic {

    private Pitch previousNote = null;

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            if (previousNote == null) {
                previousNote = sound.getPitch();
            } else {
                Interval interval = new Interval(previousNote, sound.getPitch());
                processInterval(interval);
                previousNote = sound.getPitch();
            }
        }
    }

    protected abstract void processInterval(Interval interval);
}
