package music;

import jm.constants.Durations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Harmony {

    private final Sound base;
    private final Set<Pitch> components;

    public Harmony(PitchInterval[] intervals, Pitch base) {
        this.base = new Sound(base, Octave.SUBSUBCONTRA, Durations.WHOLE_NOTE);
        this.components = new HashSet<>();
        for (PitchInterval interval : Arrays.asList(intervals)) {
            Pitch pitchComponent = Sound.createSoundWithInterval(this.base, interval).getPitch();
            components.add(pitchComponent);
        }
    }

    public Set<Pitch> getComponents() {
        return components;
    }

    public boolean fit(Sound sound) {
        if (components.contains(sound.getPitch())) {
            return true;
        } else {
            return false;
        }
    }
}
