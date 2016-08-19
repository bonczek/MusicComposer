package music.analysis;

import genetic.fitness.function.MusicalFitnessFunction;
import genetic.util.Converter;
import music.notes.Note;

import java.io.IOException;
import java.util.List;

public class Reader {

    public String analyseMidiFile(String midiFilePath, MusicalFitnessFunction fitnessFunction) throws
            IllegalArgumentException {
        try {
            List<Note> melody = Converter.convertMidiToMelodyLine(midiFilePath);
            String report = fitnessFunction.createAnalysisReport(melody);
            System.out.println(report);
            return report;
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Failed to analyse given midi file: %s. %s",
                    midiFilePath, e.getMessage()));
        }
    }
}
