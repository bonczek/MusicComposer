package genetic.mutation.musical;

import genetic.mutation.MusicalMutation;
import genetic.representation.Constants;
import music.Harmony;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScaleMutation extends MusicalMutation {

    private Harmony scale;

    public ScaleMutation(Random randomGenerator, Harmony scale) {
        super(randomGenerator);
        this.scale = scale;
    }

    @Override
    protected void mutateNotes(List<Note> noteList) {
        for (Note note : noteList) {
            if (note instanceof Sound) {
                Sound sound = (Sound) note;
                if (!scale.fit(sound.getPitch())) {
                    List<NoteName> scaleNotes = new ArrayList<>(scale.getComponents());
                    int noteIndex = randomGenerator.nextInt(scaleNotes.size());
                    NoteName newNoteName = scaleNotes.get(noteIndex);
                    try {
                        sound.getPitch().changeNoteName(newNoteName);
                    } catch (IllegalArgumentException e) {
                        if (sound.getPitch().getMidiValue() > Constants.MAX_MIDI_VALUE.value()) {
                            sound.getPitch().changeOctave(Octave.FIFE_LINED);
                        }
                    }
                    break;
                }
            }
        }
    }
}
