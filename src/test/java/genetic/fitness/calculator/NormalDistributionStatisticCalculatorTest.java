package genetic.fitness.calculator;

import music.analysis.feature.type.StatisticalFeature;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class NormalDistributionStatisticCalculatorTest {

    private NormalDistributionStatisticCalculator fitnessCalculator = new NormalDistributionStatisticCalculator();

    @Test
    public void testCalculateReward() throws Exception {
        //given
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(0.5);
        when(featureMock.getFeatureResult()).thenReturn(0.7);
        //when
        int reward = fitnessCalculator.calculateReward(featureMock);
        //then
        assertThat(reward, is(68));
    }

    @Test
    public void testCalculateReward_givenPerfectValue() throws Exception {
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(0.5);
        when(featureMock.getFeatureResult()).thenReturn(0.5);

        int reward = fitnessCalculator.calculateReward(featureMock);

        assertThat(reward, is(100));
    }

    @Test
    public void testCalculateReward_givenWorstValue() throws Exception {
        int expectedReward = 4;
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(0.0);
        when(featureMock.getFeatureResult()).thenReturn(1.0);

        int reward = fitnessCalculator.calculateReward(featureMock);

        assertThat(reward, is(expectedReward));
    }

    @Test
    public void testCalculateReward_givenNaNValue() throws Exception {
        int expectedReward = 0;
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(0.0);
        when(featureMock.getFeatureResult()).thenReturn(Double.NaN);

        int reward = fitnessCalculator.calculateReward(featureMock);

        assertThat(reward, is(expectedReward));
    }

    @Test
    public void testCalculateReward_givenGreaterThanOneResult() throws Exception {
        int expectedReward = 4;
        StatisticalFeature featureMock = Mockito.mock(StatisticalFeature.class);

        when(featureMock.getFeatureWeight()).thenReturn(100.0);
        when(featureMock.getExpectedValue()).thenReturn(1.0);
        when(featureMock.getFeatureResult()).thenReturn(2.0);

        int reward = fitnessCalculator.calculateReward(featureMock);

        assertThat(reward, is(expectedReward));
    }

}