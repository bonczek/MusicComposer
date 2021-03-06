package music.analysis.feature.processor.statistics;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class AveragePitchStatistic extends StatisticCounter<Double> {

    private SummaryStatistics summaryStatistics = new SummaryStatistics();

    public AveragePitchStatistic() {
        super(0.0, (double) Pitch.MAX_MIDI_VALUE);
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            summaryStatistics.addValue((double) sound.getPitch().getMidiValue());
            numerator = summaryStatistics.getMean();
        }
    }

    @Override
    public void clear() {
        numerator = 0.0;
        summaryStatistics.clear();
    }

    public long getMidiValueListSize() {
        return summaryStatistics.getN();
    }
}
