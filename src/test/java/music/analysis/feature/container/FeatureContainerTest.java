package music.analysis.feature.container;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.fitness.Fitness;
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
import music.analysis.util.MelodyData;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.Note;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeatureContainerTest {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);

    @Test
    public void testCalculateReward_givenRuleFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        RuleContainer ruleContainer = new RuleContainer(prepareRuleFeatures());

        Fitness result = ruleContainer.calculateReward(testMelody);

        assertThat(result.getFitnessValue(), is(15));
    }

    @Test
    public void testCreateReport_givenRuleFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        RuleContainer ruleContainer = new RuleContainer(prepareRuleFeatures());

        String report = ruleContainer.createFitnessReport(testMelody);

        assertThat(report, is("CONSONANCES - count: 1,500000; weight: 10,000000; reward: 15\n" +
                "HALF_NOTES - count: 0,000201; weight: 10,000000; reward: 0\n"));
    }

    @Test
    public void testCalculateReward_givenStatisticalFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        StatisticContainer statisticContainer = new StatisticContainer(prepareStatFeatures());

        Fitness result = statisticContainer.calculateReward(testMelody);

        assertThat(result.getFitnessValue(), is(40));
    }

    @Test
    public void testCreateReport_givenStatisticalFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        StatisticContainer statisticContainer = new StatisticContainer(prepareStatFeatures());

        String report = statisticContainer.createFitnessReport(testMelody);

        assertThat(report, is(
                "PITCH_VARIETY - Result: 1,000000; expected: 1,000000; Diff: 0,000000; weight: 10,000000; reward: 10\n" +
                        "CONTOUR_DIRECTION - Result: 1,000000; expected: 1,000000; Diff: 0,000000; weight: 10,000000; reward: 10\n" +
                        "NON_SCALE_RATING - Result: 0,000000; expected: 0,000000; Diff: 0,000000; weight: 10,000000; reward: 10\n" +
                        "REPEATED_RHYTHM_INTERVALS - Result: 1,000000; expected: 1,000000; Diff: 0,000000; weight: 10,000000; reward: 10\n"));
    }

    @Test
    public void testFitnessUpdate_givenEmptyRuleFitness() throws Exception {
        List<Note> melody = MelodyData.prepareThreeMeasuresOfMelody();
        StatisticContainer container = new StatisticContainer(new ArrayList<>());

        assertThat(container.calculateReward(melody).getFitnessValue(), is(0));
        assertThat(container.createFitnessReport(melody), is(""));
    }

    private List<RuleFeature> prepareRuleFeatures() {
        double weight = 10.0;
        RuleFeature[] features = {
                new RuleFeature(RuleName.CONSONANCES, weight, new ConsonancesRule()),
                new RuleFeature(RuleName.HALF_NOTES, weight, new NotesRhythmRule(Durations.HALF_NOTE))
        };
        return Arrays.asList(features);
    }

    private List<StatisticalFeature> prepareStatFeatures() {
        double weight = 10.0;
        StatisticalFeature[] features = {
                new StatisticalFeature(StatisticName.PITCH_VARIETY, 1.0, weight, new PitchVarietyStatistic()),
                new StatisticalFeature(StatisticName.CONTOUR_DIRECTION, 1.0, weight, new ContourDirectionStatistic()),
                new StatisticalFeature(StatisticName.NON_SCALE_RATING, 0.0, weight, new NonScaleDensityStatistic(C_MAJOR_SCALE)),
                new StatisticalFeature(StatisticName.REPEATED_RHYTHM_INTERVALS, 1.0, weight, new RepeatedSoundRhythmPairStatistic())
        };
        return Arrays.asList(features);
    }
}