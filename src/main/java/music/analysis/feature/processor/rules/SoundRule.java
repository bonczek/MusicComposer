package music.analysis.feature.processor.rules;

import music.notes.Note;
import music.notes.Sound;

public abstract class SoundRule extends RuleCounter {

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            processSound(sound);
        }
    }

    protected abstract void processSound(Sound sound);
}
