package genetic.mutation.musical.sound;

import music.notes.Sound;
import music.notes.pitch.Pitch;
import music.notes.pitch.PitchInterval;

import java.util.List;
import java.util.Random;

public class TransposeMutation extends IntervalMutation {

    public TransposeMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    protected void mutateSounds(List<Sound> soundList) {
        int index = randomGenerator.nextInt(soundList.size());
        Sound randomSound = soundList.get(index);

        int intervalIndex = randomGenerator.nextInt(pitchIntervals.size());
        PitchInterval intervalToTranspose = pitchIntervals.get(intervalIndex);

        try {
            Pitch newPitch = preparePitchWithNewInterval(randomSound.getPitch(), intervalToTranspose);
            randomSound.setPitch(newPitch);
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Transpose mutation failed. Cannot set new pitch from given: " +
                    "%d and %s interval.", randomSound.getPitch().getMidiValue(), intervalToTranspose));
        }
    }
}
