package music.analysis.feature.processor.statistics;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Sound;

public class StrongBeatStatistic extends StatisticCounter<Double> {

    private double melodyTime = 0.0;

    public StrongBeatStatistic() {
        super(0.0, 0.0);
    }

    public double getMelodyTime() {
        return melodyTime;
    }

    @Override
    public void processNote(Note note) {
        if ((note instanceof Sound) && Double.compare(melodyTime % Durations.WHOLE_NOTE, 0.0) == 0) {
            numerator++;
        }
        melodyTime += note.getRhythmValue();
        denominator = melodyTime / Durations.WHOLE_NOTE;
    }

    @Override
    public void clear() {
        numerator = 0.0;
        denominator = 0.0;
        melodyTime = 0.0;
    }
}
