package music.analysis.feature.processor.statistics;

import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RhythmicRangeStatisticTest extends StatisticCounterTest<RhythmicRangeStatistic> {

    private static final int RHYTHM_DENOMINATOR = 16;

    @Override
    protected RhythmicRangeStatistic initStatistic() {
        return new RhythmicRangeStatistic(RHYTHM_DENOMINATOR);
    }

    @Override
    protected double getExpectedResult() {
        return 1.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getNumerator(), is(0));
        assertThat(statistic.getDenominator(), is(RHYTHM_DENOMINATOR));
        assertThat(statistic.getMaxRhythmValue(), is(Double.MIN_VALUE));
        assertThat(statistic.getMinRhythmValue(), is(Double.MAX_VALUE));
    }

    @Test
    public void testProcessNote_givenSameRhythmicValues() throws Exception {
        RhythmicRangeStatistic statistic = new RhythmicRangeStatistic(RHYTHM_DENOMINATOR);
        List<Note> melodyLine = MelodyData.prepareOneMeasureCMaj7Chord();
        melodyLine.forEach(statistic::processNote);

        assertThat(statistic.getResult(), is(0.0625));
    }
}