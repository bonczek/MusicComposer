package genetic.mutation.musical;

import jm.constants.Durations;
import music.notes.Note;

import java.util.List;
import java.util.Random;

public class NoteLengthMutation extends MusicalMutation {

    public NoteLengthMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    protected void mutateNotes(List<Note> noteList) {
        int noteToCutIndex = randomGenerator.nextInt(noteList.size());
        int noteToExtend = randomGenerator.nextInt(noteList.size());

        noteList.get(noteToCutIndex).addRhythmValue(-Durations.SIXTEENTH_NOTE);
        noteList.get(noteToExtend).addRhythmValue(Durations.SIXTEENTH_NOTE);

        noteList.removeIf(n -> Double.compare(n.getRhythmValue(), 0.0) <= 0);
    }
}
