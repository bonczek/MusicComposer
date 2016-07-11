package music.analysis.feature.container;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.fitness.type.FeatureFitness;
import genetic.fitness.type.Fitness;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import music.analysis.util.MelodyData;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.Note;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeatureContainerTest {

    @Test
    public void testApplyFeatureProcessors_givenRuleFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        int weight = 10;
        RuleFeature[] features = {
                new RuleFeature(RuleName.PERFECT_CONSONANCE, weight),
                new RuleFeature(RuleName.DISSONANCE, weight),
                new RuleFeature(RuleName.LESS_THAN_OCTAVE, weight),
                new RuleFeature(RuleName.IMPERFECT_CONSONANCE, weight)
        };
        List<RuleFeature> ruleFeatures = Arrays.asList(features);
        RuleContainer ruleContainer = new RuleContainer(ruleFeatures);

        ruleContainer.applyFeatureProcessors(testMelody);
        Fitness result = ruleContainer.getRewardSum();
        FeatureFitness<RuleFeature> ruleFitness = (FeatureFitness<RuleFeature>) result;
        System.out.println(ruleFitness.getReport());

        assertThat(ruleFitness.getFitnessValue(), is(60));
    }

    @Test
    public void testApplyFeatureProcessors_givenStatisticalFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        Harmony cMajorScale = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);
        double weight = 10.0;
        StatisticalFeature[] features = {
                new StatisticalFeature(StatisticName.PITCH_VARIETY, 1.0, weight, cMajorScale),
                new StatisticalFeature(StatisticName.CONTOUR_DIRECTION, 1.0, weight, cMajorScale),
                new StatisticalFeature(StatisticName.NON_SCALE_RATING, 0.0, weight, cMajorScale),
                new StatisticalFeature(StatisticName.REPEATED_INTERVALS, 0.0, weight, cMajorScale)
        };
        List<StatisticalFeature> statFeatures = Arrays.asList(features);
        StatisticContainer statisticContainer = new StatisticContainer(statFeatures);

        statisticContainer.applyFeatureProcessors(testMelody);
        Fitness result = statisticContainer.getRewardSum();
        FeatureFitness<StatisticalFeature> fitness = (FeatureFitness<StatisticalFeature>) result;
        System.out.println(fitness.getReport());

        assertThat(fitness.getFitnessValue(), is(40));
    }
}