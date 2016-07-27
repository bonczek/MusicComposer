package music.analysis.feature.processor.statistics;

import jm.constants.Durations;
import music.notes.Note;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class RhythmStandardDeviationStatistic extends StatisticCounter<Double> {

    public static double MAX_STANDARD_DEVIATION = Math.log(Durations.WHOLE_NOTE + 1);

    private SummaryStatistics summaryStatistics = new SummaryStatistics();

    public RhythmStandardDeviationStatistic() {
        super(0.0, MAX_STANDARD_DEVIATION);
    }

    //@todo add test

    @Override
    public void processNote(Note note) {
        double logRhythm = Math.log(note.getRhythmValue() + 1);
        summaryStatistics.addValue(logRhythm);
        numerator = summaryStatistics.getStandardDeviation();
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
