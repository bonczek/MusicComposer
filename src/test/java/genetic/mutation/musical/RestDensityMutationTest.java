package genetic.mutation.musical;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class RestDensityMutationTest {

    private Random randomMock = Mockito.mock(Random.class);

    @Test
    public void testMutateNotes_givenSoundIsChosen() throws Exception {
        int mutatedNoteIndex = 2;
        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G_SHARP, Octave.FOUR_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);

        RestDensityMutation mutation = new RestDensityMutation(randomMock);
        when(randomMock.nextInt(noteList.size())).thenReturn(mutatedNoteIndex);
        mutation.mutateNotes(noteList);

        assertThat(noteList.get(mutatedNoteIndex) instanceof Rest, is(true));
        assertThat(noteList.get(mutatedNoteIndex).getRhythmValue(), is(Durations.EIGHTH_NOTE));
    }

    @Test
    public void testMutateNotes_givenRestIsChosen() throws Exception {
        int mutatedNoteIndex = 1;
        Pitch randomPitch = Pitch.createWithMidi(35);
        int numberOfMidiValues = 128;

        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G_SHARP, Octave.FOUR_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);

        RestDensityMutation mutation = new RestDensityMutation(randomMock);
        when(randomMock.nextInt(noteList.size())).thenReturn(mutatedNoteIndex);
        when(randomMock.nextInt(numberOfMidiValues)).thenReturn(randomPitch.getMidiValue());
        mutation.mutateNotes(noteList);

        assertThat(noteList.get(mutatedNoteIndex) instanceof Sound, is(true));
        assertThat(noteList.get(mutatedNoteIndex).getRhythmValue(), is(Durations.HALF_NOTE));
        Sound mutatedSound = (Sound) noteList.get(mutatedNoteIndex);
        assertThat(mutatedSound.getPitch().equals(randomPitch), is(true));
    }
}