package music.analysis.rules;

import music.notes.Note;

import java.util.List;
import java.util.function.Function;

public class RepeatingRhythm extends NoteRule {

    private static Function<List<Note>, Boolean> FUNCTION = notes -> {
        long differentRhythms = notes.stream().mapToDouble(Note::getRhythmValue).distinct().count();
        if (notes.size() != differentRhythms) {
            return true;
        } else {
            return false;
        }
    };

    protected RepeatingRhythm(int patternSize) {
        super(patternSize, FUNCTION);
    }


}
