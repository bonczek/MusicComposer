package music.harmony;

import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import music.notes.pitch.PitchInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Harmony {

    private final List<NoteName> components;
    private final HarmonyRelation harmonyRelation;
    private final NoteName baseNoteName;

    public Harmony(HarmonyRelation harmonyRelation, NoteName baseNoteName) {
        this.harmonyRelation = harmonyRelation;
        this.baseNoteName = baseNoteName;
        this.components = new ArrayList<>();
        initComponentsList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Harmony harmony = (Harmony) o;

        if (!harmonyRelation.equals(harmony.harmonyRelation)) return false;
        return baseNoteName == harmony.baseNoteName;

    }

    @Override
    public int hashCode() {
        int result = harmonyRelation.hashCode();
        result = 31 * result + baseNoteName.hashCode();
        return result;
    }

    private void initComponentsList() {
        Pitch base = Pitch.createWithNames(baseNoteName, Octave.SUBSUBCONTRA);
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
