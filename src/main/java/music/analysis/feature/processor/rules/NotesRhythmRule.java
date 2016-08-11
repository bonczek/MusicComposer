package music.analysis.feature.processor.rules;

import music.notes.Note;
import org.apache.commons.math3.distribution.NormalDistribution;

public class NotesRhythmRule extends RuleCounter {

    private static final double DISTRIBUTION_STANDARD_DEVIATION = 0.1;
    private final NormalDistribution ruleDistribution;
    private final double maxDistributionValue;

    public NotesRhythmRule(double distributionMean) {
        double logRhythmMean = Math.log(distributionMean + 1.0);
        ruleDistribution = new NormalDistribution(logRhythmMean, DISTRIBUTION_STANDARD_DEVIATION);
        this.maxDistributionValue = ruleDistribution.density(logRhythmMean);
    }

    @Override
    public void processNote(Note note) {
        double logRhythm = Math.log(note.getRhythmValue() + 1.0);
        ruleCounter += ruleDistribution.density(logRhythm) / maxDistributionValue;
    }
}

