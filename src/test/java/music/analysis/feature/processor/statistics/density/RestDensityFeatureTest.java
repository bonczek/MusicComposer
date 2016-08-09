package music.analysis.feature.processor.statistics.density;

import music.analysis.feature.processor.FeatureCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestDensityFeatureTest extends FeatureCounterTest<RestDensityStatistic> {

    @Override
    protected RestDensityStatistic initFeatureCounter() {
        return new RestDensityStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.1875;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getDenominator(), is(0.0));
        assertThat(featureCounter.getNumerator(), is(0.0));
    }
}