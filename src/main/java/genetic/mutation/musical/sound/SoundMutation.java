package genetic.mutation.musical.sound;

import genetic.mutation.musical.MusicalMutation;
import music.notes.Note;
import music.notes.Sound;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class SoundMutation extends MusicalMutation {

    public SoundMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    protected void mutateNotes(List<Note> noteList) {
        List<Sound> soundList = collectSounds(noteList);
        if (!soundList.isEmpty()) {
            mutateSounds(soundList);
        }
    }

    protected abstract void mutateSounds(List<Sound> soundList);

    private List<Sound> collectSounds(List<Note> noteList) {
        return noteList.stream().filter(note -> note instanceof Sound)
                .map(note -> (Sound) note).collect(Collectors.toList());
    }
}
