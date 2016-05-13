package music.analysis.statistics;

import music.notes.Note;

public interface MusicalStatistic {

    void processNote(Note note);

    void clear();

    double getResult();

}
