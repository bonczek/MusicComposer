package music.analysis.feature.processor.statistics;

import music.analysis.feature.processor.FeatureCounterTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RepeatedSoundRhythmPairFeatureTest extends FeatureCounterTest<RepeatedSoundRhythmPairStatistic> {
    @Override
    protected RepeatedSoundRhythmPairStatistic initFeatureCounter() {
        return new RepeatedSoundRhythmPairStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.25;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getDenominator(), is(0));
        assertThat(featureCounter.getNumerator(), is(0));
        assertThat(featureCounter.getPreviousSound(), is(nullValue()));
    }

}