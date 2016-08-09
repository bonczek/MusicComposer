package music.analysis.feature.processor;

import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public abstract class FeatureCounterTest<T extends DoubleFeatureCounter> {

    private static final double PRECISION = 0.0001;

    protected T featureCounter = initFeatureCounter();

    protected abstract T initFeatureCounter();

    @Test
    public void testFeatureComputation() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        melody.forEach(featureCounter::processNote);
        assertEquals(featureCounter.getResult(), getExpectedResult(), PRECISION);
        featureCounter.clear();
        afterClearAsserts();
    }

    @Test
    public void testFeatureComputation_givenSameMelodyFourTimes() throws Exception {
        List<Note> melody = MelodyData.prepareFourMeasureSample();
        for (int i = 0; i < 4; i++) {
            melody.forEach(featureCounter::processNote);
            assertEquals(featureCounter.getResult(), getExpectedResult(), PRECISION);
            featureCounter.clear();
            afterClearAsserts();
        }
    }

    protected abstract double getExpectedResult();

    protected abstract void afterClearAsserts() throws Exception;

}
