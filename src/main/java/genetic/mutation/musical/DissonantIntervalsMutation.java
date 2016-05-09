package genetic.mutation.musical;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import music.notes.pitch.PitchInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DissonantIntervalsMutation extends MusicalMutation {

    private static final int MAX_SAFE_MIDI_VALUE = Pitch.MAX_MIDI_VALUE - PitchInterval.MAJOR_SEVENTH.semitones();
    private static final int MIN_SAFE_MIDI_VALUE = Pitch.MIN_MIDI_VALUE + PitchInterval.MAJOR_SEVENTH.semitones();
    private final List<PitchInterval> pitchIntervals = new ArrayList<>(Arrays.asList(PitchInterval.values()));

    public DissonantIntervalsMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    protected void mutateNotes(List<Note> noteList) {
        List<Sound> soundList = collectSounds(noteList);
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

    private List<Sound> collectSounds(List<Note> noteList) {
        List<Sound> soundList = new ArrayList<>();
        noteList.stream().filter(note -> note instanceof Sound).forEach(note -> {
            Sound sound = (Sound) note;
            soundList.add(sound);
        });
        return soundList;
    }

    private Pitch preparePitchWithNewInterval(Pitch basePitch, PitchInterval newInterval) throws
            IllegalArgumentException {
        if (basePitch.getMidiValue() >= MAX_SAFE_MIDI_VALUE) {
            return Pitch.createWithDownInterval(basePitch, newInterval);
        } else if (basePitch.getMidiValue() <= MIN_SAFE_MIDI_VALUE) {
            return Pitch.createWithUpInterval(basePitch, newInterval);
        } else {
            return randomUpOrDownInterval(basePitch, newInterval);
        }
    }

    private Pitch randomUpOrDownInterval(Pitch basePitch, PitchInterval newInterval) throws IllegalArgumentException {
        if (randomGenerator.nextBoolean()) {
            return Pitch.createWithUpInterval(basePitch, newInterval);
        } else {
            return Pitch.createWithDownInterval(basePitch, newInterval);
        }
    }
}
