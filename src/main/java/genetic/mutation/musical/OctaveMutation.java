package genetic.mutation.musical;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OctaveMutation extends MusicalMutation {

    public OctaveMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    protected void mutateNotes(List<Note> noteList) {
        List<Sound> soundList = collectSounds(noteList);
        int randomIndex = randomGenerator.nextInt(soundList.size());

        Sound sound = soundList.get(randomIndex);
        Octave actualOctave = sound.getPitch().getOctave();
        Octave newOctave;
        if (actualOctave.equals(Octave.SUBSUBCONTRA)) {
            newOctave = Octave.SUB_CONTRA;
        } else if (actualOctave.equals(Octave.SIX_LINED) || (actualOctave.equals(Octave.FIFE_LINED) && sound.getPitch
                ().getNoteName().compareTo(NoteName.G) > 0)) {
            newOctave = Octave.FIFE_LINED;
        } else {
            newOctave = chooseAdjacentOctave(actualOctave);
        }
        Pitch newPitch = Pitch.createWithNames(sound.getPitch().getNoteName(), newOctave);
        sound.setPitch(newPitch);
    }

    //@todo same method as in DissonantIntervalsMutation
    private List<Sound> collectSounds(List<Note> noteList) {
        List<Sound> soundList = new ArrayList<>();
        noteList.stream().filter(note -> note instanceof Sound).forEach(note -> {
            Sound sound = (Sound) note;
            soundList.add(sound);
        });
        return soundList;
    }

    private Octave chooseAdjacentOctave(Octave actualOctave) {
        int octaveNumber = actualOctave.number();
        int changedOctaveNumber;
        if (randomGenerator.nextBoolean()) {
            changedOctaveNumber = octaveNumber + 1;
        } else {
            changedOctaveNumber = octaveNumber - 1;
        }
        return Octave.values()[changedOctaveNumber];
    }
}
