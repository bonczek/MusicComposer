package genetic.fitness.statistic;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MetricTest {

    @Test
    public void testCalculateReward() throws Exception {
        double weightReward = 100.0;
        Metric testMetric = new Metric(new MetricUnit(Statistic.PITCH_RANGE, 0.5, weightReward));
        testMetric.calculateReward(0.7);
        assertThat(testMetric.getReward(), is(68));
        assertThat(testMetric.report(), is("PITCH_RANGE - Result: 0,700000; Diff: 0,200000; Reward: 68\n"));
    }

    @Test
    public void testCalculateReward_givenPerfectValue() throws Exception {
        double weightReward = 100.0;
        Metric testMetric = new Metric(new MetricUnit(Statistic.PITCH_RANGE, 0.5, weightReward));
        testMetric.calculateReward(0.5);
        assertThat(testMetric.getReward(), is((int) weightReward));
    }

    @Test
    public void testCalculateReward_givenWorstValue() throws Exception {
        double weightReward = 100.0;
        int expectedResult = 4;
        Metric testMetric = new Metric(new MetricUnit(Statistic.PITCH_RANGE, 0.0, weightReward));
        testMetric.calculateReward(1.0);
        assertThat(testMetric.getReward(), is(expectedResult));
    }

}