package music.analysis.feature.processor.rules;

import music.notes.Sound;
import music.notes.pitch.Octave;
import org.apache.commons.math3.distribution.NormalDistribution;

public class OneLinedOctaveGravityRule extends SoundRule {

    private static final int ACCURACY_MULTIPLIER = 2;
    private static final double OCTAVE_STANDARD_DEVIATION = 1.0;
    private static final double DISTRIBUTION_MEAN = Octave.ONE_LINED.number();
    private static final NormalDistribution rewardDistribution = new NormalDistribution(DISTRIBUTION_MEAN,
            OCTAVE_STANDARD_DEVIATION);

    @Override
    protected void processSound(Sound sound) {
        double difference = Math.abs(sound.getPitch().getOctave().number() - DISTRIBUTION_MEAN);
        double accuracyFactor = ACCURACY_MULTIPLIER * rewardDistribution.cumulativeProbability(DISTRIBUTION_MEAN - difference);
        ruleCounter += accuracyFactor;
    }

    //@todo add test


}
