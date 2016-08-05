package music.analysis.feature.container;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.fitness.type.FeatureFitness;
import genetic.fitness.type.Fitness;
import jm.constants.Durations;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.rules.NotesRhythmRule;
import music.analysis.feature.processor.rules.interval.ConsonancesRule;
import music.analysis.feature.processor.statistics.PitchVarietyStatistic;
import music.analysis.feature.processor.statistics.RepeatedSoundRhythmPairStatistic;
import music.analysis.feature.processor.statistics.density.NonScaleDensityStatistic;
import music.analysis.feature.processor.statistics.intervals.ContourDirectionStatistic;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import music.analysis.util.ChordProgressionData;
import music.analysis.util.MelodyData;
import music.harmony.Chord;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.Note;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeatureContainerTest {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);

    @Test
    public void testApplyFeatureProcessors_givenRuleFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        List<Chord> chords = ChordProgressionData.prepareFourMeasuresGAndCMajor();
        int weight = 10;
        RuleFeature[] features = {
                new RuleFeature(RuleName.CONSONANCES, weight, new ConsonancesRule()),
                new RuleFeature(RuleName.HALF_NOTES, weight, new NotesRhythmRule(Durations.HALF_NOTE))
        };
        List<RuleFeature> ruleFeatures = Arrays.asList(features);
        RuleContainer ruleContainer = new RuleContainer(ruleFeatures);

        ruleContainer.applyFeatureProcessors(testMelody);
        Fitness result = ruleContainer.getRewardSum();
        FeatureFitness<RuleFeature> ruleFitness = (FeatureFitness<RuleFeature>) result;
        System.out.println(ruleFitness.getReport());

        assertThat(ruleFitness.getFitnessValue(), is(15));
    }

    @Test
    public void testApplyFeatureProcessors_givenStatisticalFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        double weight = 10.0;
        StatisticalFeature[] features = {
                new StatisticalFeature(StatisticName.PITCH_VARIETY, 1.0, weight, new PitchVarietyStatistic()),
                new StatisticalFeature(StatisticName.CONTOUR_DIRECTION, 1.0, weight, new ContourDirectionStatistic()),
                new StatisticalFeature(StatisticName.NON_SCALE_RATING, 0.0, weight, new NonScaleDensityStatistic(C_MAJOR_SCALE)),
                new StatisticalFeature(StatisticName.REPEATED_RHYTHM_INTERVALS, 1.0, weight, new RepeatedSoundRhythmPairStatistic())
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