package music.analysis.rules;

import music.notes.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class NoteRule extends RuleCounter {

    final int patternSize;
    private Function<List<Note>, Boolean> function;
    private List<Note> patternGroup = new ArrayList<>();

    protected NoteRule(int patternSize, Function<List<Note>, Boolean> function) {
        this.patternSize = patternSize;
        this.function = function;
    }

    @Override
    public void processNote(Note note) {
        if (patternGroup.size() < patternSize) {
            patternGroup.add(note);
        } else {
            function.apply(patternGroup);
            patternGroup.clear();
        }
    }
}
