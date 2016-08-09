package music.analysis.feature.processor.statistics;

import music.analysis.feature.processor.FeatureCounterTest;
import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StrongBeatFeatureTest extends FeatureCounterTest<StrongBeatStatistic> {

    private static final int NUMBER_OF_MEASURES = 4;

    @Override
    protected StrongBeatStatistic initFeatureCounter() {
        return new StrongBeatStatistic(NUMBER_OF_MEASURES);
    }

    @Override
    protected double getExpectedResult() {
        return 1.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getMelodyTime(), is(0.0));
        assertThat(featureCounter.getNumerator(), is(0));
        assertThat(featureCounter.getDenominator(), is(NUMBER_OF_MEASURES));
    }

    @Test
    public void testProcessNote_givenIrregularRhythm() throws Exception {
        int numberOfMeasures = 2;
        List<Note> noteList = MelodyData.prepareTwoMeasuresWithLongNotesWithoutMeasureSchema();
        StrongBeatStatistic statistic = new StrongBeatStatistic(numberOfMeasures);

        noteList.forEach(statistic::processNote);

        assertThat(statistic.getResult(), is(0.5));
    }
}