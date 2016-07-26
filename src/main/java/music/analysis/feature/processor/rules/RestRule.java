package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;

public class RestRule extends RuleCounter {

    @Override
    public void processNote(Note note) {
        if (note instanceof Rest) {
            ruleCounter += note.getRhythmValue() / Durations.SIXTEENTH_NOTE;
        }
    }
}
