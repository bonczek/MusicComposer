package genetic.mutation;

import genetic.representation.Constants;
import music.Harmony;
import music.Note;
import music.NoteName;
import music.Octave;
import music.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScaleMutation extends MusicalMutation {

    private Harmony scale;

    public ScaleMutation(double mutationRate, Random randomGenerator, Harmony scale) {
        super(mutationRate, randomGenerator);
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
