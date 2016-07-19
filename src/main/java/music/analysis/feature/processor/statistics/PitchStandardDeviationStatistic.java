package music.analysis.feature.processor.statistics;

import music.notes.Note;
import music.notes.Sound;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class PitchStandardDeviationStatistic extends StatisticCounter<Double> {

    public static double MAX_STANDARD_DEVIATION = 48.0;

    private SummaryStatistics summaryStatistics = new SummaryStatistics();

    public PitchStandardDeviationStatistic() {
        super(0.0, MAX_STANDARD_DEVIATION);
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            summaryStatistics.addValue((double) sound.getPitch().getMidiValue());
            numerator = summaryStatistics.getStandardDeviation();
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
