package music.analysis.feature.processor.rules;

import music.harmony.Harmony;
import music.notes.Sound;

public class ScaleNoteRule extends SoundRule {

    private final Harmony scale;

    public ScaleNoteRule(Harmony scale) {
        this.scale = scale;
    }

    @Override
    protected void processSound(Sound sound) {
        if (scale.fit(sound.getPitch())) {
            ruleCounter += 1.0;
        }
    }
}
