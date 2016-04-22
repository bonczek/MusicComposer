package music;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Harmony {


    private final Set<NoteName> components;

    public Harmony(PitchInterval[] intervals, NoteName baseNoteName) {
        Pitch base = Pitch.createWithNames(baseNoteName, Octave.SUBSUBCONTRA);
        this.components = new HashSet<>();
        for (PitchInterval interval : Arrays.asList(intervals)) {
            NoteName noteNameComponent = Pitch.createWithInterval(base, interval).getNoteName();
            components.add(noteNameComponent);
        }
    }

    public Set<NoteName> getComponents() {
        return components;
    }

    public boolean fit(Pitch pitch) {
        if (components.contains(pitch.getNoteName())) {
            return true;
        } else {
            return false;
        }
    }
}
