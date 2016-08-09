package genetic.fitness.calculator;

import music.analysis.feature.type.StatisticalFeature;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ProbabilityDensityCalculatorTest {

    private ProbabilityDensityCalculator fitnessCalculator = new ProbabilityDensityCalculator();

    @Test
    public void testCalculateReward() throws Exception {
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(0.0);
        when(featureMock.getFeatureResult()).thenReturn(1.0);
        when(featureMock.getStandardDeviation()).thenReturn(1.0);
        //when
        int reward = fitnessCalculator.calculateReward(featureMock);
        //then
        assertThat(reward, is(24));
    }

    @Test
    public void testCalculateReward_givenPerfectValue() throws Exception {
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(0.0);
        when(featureMock.getFeatureResult()).thenReturn(0.0);
        when(featureMock.getStandardDeviation()).thenReturn(1.0);
        //when
        int reward = fitnessCalculator.calculateReward(featureMock);
        //then
        assertThat(reward, is(39));
    }

    @Test
    public void testCalculateReward_givenWorstValue() throws Exception {
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(0.0);
        when(featureMock.getFeatureResult()).thenReturn(1.0);
        when(featureMock.getStandardDeviation()).thenReturn(0.35);
        //when
        int reward = fitnessCalculator.calculateReward(featureMock);
        //then
        assertThat(reward, is(1));
    }
}