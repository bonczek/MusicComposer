package music.analysis;

import genetic.fitness.function.MusicalFitnessFunction;
import genetic.fitness.type.Fitness;
import genetic.util.Converter;
import music.analysis.feature.container.StatisticContainer;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.type.StatisticalFeature;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.Note;
import music.notes.pitch.NoteName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public Fitness analyseMidiFile(String midiFilePath) {

        Harmony scale = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);
        List<StatisticalFeature> features = new ArrayList<>();
        for (StatisticName stat : StatisticName.values()) {
            features.add(new StatisticalFeature(stat, 0.5, 10.0, scale));
        }
        StatisticContainer statisticContainer = new StatisticContainer(features);
        MusicalFitnessFunction<StatisticContainer> fitnessFunction = new MusicalFitnessFunction<>(statisticContainer);

        try {
            List<Note> melody = Converter.convertMidiToMelodyLine(midiFilePath);
            return fitnessFunction.rateMelody(melody);
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to analyse given midi file!");
        }
    }
}
