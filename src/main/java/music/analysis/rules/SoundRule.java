package music.analysis.rules;

import music.notes.Note;
import music.notes.Sound;

public abstract class SoundRule extends RuleCounter {

    private Sound previousSound = null;

    public Sound getPreviousSound() {
        return previousSound;
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            if (previousSound == null) {
                previousSound = sound;
            } else {
                processSound(sound);
                previousSound = sound;
            }
        }
    }

    @Override
    public void clear() {
        super.clear();
        previousSound = null;
    }

    protected abstract void processSound(Sound sound);
}
