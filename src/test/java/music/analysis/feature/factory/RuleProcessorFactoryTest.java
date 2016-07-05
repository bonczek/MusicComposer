package music.analysis.feature.factory;

import music.analysis.feature.name.RuleName;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuleProcessorFactoryTest {

    @Test
    public void testCreateRule() throws Exception {
        for (RuleName ruleName : RuleName.values()) {
            assertThat(RuleProcessorFactory.createRule(ruleName), is(notNullValue()));
        }
    }
}