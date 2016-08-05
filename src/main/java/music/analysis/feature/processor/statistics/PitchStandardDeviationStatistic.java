package music.analysis.feature.processor.statistics;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;

public class PitchStandardDeviationStatistic extends StatisticCounter<Double> {

    private static double[] MIDI_LIMITS = {(double) Pitch.MIN_MIDI_VALUE, (double) Pitch.MAX_MIDI_VALUE};
    public static double MAX_STANDARD_DEVIATION = FastMath.sqrt(StatUtils.variance(MIDI_LIMITS));
//    public static double MAX_STANDARD_DEVIATION = 48.0;

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
