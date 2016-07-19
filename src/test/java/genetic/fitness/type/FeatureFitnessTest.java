package genetic.fitness.type;

import genetic.fitness.calculator.FitnessCalculator;
import genetic.fitness.calculator.NormalDistributionStatisticCalculator;
import genetic.fitness.calculator.RuleFitnessCalculator;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class FeatureFitnessTest {

    private static final String TEST_REPORT = "TEST";

    private static final double FEATURE_WEIGHT = 10.0;

    private static final double FEATURE_COUNT = 50.0;

    private static final FitnessCalculator<RuleFeature> RULE_FITNESS_CALCULATOR = new RuleFitnessCalculator();

    private static final FitnessCalculator<StatisticalFeature> STATISTIC_FITNESS_CALCULATOR =
            Mockito.mock(NormalDistributionStatisticCalculator.class);

    private static final StatisticalFeature STATISTIC_MOCK = Mockito.mock(StatisticalFeature.class);

    @Test
    public void testFitnessUpdate_givenSingleRuleFeature() throws Exception {
        RuleFeature ruleFeature = Mockito.mock(RuleFeature.class);
        when(ruleFeature.getFeatureResult()).thenReturn(FEATURE_COUNT);
        when(ruleFeature.getFeatureWeight()).thenReturn(FEATURE_WEIGHT);
        when(ruleFeature.getReport()).thenReturn(TEST_REPORT);

        FeatureFitness<RuleFeature> fitness = new FeatureFitness<>(RULE_FITNESS_CALCULATOR);
        fitness.addFeatureReward(ruleFeature);

        int fitnessReward = (int) (FEATURE_WEIGHT * FEATURE_COUNT);
        assertThat(fitness.getFitnessValue(), is(fitnessReward));
        assertThat(fitness.getReport(), is(String.format("%s reward: %d\n", TEST_REPORT, fitnessReward)));
    }

    @Test
    public void testFitnessUpdate_givenEmptyRuleFitness() throws Exception {
        FeatureFitness<RuleFeature> fitness = new FeatureFitness<>(RULE_FITNESS_CALCULATOR);
        int reward = 0;

        assertThat(fitness.getFitnessValue(), is(reward));
        assertThat(fitness.getReport(), is(""));
    }

    @Test
    public void testFitnessUpdate_givenFourRuleFeatures() {
        int numberOfRules = 4;
        RuleFeature ruleFeature = Mockito.mock(RuleFeature.class);
        when(ruleFeature.getFeatureResult()).thenReturn(FEATURE_COUNT);
        when(ruleFeature.getFeatureWeight()).thenReturn(FEATURE_WEIGHT);
        when(ruleFeature.getReport()).thenReturn(TEST_REPORT);

        FeatureFitness<RuleFeature> fitness = new FeatureFitness<>(RULE_FITNESS_CALCULATOR);
        for (int i = 0; i < numberOfRules; i++) {
            fitness.addFeatureReward(ruleFeature);
        }
        int fitnessReward = (int) (numberOfRules * FEATURE_WEIGHT * FEATURE_COUNT);
        assertThat(fitness.getFitnessValue(), is(fitnessReward));
        String[] reportLines = fitness.getReport().split("\n");
        assertThat(reportLines.length, is(numberOfRules));
        for (int i = 0; i < numberOfRules; i++) {
            assertThat(reportLines[i], is(String.format("%s reward: %d", TEST_REPORT, fitnessReward / 4)));
        }
    }


    @Test
    public void testFitnessUpdate_givenSingleStatisticFeature() throws Exception {
        int reward = 20;

        when(STATISTIC_MOCK.getReport()).thenReturn(TEST_REPORT);
        when(STATISTIC_FITNESS_CALCULATOR.calculateReward(STATISTIC_MOCK)).thenReturn(reward);

        FeatureFitness<StatisticalFeature> fitness = new FeatureFitness<>(STATISTIC_FITNESS_CALCULATOR);
        fitness.addFeatureReward(STATISTIC_MOCK);

        assertThat(fitness.getFitnessValue(), is(reward));
        assertThat(fitness.getReport(), is(String.format("%s reward: %d\n", TEST_REPORT, reward)));
    }
}