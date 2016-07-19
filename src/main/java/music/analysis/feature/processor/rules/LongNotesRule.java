package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.notes.Note;
import org.apache.commons.math3.distribution.NormalDistribution;

public class LongNotesRule extends RuleCounter {

    private static final double DISTRIBUTION_MEAN = Durations.HALF_NOTE;

    private static final int ACCURACY_MULTIPLIER = 2;

    private static final double DISTRIBUTION_STANDARD_DEVIATION = 1.0;

    private static final NormalDistribution REWARD_DISTRIBUTION = new NormalDistribution(DISTRIBUTION_MEAN, DISTRIBUTION_STANDARD_DEVIATION);

    @Override
    public void processNote(Note note) {
        double difference = Math.abs(note.getRhythmValue() - DISTRIBUTION_MEAN);
        double accuracyFactor = ACCURACY_MULTIPLIER * REWARD_DISTRIBUTION.cumulativeProbability(DISTRIBUTION_MEAN - difference);
        ruleCounter += accuracyFactor;
    }
}

