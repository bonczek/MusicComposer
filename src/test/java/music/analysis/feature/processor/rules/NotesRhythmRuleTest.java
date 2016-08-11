package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.util.MelodyData;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class NotesRhythmRuleTest {

    private static final double PRECISION = 0.0001;

    @Test
    public void testProcessNote() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.HALF_NOTE);
        melody.forEach(rule::processNote);
        assertEquals(rule.getResult(), 1.0013, PRECISION);
    }

    @Test
    public void testProcessNote_givenEightNote() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.QUARTER_NOTE);
        melody.forEach(rule::processNote);
        assertEquals(rule.getResult(), 5.016255, PRECISION);
    }

    @Test
    public void testNextValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.QUARTER_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.EIGHTH_NOTE);
        rule.processNote(note);
        assertEquals(rule.getResult(), 0.01595, PRECISION);
    }
}