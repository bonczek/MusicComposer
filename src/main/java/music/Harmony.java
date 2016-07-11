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

    public Harmony(HarmonyRelation harmonyRelation, NoteName baseNoteName) {
        Pitch base = Pitch.createWithNames(baseNoteName, Octave.SUBSUBCONTRA);
        this.components = new ArrayList<>();
        for (PitchInterval interval : Arrays.asList(harmonyRelation.getIntervals())) {
            NoteName noteNameComponent = Pitch.createWithUpInterval(base, interval).getNoteName();
            components.add(noteNameComponent);
        }
    }

    public List<NoteName> getComponents() {
        return components;
    }

    public boolean fit(Pitch pitch) {
        return components.contains(pitch.getNoteName());
    }
}
