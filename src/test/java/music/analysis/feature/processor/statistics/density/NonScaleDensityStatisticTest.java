package music.analysis.feature.processor.statistics.density;

import music.analysis.feature.processor.statistics.StatisticCounterTest;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.pitch.NoteName;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NonScaleDensityStatisticTest extends StatisticCounterTest<NonScaleDensityStatistic> {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);

    @Override
    protected NonScaleDensityStatistic initStatistic() {
        return new NonScaleDensityStatistic(C_MAJOR_SCALE);
    }

    @Override
    protected double getExpectedResult() {
        return 4.00 / 13.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getNumerator(), is(0.0));
        assertThat(statistic.getDenominator(), is(0.0));
    }

}