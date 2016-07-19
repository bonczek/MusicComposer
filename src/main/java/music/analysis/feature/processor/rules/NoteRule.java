package music.analysis.feature.processor.rules;

import music.notes.Note;

import java.util.function.Function;

public class NoteRule extends RuleCounter {

    private Function<Note, Boolean> function;

    public NoteRule(Function<Note, Boolean> function) {
        this.function = function;
    }

    @Override
    public void processNote(Note note) {
        if (function.apply(note)) {
            ruleCounter++;
        }
    }
}
