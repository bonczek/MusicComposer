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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotesRhythmRuleTest {

    @Test
    public void testProcessNote() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.HALF_NOTE);
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(1.0));
    }

    @Test
    public void testProcessNote_givenEightNote() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.QUARTER_NOTE);
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(5.0));
    }

    @Test
    public void testProcessNote_givenMelodyWithShortNotes() throws Exception {
        List<Note> melody = MelodyData.prepareTwoMeasuresWithShortNotes();
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.SIXTEENTH_NOTE);
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(6.0));
    }

    @Test
    public void testProcessNote_givenWholeNoteLeftMarginValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.DOTTED_HALF_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.WHOLE_NOTE);
        rule.processNote(note);
        assertThat(rule.getResult(), is(0.0));
    }

    @Test
    public void testProcessNote_givenWholeNoteRightMarginValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.WHOLE_NOTE + Durations.HALF_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.WHOLE_NOTE);
        rule.processNote(note);
        assertThat(rule.getResult(), is(0.5));
    }

    @Test
    public void testProcessNote_givenHalfNoteLeftMarginValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.DOTTED_QUARTER_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.HALF_NOTE);
        rule.processNote(note);
        assertThat(rule.getResult(), is(0.0));
    }

    @Test
    public void testProcessNote_givenHalfNoteRightMarginValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.DOTTED_HALF_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.HALF_NOTE);
        rule.processNote(note);
        assertThat(rule.getResult(), is(0.5));
    }

    @Test
    public void testProcessNote_givenQuarterNoteLeftMarginValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.DOTTED_EIGHTH_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.QUARTER_NOTE);
        rule.processNote(note);
        assertThat(rule.getResult(), is(0.0));
    }

    @Test
    public void testProcessNote_givenEightNoteLeftMarginValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.SIXTEENTH_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.EIGHTH_NOTE);
        rule.processNote(note);
        assertThat(rule.getResult(), is(0.0));
    }

    @Test
    public void testProcessNote_givenEightNoteRightMarginValue() throws Exception {
        Note note = new Sound(Pitch.createWithNames(NoteName.C, Octave.ONE_LINED), Durations.DOTTED_EIGHTH_NOTE);
        DoubleFeatureCounter rule = new NotesRhythmRule(Durations.EIGHTH_NOTE);
        rule.processNote(note);
        assertThat(rule.getResult(), is(0.5));
    }
}