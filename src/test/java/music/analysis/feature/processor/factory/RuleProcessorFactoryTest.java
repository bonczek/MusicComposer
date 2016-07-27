package music.analysis.feature.processor.factory;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.analysis.feature.name.RuleName;
import music.harmony.Chord;
import music.harmony.ChordName;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuleProcessorFactoryTest {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);

    @Test
    public void testCreateRule() throws Exception {
        Chord[] chords = {new Chord(new Harmony(ChordName.MAJOR, NoteName.C), 0.0, Durations.WHOLE_NOTE)};
        for (RuleName ruleName : RuleName.values()) {
            assertThat(RuleProcessorFactory.createRule(ruleName, C_MAJOR_SCALE, Arrays.asList(chords)), is(notNullValue()));
        }
    }
}