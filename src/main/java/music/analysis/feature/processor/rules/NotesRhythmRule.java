package music.analysis.feature.processor.rules;

import music.notes.Note;
import org.apache.commons.math3.distribution.NormalDistribution;

public class NotesRhythmRule extends RuleCounter {

    private static final int ACCURACY_MULTIPLIER = 2;
    private static final double DISTRIBUTION_STANDARD_DEVIATION = 0.25;
    private final double distributionMean;
    private final NormalDistribution rewardDistribution;

    public NotesRhythmRule(double distributionMean) {
        this.distributionMean = distributionMean;
        rewardDistribution = new NormalDistribution(distributionMean, DISTRIBUTION_STANDARD_DEVIATION);
    }

    @Override
    public void processNote(Note note) {
        double difference = Math.abs(note.getRhythmValue() - distributionMean);
        double accuracyFactor = ACCURACY_MULTIPLIER * rewardDistribution.cumulativeProbability(distributionMean - difference);
        ruleCounter += accuracyFactor;
    }
}

