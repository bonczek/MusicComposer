package genetic.mutation.musical;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import music.notes.pitch.PitchInterval;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class DissonantIntervalsMutationTest {

    private final List<PitchInterval> pitchIntervals = new ArrayList<>(Arrays.asList(PitchInterval.values()));
    private Random randomMock = Mockito.mock(Random.class);

    @Test
    public void testMutateNotes() throws Exception {
        Note[] notes = {new Sound(Pitch.createWithNames(NoteName.G, Octave.GREAT), Durations.SIXTEENTH_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.CONTRA), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.FOUR_LINED), Durations.SIXTEENTH_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        DissonantIntervalsMutation mutation = new DissonantIntervalsMutation(randomMock);
        when(randomMock.nextInt(3 - 1)).thenReturn(1);
        when(randomMock.nextInt(pitchIntervals.size())).thenReturn(PitchInterval.PERFECT_FIFTH.semitones());
        when(randomMock.nextBoolean()).thenReturn(true);

        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(3);
        assertThat(mutatedSound.getPitch().getNoteName(), is(NoteName.A));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.CONTRA));
    }

    @Test
    public void testMutateNotes_givenFirstOctavePitch() throws Exception {
        Note[] notes = {new Sound(Pitch.createWithNames(NoteName.G, Octave.SUBSUBCONTRA), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.CONTRA), Durations.SIXTEENTH_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        DissonantIntervalsMutation mutation = new DissonantIntervalsMutation(randomMock);
        when(randomMock.nextInt(2 - 1)).thenReturn(0);
        when(randomMock.nextInt(pitchIntervals.size())).thenReturn(PitchInterval.MAJOR_THIRD.semitones());
        when(randomMock.nextBoolean()).thenReturn(false);

        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(1);
        assertThat(mutatedSound.getPitch().getNoteName(), is(NoteName.B));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.SUBSUBCONTRA));
    }

    @Test
    public void testMutateNotes_givenLastOctavePitch() throws Exception {
        Note[] notes = {new Sound(Pitch.createWithNames(NoteName.A, Octave.FIFE_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.CONTRA), Durations.SIXTEENTH_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        DissonantIntervalsMutation mutation = new DissonantIntervalsMutation(randomMock);
        when(randomMock.nextInt(2 - 1)).thenReturn(0);
        when(randomMock.nextInt(pitchIntervals.size())).thenReturn(PitchInterval.MAJOR_THIRD.semitones());
        when(randomMock.nextBoolean()).thenReturn(true);

        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(1);
        assertThat(mutatedSound.getPitch().getNoteName(), is(NoteName.F));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.FIFE_LINED));
    }
}