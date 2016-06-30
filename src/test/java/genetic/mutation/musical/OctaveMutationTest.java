package genetic.mutation.musical;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class OctaveMutationTest {

    private Random randomMock = Mockito.mock(Random.class);

    @Test
    public void testMutateNotes_givenUpChange() throws Exception {
        int soundNumbers = 3;
        int soundMutationIndex = 1;
        int noteMutationIndex = 2;
        NoteName mutatetNoteName = NoteName.G;

        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(mutatetNoteName, Octave.GREAT), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);
        OctaveMutation mutation = new OctaveMutation(randomMock);

        when(randomMock.nextInt(soundNumbers)).thenReturn(soundMutationIndex);
        when(randomMock.nextBoolean()).thenReturn(true);

        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(noteMutationIndex);
        assertThat(mutatedSound.getPitch().getNoteName(), is(mutatetNoteName));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.SMALL));
        assertThat(mutatedSound.getPitch().getMidiValue(), is(55));
    }

    @Test
    public void testMutateNotes_givenDownChange() throws Exception {
        int soundNumbers = 3;
        int soundMutationIndex = 1;
        int noteMutationIndex = 2;
        NoteName mutatetNoteName = NoteName.G;

        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(mutatetNoteName, Octave.GREAT), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);
        OctaveMutation mutation = new OctaveMutation(randomMock);

        when(randomMock.nextInt(soundNumbers)).thenReturn(soundMutationIndex);
        when(randomMock.nextBoolean()).thenReturn(false);

        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(noteMutationIndex);
        assertThat(mutatedSound.getPitch().getNoteName(), is(mutatetNoteName));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.CONTRA));
        assertThat(mutatedSound.getPitch().getMidiValue(), is(31));
    }

    @Test
    public void testMutateNotes_givenNoteInHighestOctave() throws Exception {
        int soundNumbers = 3;
        int soundMutationIndex = 1;
        int noteMutationIndex = 2;
        NoteName mutatetNoteName = NoteName.G;

        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(mutatetNoteName, Octave.SIX_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);
        OctaveMutation mutation = new OctaveMutation(randomMock);

        when(randomMock.nextInt(soundNumbers)).thenReturn(soundMutationIndex);

        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(noteMutationIndex);
        assertThat(mutatedSound.getPitch().getNoteName(), is(mutatetNoteName));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.FIFE_LINED));
        assertThat(mutatedSound.getPitch().getMidiValue(), is(115));
    }

    @Test
    public void testMutateNotes_givenNoteInLowestOctave() throws Exception {
        int soundNumbers = 3;
        int soundMutationIndex = 1;
        int noteMutationIndex = 2;
        NoteName mutatetNoteName = NoteName.G;

        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(mutatetNoteName, Octave.SUBSUBCONTRA), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);
        OctaveMutation mutation = new OctaveMutation(randomMock);

        when(randomMock.nextInt(soundNumbers)).thenReturn(soundMutationIndex);

        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(noteMutationIndex);
        assertThat(mutatedSound.getPitch().getNoteName(), is(mutatetNoteName));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.SUB_CONTRA));
        assertThat(mutatedSound.getPitch().getMidiValue(), is(19));
    }
}