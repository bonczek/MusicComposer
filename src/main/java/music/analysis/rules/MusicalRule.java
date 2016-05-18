package music.analysis.rules;

import music.notes.Note;

public interface MusicalRule {

    void processNote(Note note);

    void clear();

    int ruleCount();
}
