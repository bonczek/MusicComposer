package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OneLinedOctaveGravityRuleTest extends RuleCounterTest<OneLinedOctaveGravityRule> {

    @Override
    protected OneLinedOctaveGravityRule initFeatureCounter() {
        return new OneLinedOctaveGravityRule();
    }

    @Override
    protected double getExpectedResult() {
        return 0;
    }

    @Test
    public void testSampleMelody() throws Exception {
        Note[] notes = {
                new Sound(Pitch.createWithNames(NoteName.A, Octave.ONE_LINED), Durations.WHOLE_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A, Octave.SMALL), Durations.QUARTER_NOTE)
        };
        RuleCounter rule = new OneLinedOctaveGravityRule();
        List<Note> melody = Arrays.asList(notes);
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(18.0));
    }
}