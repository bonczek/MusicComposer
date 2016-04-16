package genetic.mutation;

import genetic.representation.Constants;
import music.Harmony;
import music.Note;
import music.Octave;
import music.Pitch;
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
                if (!scale.fit(sound)) {
                    List<Pitch> scaleNotes = new ArrayList<>(scale.getComponents());
                    int noteIndex = randomGenerator.nextInt(scaleNotes.size());
                    Pitch newPitch = scaleNotes.get(noteIndex);
                    try {
                        sound.setPitch(newPitch);
                    } catch (IllegalArgumentException e) {
                        if (sound.getMidiValue() > Constants.MAX_MIDI_VALUE.value()) {
                            sound.setOctave(Octave.FIFE_LINED);
                        }
                    }
                    break;
                }
            }
        }
    }
}
