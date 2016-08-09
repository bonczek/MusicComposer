package music.analysis.feature.processor.statistics;

import music.analysis.feature.processor.FeatureCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RhythmVarietyFeatureTest extends FeatureCounterTest<RhythmVarietyStatistic> {

    @Override
    protected RhythmVarietyStatistic initFeatureCounter() {
        return new RhythmVarietyStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 5.0 / 11.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getDenominator(), is(0));
        assertThat(featureCounter.getNumerator(), is(0));
        assertThat(featureCounter.getRhythmSetSize(), is(0));
    }
}