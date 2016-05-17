package music.analysis.statistics.intervals;

import music.analysis.statistics.StatisticCounter;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Interval;
import music.notes.pitch.Pitch;

public abstract class IntervalStatistic extends StatisticCounter<Integer> {

    private Pitch previousPitch = null;

    public IntervalStatistic() {
        super(0, 0);
    }

    public Pitch getPreviousPitch() {
        return previousPitch;
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            if (previousPitch == null) {
                previousPitch = sound.getPitch();
            } else {
                Interval interval = new Interval(previousPitch, sound.getPitch());
                processInterval(interval);
                previousPitch = sound.getPitch();
            }
        }
    }

    @Override
    public void clear() {
        numerator = 0;
        denominator = 0;
        previousPitch = null;
    }

    protected abstract void processInterval(Interval interval);
}
