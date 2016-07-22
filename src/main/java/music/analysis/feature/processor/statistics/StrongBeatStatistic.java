package music.analysis.feature.processor.statistics;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Sound;

public class StrongBeatStatistic extends StatisticCounter<Integer> {

    private final int numberOfMeasures;
    private double melodyTime = 0.0;

    public StrongBeatStatistic(int numberOfMeasures) {
        super(0, numberOfMeasures);
        this.numberOfMeasures = numberOfMeasures;
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
    }

    @Override
    public void clear() {
        numerator = 0;
        denominator = numberOfMeasures;
        melodyTime = 0.0;
    }
}
