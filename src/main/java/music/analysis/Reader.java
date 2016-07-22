package music.analysis;

import genetic.fitness.function.MusicalFitnessFunction;
import genetic.fitness.type.Fitness;
import genetic.util.Converter;
import jm.constants.Durations;
import music.analysis.feature.container.StatisticContainer;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.type.StatisticalFeature;
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

    public Fitness analyseMidiFile(String midiFilePath) {

        Harmony scale = new Harmony(ScaleName.MINOR_PENTATONIC_SCALE, NoteName.A);
        ChordProgressionBuilder progressionBuilder = new ChordProgressionBuilder();
        for (int i = 0; i < 7; i++) {
            progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.C), Durations.WHOLE_NOTE);
        }
        List<StatisticalFeature> features = new ArrayList<>();
        for (StatisticName stat : StatisticName.values()) {
            if (!stat.equals(StatisticName.CHORD_NOTES)) {
                features.add(new StatisticalFeature(stat, 0.5, 10.0, scale, progressionBuilder.getChordList(), 16));
            }
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
