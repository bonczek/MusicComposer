package music.analysis.statistics.density;

import music.Harmony;
import music.Scale;
import music.analysis.statistics.StatisticCounterTest;
import music.notes.pitch.NoteName;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NonScaleDensityStatisticTest extends StatisticCounterTest<NonScaleDensityStatistic> {

    private static final Harmony C_MAJOR_SCALE = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);

    @Override
    protected NonScaleDensityStatistic initStatistic() {
        return new NonScaleDensityStatistic(C_MAJOR_SCALE);
    }

    @Override
    protected double getExpectedResult() {
        return 0.25;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getNumerator(), is(0.0));
        assertThat(statistic.getDenominator(), is(0.0));
    }

}