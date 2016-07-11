package music.harmony;

import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HarmonyTest {

    private Map<NoteName, Pitch> chromaticNotesMap = createChromaticNotesMap();

    @Test
    public void testFit_givenDMajorScale() throws Exception {
        Harmony cMajorScale = new Harmony(ScaleName.MAJOR_SCALE, NoteName.D);

        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.C)), is(false));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.C_SHARP)), is(true));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.D)), is(true));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.D_SHARP)), is(false));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.E)), is(true));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.F)), is(false));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.F_SHARP)), is(true));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.G)), is(true));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.G_SHARP)), is(false));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.A)), is(true));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.A_SHARP)), is(false));
        assertThat(cMajorScale.fit(chromaticNotesMap.get(NoteName.B)), is(true));
    }

    @Test
    public void testFit_givenEMinorChord() throws Exception {
        Harmony eMinorChord = new Harmony(ChordName.MINOR, NoteName.E);

        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.C)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.C_SHARP)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.D)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.D_SHARP)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.E)), is(true));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.F)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.F_SHARP)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.G)), is(true));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.G_SHARP)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.A)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.A_SHARP)), is(false));
        assertThat(eMinorChord.fit(chromaticNotesMap.get(NoteName.B)), is(true));
    }

    private Map<NoteName, Pitch> createChromaticNotesMap() {
        Map<NoteName, Pitch> chromaticMap = new HashMap<>();
        for (NoteName noteName : NoteName.values()) {
            chromaticMap.put(noteName, Pitch.createWithNames(noteName, Octave.ONE_LINED));
        }
        return chromaticMap;
    }

}