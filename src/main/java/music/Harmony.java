package music;

import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import music.notes.pitch.PitchInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Harmony {


    private final List<NoteName> components;

    public Harmony(PitchInterval[] intervals, NoteName baseNoteName) {
        Pitch base = Pitch.createWithNames(baseNoteName, Octave.SUBSUBCONTRA);
        this.components = new ArrayList<>();
        for (PitchInterval interval : Arrays.asList(intervals)) {
            NoteName noteNameComponent = Pitch.createWithInterval(base, interval).getNoteName();
            components.add(noteNameComponent);
        }
    }

    public List<NoteName> getComponents() {
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
