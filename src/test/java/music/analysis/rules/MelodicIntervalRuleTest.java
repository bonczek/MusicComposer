package music.analysis.rules;

import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MelodicIntervalRuleTest {

    @Test
    public void testProcessInterval_givenPerfectConsonanceCondition() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        MusicalRule rule = new MelodicIntervalRule(i -> !i.moreThanOctave() && i.perfectConsonance());
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(1));
    }

    @Test
    public void testProcessInterval_givenImperfectConsonanceCondition() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        MusicalRule rule = new MelodicIntervalRule(i -> !i.moreThanOctave() && i.perfectConsonance());
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(1));
    }

    @Test
    public void testProcessInterval_givenDissonanceCondition() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        MusicalRule rule = new MelodicIntervalRule(i -> i.moreThanOctave() || i.dissonance());
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(6));
    }
}