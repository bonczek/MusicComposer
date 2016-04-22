package music;

import jm.constants.Durations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Harmony {

    private final Sound base;
    private final Set<NoteName> components;

    public Harmony(PitchInterval[] intervals, NoteName base) {
        this.base = new Sound(base, Octave.SUBSUBCONTRA, Durations.WHOLE_NOTE);
        this.components = new HashSet<>();
        for (PitchInterval interval : Arrays.asList(intervals)) {
            NoteName noteNameComponent = Sound.createSoundWithInterval(this.base, interval).getNoteName();
            components.add(noteNameComponent);
        }
    }

    public Set<NoteName> getComponents() {
        return components;
    }

    public boolean fit(Sound sound) {
        if (components.contains(sound.getNoteName())) {
            return true;
        } else {
            return false;
        }
    }
}
