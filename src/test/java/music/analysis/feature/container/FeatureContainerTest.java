package music.analysis.feature.container;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.rules.IntervalRule;
import music.analysis.feature.processor.rules.NotesRhythmRule;
import music.analysis.feature.processor.statistics.PitchVarietyStatistic;
import music.analysis.feature.processor.statistics.RepeatedSoundRhythmPairStatistic;
import music.analysis.feature.processor.statistics.density.NonScaleDensityStatistic;
import music.analysis.feature.processor.statistics.intervals.ContourDirectionStatistic;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import music.analysis.util.MelodyData;
import music.harmony.Harmony;
import music.harmony.ScaleType;
import music.notes.Note;
import music.notes.pitch.Interval;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeatureContainerTest {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleType.MAJOR_SCALE, NoteName.C);

    @Test
    public void testCalculateReward_givenRuleFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        RuleContainer ruleContainer = new RuleContainer(prepareRuleFeatures());

        int result = ruleContainer.calculateReward(testMelody);

        assertThat(result, is(30));
    }

    @Test
    public void testCreateReport_givenRuleFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        RuleContainer ruleContainer = new RuleContainer(prepareRuleFeatures());

        String report = ruleContainer.createFitnessReport(testMelody);

        assertThat(report, is("IMPERFECT_CONSONANCE - count: 3,000000; weight: 10,000000; reward: 30\n" +
                "HALF_NOTE - count: 0,000000; weight: 10,000000; reward: 0\n"));
    }

    @Test
    public void testCalculateReward_givenStatisticalFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        StatisticContainer statisticContainer = new StatisticContainer(prepareStatFeatures());

        int result = statisticContainer.calculateReward(testMelody);

        assertThat(result, is(4 * 10));
    }

    @Test
    public void testCreateReport_givenStatisticalFeatures() throws Exception {
        List<Note> testMelody = MelodyData.prepareOneMeasureCMaj7Chord();
        StatisticContainer statisticContainer = new StatisticContainer(prepareStatFeatures());

        String report = statisticContainer.createFitnessReport(testMelody);

        assertThat(report, is(
                "PITCH_VARIETY - Result: 1,000000; expected: 1,000000; Diff: 0,000000; weight: 10,000000; stdev: 0," +
                        "100000; reward: 10\n" +
                        "CONTOUR_DIRECTION - Result: 1,000000; expected: 1,000000; Diff: 0,000000; weight: 10,000000;" +
                        " stdev: 0,100000; reward: 10\n" +
                        "NON_SCALE_RATING - Result: 0,000000; expected: 0,000000; Diff: 0,000000; weight: 10,000000; " +
                        "stdev: 0,100000; reward: 10\n" +
                        "REPEATED_RHYTHM_INTERVALS - Result: 1,000000; expected: 1,000000; Diff: 0,000000; weight: 10," +
                        "000000; stdev: 0,100000; reward: 10\n"));
    }

    @Test
    public void testFitnessUpdate_givenEmptyRuleFitness() throws Exception {
        List<Note> melody = MelodyData.prepareThreeMeasuresOfMelody();
        StatisticContainer container = new StatisticContainer(new ArrayList<>());

        assertThat(container.calculateReward(melody), is(0));
        assertThat(container.createFitnessReport(melody), is(""));
    }

    private List<RuleFeature> prepareRuleFeatures() {
        double weight = 10.0;
        RuleFeature[] features = {
                new RuleFeature(RuleName.IMPERFECT_CONSONANCE, weight, new IntervalRule(Interval::imperfectConsonance)),
                new RuleFeature(RuleName.HALF_NOTE, weight, new NotesRhythmRule(Durations.HALF_NOTE))
        };
        return Arrays.asList(features);
    }

    private List<StatisticalFeature> prepareStatFeatures() {
        double weight = 10.0;
        StatisticalFeature[] features = {
                new StatisticalFeature(StatisticName.PITCH_VARIETY, 1.0, weight, 0.1, new PitchVarietyStatistic()),
                new StatisticalFeature(StatisticName.CONTOUR_DIRECTION, 1.0, weight, 0.1, new ContourDirectionStatistic
                        ()),
                new StatisticalFeature(StatisticName.NON_SCALE_RATING, 0.0, weight, 0.1, new NonScaleDensityStatistic
                        (C_MAJOR_SCALE)),
                new StatisticalFeature(StatisticName.REPEATED_RHYTHM_INTERVALS, 1.0, weight, 0.1, new
                        RepeatedSoundRhythmPairStatistic())
        };
        return Arrays.asList(features);
    }
}