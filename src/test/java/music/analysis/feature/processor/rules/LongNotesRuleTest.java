package music.analysis.feature.processor.rules;

import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class LongNotesRuleTest {

    private static final double PRECISION = 0.0001;

    @Test
    public void testProcessNote() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        MusicalRule rule = new LongNotesRule();
        melody.forEach(rule::processNote);
        assertEquals(rule.getResult(), 2.9714, PRECISION);
    }
}