package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class NotesRhythmRuleTest {

    private static final double PRECISION = 0.0001;

    @Test
    public void testProcessNote() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        MusicalRule rule = new NotesRhythmRule(Durations.HALF_NOTE);
        melody.forEach(rule::processNote);
        assertEquals(rule.getResult(), 1.0003, PRECISION);
    }

    @Test
    public void testProcessNote_givenEightNote() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        MusicalRule rule = new NotesRhythmRule(Durations.QUARTER_NOTE);
        melody.forEach(rule::processNote);
        assertEquals(rule.getResult(), 5.004072, PRECISION);
    }
}