package music.analysis.feature.processor.rules;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Interval;
import music.notes.pitch.Pitch;

import java.util.function.Function;

public class IntervalRule extends RuleCounter {

    private final Function<Interval, Boolean> condition;
    private Pitch previousPitch = null;

    public IntervalRule(Function<Interval, Boolean> condition) {
        this.condition = condition;
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
        super.clear();
        previousPitch = null;
    }

    private void processInterval(Interval interval) {
        if (condition.apply(interval)) {
            ruleCounter += 1.0;
        }
    }
}
