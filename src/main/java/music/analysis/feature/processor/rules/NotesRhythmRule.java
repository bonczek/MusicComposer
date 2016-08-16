package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.notes.Note;

public class NotesRhythmRule extends RuleCounter {

    private final double rhythmicValueMean;
    private final double leftMargin;
    private final double rightMargin;

    public NotesRhythmRule(double rhythmicValueMean) {
        this.rhythmicValueMean = rhythmicValueMean;
        this.leftMargin = rhythmicValueMean - (rhythmicValueMean / 4.0);
        this.rightMargin = rhythmicValueMean + (rhythmicValueMean / 2.0);
    }

    @Override
    public void processNote(Note note) {
        double rhythmValue = note.getRhythmValue();
        if (Double.compare(rhythmValue, rhythmicValueMean) == 0) {
            ruleCounter += 1.0;
            return;
        }

        if (Double.compare(rhythmValue, Durations.SIXTEENTH_NOTE) != 0) {
            if ((Double.compare(rhythmValue, leftMargin) > 0) && (Double.compare(rhythmValue, rightMargin) <= 0)) {
                ruleCounter += 0.5;
            }
        }
    }
}

