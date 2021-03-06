package genetic.mutation.musical.sound;

import music.notes.Sound;
import music.notes.pitch.Pitch;
import music.notes.pitch.PitchInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IntervalMutation extends SoundMutation {

    private static final int MAX_SAFE_MIDI_VALUE = Pitch.MAX_MIDI_VALUE - PitchInterval.MAJOR_SEVENTH.semitones();
    private static final int MIN_SAFE_MIDI_VALUE = Pitch.MIN_MIDI_VALUE + PitchInterval.MAJOR_SEVENTH.semitones();
    protected final List<PitchInterval> pitchIntervals = new ArrayList<>(Arrays.asList(PitchInterval.values()));

    public IntervalMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    /**
     * Replace second of randomly selected adjacent sound, so they can form new random interval.
     * 1. Looking for adjacent two sounds in melody line.
     * 2. Randomly select new interval
     * 3. Create new pitch, so it will be differ from first sound by selected interval (up or down)
     * 4. Set new pitch for second sound.
     *
     * @param soundList sounds in melody line to mutate
     */
    @Override
    protected void mutateSounds(List<Sound> soundList) {
        if (soundList.size() < 2) {
            return;
        }

        int index = randomGenerator.nextInt(soundList.size() - 1);
        Sound firstSound = soundList.get(index);
        Sound nextSound = soundList.get(index + 1);

        int intervalIndex = randomGenerator.nextInt(pitchIntervals.size());
        PitchInterval newInterval = pitchIntervals.get(intervalIndex);

        try {
            Pitch newPitch = preparePitchWithNewInterval(firstSound.getPitch(), newInterval);
            nextSound.setPitch(newPitch);
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Dissonant intervals mutation failed. Cannot set new pitch from given: " +
                    "%d and %s interval.", firstSound.getPitch().getMidiValue(), newInterval));
        }
    }

    /**
     * Generate new pitch using given base pitch and interval.
     * Generally interval direction is randomly selected, but for safety,
     * in case of values near MIDI limits direction is chosen.
     * This simplification is used, because sounds in highest and lowest octaves are hard to listen.
     *
     * @param basePitch   previous pitch since interval will be counted
     * @param newInterval new relation between base pitch and resulting pitch
     * @return pitch with specified interval/relation to base pitch
     * @throws IllegalArgumentException in case of exceed MIDI limit for note value
     */
    protected Pitch preparePitchWithNewInterval(Pitch basePitch, PitchInterval newInterval) throws
            IllegalArgumentException {
        if (basePitch.getMidiValue() >= MAX_SAFE_MIDI_VALUE) {
            return Pitch.createWithDownInterval(basePitch, newInterval);
        } else if (basePitch.getMidiValue() <= MIN_SAFE_MIDI_VALUE) {
            return Pitch.createWithUpInterval(basePitch, newInterval);
        } else {
            return randomUpOrDownInterval(basePitch, newInterval);
        }
    }

    protected Pitch randomUpOrDownInterval(Pitch basePitch, PitchInterval newInterval) throws IllegalArgumentException {
        if (randomGenerator.nextBoolean()) {
            return Pitch.createWithUpInterval(basePitch, newInterval);
        } else {
            return Pitch.createWithDownInterval(basePitch, newInterval);
        }
    }
}
