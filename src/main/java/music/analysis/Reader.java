package music.analysis;

import genetic.fitness.Fitness;
import genetic.fitness.statistic.MusicalStatisticsFitness;
import genetic.util.Converter;
import music.Harmony;
import music.Scale;
import music.notes.Note;
import music.notes.pitch.NoteName;

import java.io.IOException;
import java.util.List;

public class Reader {

    public Fitness analyseMidiFile(String midiFilePath) {
        Harmony scale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
        MusicalStatisticsFitness fitnessFunction = new MusicalStatisticsFitness(scale);

        try {
            List<Note> melody = Converter.convertMidiToMelodyLine(midiFilePath);
            return fitnessFunction.rateMelody(melody);
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to analyse given midi file!");
        }
    }
}
