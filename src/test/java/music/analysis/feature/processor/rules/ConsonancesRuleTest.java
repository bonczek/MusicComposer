package music.analysis.feature.processor.rules;

import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsonancesRuleTest {

    @Test
    public void testProcessInterval_givenPerfectConsonanceCondition() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        MusicalRule rule = new ConsonancesRule();
        melody.forEach(rule::processNote);
        assertThat(rule.getResult(), is(1.5));
    }
}