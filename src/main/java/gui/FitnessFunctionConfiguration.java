package gui;

import gui.model.RuleFeatureModel;
import gui.model.StatisticalFeatureModel;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;

import java.util.ArrayList;
import java.util.List;

public class FitnessFunctionConfiguration {

    public static List<RuleFeatureModel> initRuleConfiguration() {
        List<RuleFeatureModel> features = new ArrayList<>();
        features.add(new RuleFeatureModel(RuleName.HALF_NOTES, 250.0, true));
        features.add(new RuleFeatureModel(RuleName.QUARTER_NOTES, 120.0, true));
        features.add(new RuleFeatureModel(RuleName.EIGHT_NOTES, 50.0, true));
        features.add(new RuleFeatureModel(RuleName.SIXTEENTH_NOTES, 0.0, true));
        features.add(new RuleFeatureModel(RuleName.LESS_THAN_OCTAVE, 20.0, true));
        features.add(new RuleFeatureModel(RuleName.ONE_LINED_GRAVITY, 30.0, true));
        features.add(new RuleFeatureModel(RuleName.SCALE_NOTE, 10.0, true));
        features.add(new RuleFeatureModel(RuleName.DIATONIC_NOTES, 10.0, true));
        features.add(new RuleFeatureModel(RuleName.CHORD_NOTES, 10.0, true));
        features.add(new RuleFeatureModel(RuleName.CONSONANCES, 10.0, true));
        features.add(new RuleFeatureModel(RuleName.REST_NOTES, 10.0, true));
        features.add(new RuleFeatureModel(RuleName.STRONG_BEAT, 10.0, true));

        return features;
    }

    public static List<StatisticalFeatureModel> initStatisticConfiguration() {
        List<StatisticalFeatureModel> features = new ArrayList<>();
        features.add(new StatisticalFeatureModel(StatisticName.AVERAGE_PITCH, 0.5, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_STANDARD_DEVIATION, 0.1, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.NON_SCALE_RATING, 0.0, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.CHORD_NOTES, 0.5, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.DISSONANCE_RATING, 0.1, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.DIATONIC_RATING, 0.5, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.ILLEGAL_JUMP, 0.0, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.AVERAGE_RHYTHM, 0.4, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_STANDARD_DEVIATION, 0.2, 100.0, 0.1, true));
        features.add(new StatisticalFeatureModel(StatisticName.STRONG_BEAT, 0.9, 100.0, 0.1, true));

        features.add(new StatisticalFeatureModel(StatisticName.CONTOUR_DIRECTION, 0.5, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.CONTOUR_STABILITY, 0.5, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_RANGE, 0.5, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_VARIETY, 0.5, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.REPEATED_INTERVALS, 0.5, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.REPEATED_RHYTHM_INTERVALS, 0.5, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.REST_DENSITY, 0.3, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_RANGE, 0.4, 100.0, 0.1, false));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_VARIETY, 0.3, 100.0, 0.1, false));

        return features;
    }
}
