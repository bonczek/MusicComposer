package music.analysis.feature.processor.rules;

import music.notes.pitch.Interval;
import music.notes.pitch.PitchInterval;
import org.apache.commons.math3.distribution.NormalDistribution;

public class LessThanOctaveIntervalRule extends IntervalRule {

    private static final int ACCURACY_MULTIPLIER = 2;
    private static final double OCTAVE_STANDARD_DEVIATION = PitchInterval.values().length;
    private static final double DISTRIBUTION_MEAN = 0.0;
    private static final NormalDistribution rewardDistribution = new NormalDistribution(DISTRIBUTION_MEAN,
            OCTAVE_STANDARD_DEVIATION);

    @Override
    protected void processInterval(Interval interval) {
        if (!interval.moreThanOctave()) {
            ruleCounter += 1.0;
        }
    }

    //@todo add test
}
