package genetic.mutation.musical;

import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Mutation to change rest/note density.
 */
public class RestDensityMutation extends MusicalMutation {

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
            Sound newSound = new Sound(getPitchUsedInMelody(noteList), randomNote.getRhythmValue());
            noteList.set(randomIndex, newSound);
        }
    }

    private Pitch getPitchUsedInMelody(List<Note> noteList) {
        List<Sound> soundList = noteList.stream().filter(n -> n instanceof Sound).map(n -> (Sound) n)
                .collect(Collectors.toList());
        int soundIndex = randomGenerator.nextInt(soundList.size());
        return soundList.get(soundIndex).getPitch();
    }
}
