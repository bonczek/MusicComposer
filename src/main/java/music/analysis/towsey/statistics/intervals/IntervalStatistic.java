package music.analysis.towsey.statistics.intervals;

import music.analysis.towsey.statistics.StatisticCounter;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Interval;
import music.notes.pitch.Pitch;

public abstract class IntervalStatistic extends StatisticCounter<Integer> {

    private Pitch previousNote = null;

    public IntervalStatistic() {
        super(0, 0);
    }

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

    @Override
    public void clear() {
        numerator = 0;
        denominator = 0;
        previousNote = null;
    }

    protected abstract void processInterval(Interval interval);
}
