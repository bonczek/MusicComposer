package genetic.mutation.musical;

import music.Harmony;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ScaleMutation extends MusicalMutation {

    private Harmony scale;

    public ScaleMutation(Random randomGenerator, Harmony scale) {
        super(randomGenerator);
        this.scale = scale;
    }

    @Override
    protected void mutateNotes(List<Note> noteList) {
        List<Sound> soundList = noteList.stream().filter(n -> n instanceof Sound).map(n -> (Sound) n)
                .collect(Collectors.toList());
        int soundIndex = randomGenerator.nextInt(soundList.size());
        Sound sound = soundList.get(soundIndex);

        sound.setPitch(getMutatedScalePitch(sound.getPitch().getOctave()));
    }

    private Pitch getMutatedScalePitch(Octave actualOctave) {
        NoteName newScaleNote = getRandomScaleNoteName();
        Octave mutatedNoteOctave;
        if (actualOctave.equals(Octave.SIX_LINED) && newScaleNote.value() > NoteName.G.value()) {
            mutatedNoteOctave = Octave.FIFE_LINED;
        } else {
            mutatedNoteOctave = actualOctave;
        }
        return Pitch.createWithNames(newScaleNote, mutatedNoteOctave);
    }

    private NoteName getRandomScaleNoteName() {
        int noteIndex = randomGenerator.nextInt(scale.getComponents().size());
        return scale.getComponents().get(noteIndex);
    }
}
