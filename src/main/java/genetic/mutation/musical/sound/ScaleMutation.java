package genetic.mutation.musical.sound;

import music.Harmony;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;

import java.util.List;
import java.util.Random;

public class ScaleMutation extends SoundMutation {

    private Harmony scale;

    public ScaleMutation(Random randomGenerator, Harmony scale) {
        super(randomGenerator);
        this.scale = scale;
    }

    @Override
    protected void mutateSounds(List<Sound> soundList) {
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
