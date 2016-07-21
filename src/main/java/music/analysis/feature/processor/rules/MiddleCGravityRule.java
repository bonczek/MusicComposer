package music.analysis.feature.processor.rules;

import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import music.notes.pitch.PitchInterval;
import org.apache.commons.math3.distribution.NormalDistribution;

public class MiddleCGravityRule extends SoundRule {

    private static final Pitch MIDDLE_C = Pitch.createWithNames(NoteName.C, Octave.ONE_LINED);
    private static final int ACCURACY_MULTIPLIER = 2;
    private static final double OCTAVE_STANDARD_DEVIATION = PitchInterval.values().length;
    private static final double DISTRIBUTION_MEAN = MIDDLE_C.getMidiValue();
    private static final NormalDistribution rewardDistribution = new NormalDistribution(DISTRIBUTION_MEAN,
            OCTAVE_STANDARD_DEVIATION);

    @Override
    protected void processSound(Sound sound) {
        double difference = Math.abs(sound.getPitch().getMidiValue() - DISTRIBUTION_MEAN);
        double accuracyFactor = ACCURACY_MULTIPLIER * rewardDistribution.cumulativeProbability(DISTRIBUTION_MEAN - difference);
        ruleCounter += accuracyFactor;
    }

    //@todo add test


}
