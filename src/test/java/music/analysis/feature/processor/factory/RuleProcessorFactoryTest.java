package music.analysis.feature.processor.factory;

import music.analysis.feature.name.RuleName;
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
        for (RuleName ruleName : RuleName.values()) {
            assertThat(RuleProcessorFactory.createRule(ruleName, C_MAJOR_SCALE), is(notNullValue()));
        }
    }
}