package music.analysis.feature.processor.statistics.density;

import music.analysis.feature.processor.FeatureCounterTest;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.pitch.NoteName;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NonScaleDensityFeatureTest extends FeatureCounterTest<NonScaleDensityStatistic> {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);

    @Override
    protected NonScaleDensityStatistic initFeatureCounter() {
        return new NonScaleDensityStatistic(C_MAJOR_SCALE);
    }

    @Override
    protected double getExpectedResult() {
        return 4.00 / 13.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getNumerator(), is(0.0));
        assertThat(featureCounter.getDenominator(), is(0.0));
    }

}