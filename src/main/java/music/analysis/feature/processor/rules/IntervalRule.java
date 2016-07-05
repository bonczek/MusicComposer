package music.analysis.feature.processor.rules;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Interval;
import music.notes.pitch.Pitch;

public abstract class IntervalRule extends RuleCounter {

    private Pitch previousPitch = null;

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
        super.clear();
        previousPitch = null;
    }

    protected abstract void processInterval(Interval interval);
}
