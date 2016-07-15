package music.analysis.feature.processor.statistics;

import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public abstract class StatisticCounterTest<T extends MusicalStatistic> {

    private static final double PRECISION = 0.0001;

    protected T statistic = initStatistic();

    protected abstract T initStatistic();

    @Test
    public void testStatisticComputation() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        melody.forEach(statistic::processNote);
        assertEquals(statistic.getResult(), getExpectedResult(), PRECISION);
        statistic.clear();
        afterClearAsserts();
    }

    @Test
    public void testStatisticComputation_givenSameMelodyFourTimes() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        for (int i = 0; i < 4; i++) {
            melody.forEach(statistic::processNote);
            assertEquals(statistic.getResult(), getExpectedResult(), PRECISION);
            statistic.clear();
            afterClearAsserts();
        }
    }

    protected abstract double getExpectedResult();

    protected abstract void afterClearAsserts() throws Exception;

}
