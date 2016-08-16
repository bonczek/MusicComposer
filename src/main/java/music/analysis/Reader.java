package music.analysis;

import genetic.fitness.function.MusicalFitnessFunction;
import genetic.util.Converter;
import jm.constants.Durations;
import music.analysis.feature.container.RuleContainer;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.feature.processor.factory.FeatureProcessorFactory;
import music.analysis.feature.type.RuleFeature;
import music.harmony.ChordName;
import music.harmony.ChordProgressionBuilder;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.Note;
import music.notes.pitch.NoteName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public String analyseMidiFile(String midiFilePath) {
        //@todo refactor
        Harmony scale = new Harmony(ScaleName.MAJOR_SCALE, NoteName.G);
        ChordProgressionBuilder progressionBuilder = new ChordProgressionBuilder();
        for (int i = 0; i < 12; i++) {
            progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.G), Durations.WHOLE_NOTE);
        }
//        List<StatisticalFeature> features = new ArrayList<>();
//        for (StatisticName stat : StatisticName.values()) {
//            if (!stat.equals(StatisticName.CHORD_NOTES)) {
//                DoubleFeatureCounter featureCounter = FeatureProcessorFactory.createStatistic(stat, scale,
//                        progressionBuilder.getChordList(), 16);
//                features.add(new StatisticalFeature(stat, 0.5, 10.0, 0.1, featureCounter));
//            }
//        }
//        StatisticContainer statisticContainer = new StatisticContainer(features);
        List<RuleFeature> features = new ArrayList<>();
        for (RuleName rule : RuleName.values()) {
            DoubleFeatureCounter featureCounter = FeatureProcessorFactory.createRule(rule, scale, progressionBuilder
                    .getChordList());
            features.add(new RuleFeature(rule, 1.0, featureCounter));
        }
        RuleContainer ruleContainer = new RuleContainer(features);
        MusicalFitnessFunction<RuleContainer> fitnessFunction = new MusicalFitnessFunction<>(ruleContainer);

        try {
            List<Note> melody = Converter.convertMidiToMelodyLine(midiFilePath);
            return fitnessFunction.createMelodyReport(melody);
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to analyse given midi file!");
        }
    }
}
