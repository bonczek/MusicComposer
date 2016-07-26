package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Sound;

public class StrongBeatRule extends RuleCounter {

    private double melodyTime = 0.0;

    @Override
    public void processNote(Note note) {
        if ((note instanceof Sound) && Double.compare(melodyTime % Durations.WHOLE_NOTE, 0.0) == 0) {
            ruleCounter++;
        }
        melodyTime += note.getRhythmValue();
    }

    @Override
    public void clear() {
        super.clear();
        melodyTime = 0.0;
    }
}
