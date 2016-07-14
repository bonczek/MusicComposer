package genetic.mutation.musical;

import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.util.List;
import java.util.Random;

/**
 * Mutation to change rest/note density.
 */
public class RestDensityMutation extends MusicalMutation {

    private static final int NUMBER_OF_MIDI_VALUES = 128;

    public RestDensityMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    /**
     * Change random note in melodic line to rest or if note is a rest then change it to note with pitch already used
     * in composition.
     *
     * @param noteList melodic line
     */
    @Override
    protected void mutateNotes(List<Note> noteList) {
        int randomIndex = randomGenerator.nextInt(noteList.size());
        Note randomNote = noteList.get(randomIndex);
        if (randomNote instanceof Sound) {
            noteList.set(randomIndex, new Rest(randomNote.getRhythmValue()));
        } else if (randomNote instanceof Rest) {
            Sound newSound = new Sound(getRandomMidiPitch(), randomNote.getRhythmValue());
            noteList.set(randomIndex, newSound);
        }
    }

    private Pitch getRandomMidiPitch() {
        int midiValue = randomGenerator.nextInt(NUMBER_OF_MIDI_VALUES);
        return Pitch.createWithMidi(midiValue);
    }
}
