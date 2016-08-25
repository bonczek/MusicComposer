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
        features.add(new RuleFeatureModel(RuleName.WHOLE_NOTE, 80.0, true));
        features.add(new RuleFeatureModel(RuleName.HALF_NOTE, 40.0, true));
        features.add(new RuleFeatureModel(RuleName.QUARTER_NOTE, 20.0, true));
        features.add(new RuleFeatureModel(RuleName.EIGHT_NOTE, 8.0, true));
        features.add(new RuleFeatureModel(RuleName.SIXTEENTH_NOTE, 0.0, true));
        features.add(new RuleFeatureModel(RuleName.LESS_THAN_OCTAVE, 2.0, true));
        features.add(new RuleFeatureModel(RuleName.ONE_LINED_GRAVITY, 3.0, true));
        features.add(new RuleFeatureModel(RuleName.SCALE_NOTE, 1.5, true));
        features.add(new RuleFeatureModel(RuleName.DIATONIC_NOTE, 4.0, true));
        features.add(new RuleFeatureModel(RuleName.DISSONANCE, 2.0, true));
        features.add(new RuleFeatureModel(RuleName.CHORD_NOTE, 1.0, true));
        features.add(new RuleFeatureModel(RuleName.IMPERFECT_CONSONANCE, 3.0, true));
        features.add(new RuleFeatureModel(RuleName.PERFECT_CONSONANCE, 1.0, true));
        features.add(new RuleFeatureModel(RuleName.REST_NOTE, 5.0, true));
        features.add(new RuleFeatureModel(RuleName.STRONG_BEAT, 5.0, true));

        return features;
    }

    public static List<StatisticalFeatureModel> initStatisticConfiguration() {
        List<StatisticalFeatureModel> features = new ArrayList<>();
        features.add(new StatisticalFeatureModel(StatisticName.AVERAGE_PITCH, 0.5, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_STANDARD_DEVIATION, 0.1, 200.0, 0.2, true));
        features.add(new StatisticalFeatureModel(StatisticName.NON_SCALE_RATING, 0.0, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.CHORD_NOTES, 0.5, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.DISSONANCE_RATING, 0.1, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.DIATONIC_RATING, 0.5, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.ILLEGAL_JUMP, 0.0, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.AVERAGE_RHYTHM, 0.4, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_STANDARD_DEVIATION, 0.2, 200.0, 0.2, true));
        features.add(new StatisticalFeatureModel(StatisticName.STRONG_BEAT, 0.9, 200.0, 0.3, true));

        features.add(new StatisticalFeatureModel(StatisticName.CONTOUR_DIRECTION, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.CONTOUR_STABILITY, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_RANGE, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_VARIETY, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.REPEATED_INTERVALS, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.REPEATED_RHYTHM_INTERVALS, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.REST_DENSITY, 0.3, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_RANGE, 0.4, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_VARIETY, 0.3, 200.0, 0.3, false));

        return features;
    }

    public static List<StatisticalFeatureModel> initStatisticConfigurationWithValuesFromClassicalCompositions() {
        List<StatisticalFeatureModel> features = new ArrayList<>();
        features.add(new StatisticalFeatureModel(StatisticName.AVERAGE_PITCH, 0.5644472074, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_STANDARD_DEVIATION, 0.0535344475, 200.0, 0.2,
                true));

        features.add(new StatisticalFeatureModel(StatisticName.NON_SCALE_RATING, 0.0, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.CHORD_NOTES, 0.5, 200.0, 0.3, true));

        features.add(new StatisticalFeatureModel(StatisticName.DISSONANCE_RATING, 0.0294587656, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.DIATONIC_RATING, 0.5528802377, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.ILLEGAL_JUMP, 0.0066779546, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.AVERAGE_RHYTHM, 0.2815038409, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_STANDARD_DEVIATION, 0.155966227, 200.0, 0.3,
                true));
        features.add(new StatisticalFeatureModel(StatisticName.STRONG_BEAT, 0.7881109912, 200.0, 0.3, true));
        features.add(new StatisticalFeatureModel(StatisticName.REST_DENSITY, 0.1049814209, 200.0, 0.3, true));

        features.add(new StatisticalFeatureModel(StatisticName.CONTOUR_DIRECTION, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.CONTOUR_STABILITY, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_RANGE, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.PITCH_VARIETY, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.REPEATED_INTERVALS, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.REPEATED_RHYTHM_INTERVALS, 0.5, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_RANGE, 0.4, 200.0, 0.3, false));
        features.add(new StatisticalFeatureModel(StatisticName.RHYTHM_VARIETY, 0.3, 200.0, 0.3, false));

        return features;
    }
}
