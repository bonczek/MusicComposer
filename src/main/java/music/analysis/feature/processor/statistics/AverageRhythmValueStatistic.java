package music.analysis.feature.processor.statistics;

import jm.constants.Durations;
import music.notes.Note;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class AverageRhythmValueStatistic extends StatisticCounter<Double> {

    //@todo add test

    private SummaryStatistics summaryStatistics = new SummaryStatistics();

    public AverageRhythmValueStatistic() {
        super(0.0, Math.log(Durations.WHOLE_NOTE + 1));
    }

    @Override
    public void processNote(Note note) {
        double logRhythm = Math.log(note.getRhythmValue() + 1);
        summaryStatistics.addValue(logRhythm);
        numerator = summaryStatistics.getMean();
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
