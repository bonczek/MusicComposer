package gui;

import gui.model.RuleFeatureModel;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.type.RuleFeature;
import music.harmony.Chord;
import music.harmony.Harmony;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FitnessFunctionConfiguration {

    public static List<RuleFeatureModel> initWeightTestingConfiguration(Harmony scale, List<Chord> chords) {
        List<RuleFeature> features = new ArrayList<>();
        features.add(new RuleFeature(RuleName.HALF_NOTES, 250.0, scale, chords));
        features.add(new RuleFeature(RuleName.QUARTER_NOTES, 120.0, scale, chords));
        features.add(new RuleFeature(RuleName.EIGHT_NOTES, 50.0, scale, chords));
        features.add(new RuleFeature(RuleName.SIXTEENTH_NOTES, 0.0, scale, chords));
        features.add(new RuleFeature(RuleName.LESS_THAN_OCTAVE, 20.0, scale, chords));
        features.add(new RuleFeature(RuleName.ONE_LINED_GRAVITY, 30.0, scale, chords));
        features.add(new RuleFeature(RuleName.SCALE_NOTE, 10.0, scale, chords));
        features.add(new RuleFeature(RuleName.DIATONIC_NOTES, 10.0, scale, chords));
        features.add(new RuleFeature(RuleName.CHORD_NOTES, 10.0, scale, chords));
        features.add(new RuleFeature(RuleName.CONSONANCES, 10.0, scale, chords));
        features.add(new RuleFeature(RuleName.REST_NOTES, 10.0, scale, chords));
        features.add(new RuleFeature(RuleName.STRONG_BEAT, 10.0, scale, chords));

        return features.stream().map(RuleFeatureModel::new).collect(Collectors.toList());
    }
}
