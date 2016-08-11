package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.notes.Sound;
import music.notes.pitch.Octave;

public class OneLinedOctaveGravityRule extends SoundRule {

    @Override
    protected void processSound(Sound sound) {
        Octave octave = sound.getPitch().getOctave();
        if (octave.equals(Octave.ONE_LINED)) {
            ruleCounter += sound.getRhythmValue() / Durations.SIXTEENTH_NOTE;
        } else if (octave.equals(Octave.TWO_LINED) || octave.equals(Octave.SMALL)) {
            ruleCounter += 0.5 * (sound.getRhythmValue() / Durations.SIXTEENTH_NOTE);
        }
    }

}
