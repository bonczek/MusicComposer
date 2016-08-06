package music.analysis.feature.processor.statistics;

import music.analysis.feature.processor.FeatureCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PitchVarietyFeatureTest extends FeatureCounterTest<PitchVarietyStatistic> {

    @Override
    protected PitchVarietyStatistic initFeatureCounter() {
        return new PitchVarietyStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.7777777777777778;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getPitchSetSize(), is(0));
        assertThat(featureCounter.getDenominator(), is(0));
        assertThat(featureCounter.getNumerator(), is(0));
    }


}