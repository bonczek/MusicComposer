package genetic.mutation.musical.sound;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.Harmony;
import music.Scale;
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

public class ScaleMutationTest {

    private Random randomMock = Mockito.mock(Random.class);

    @Test
    public void testMutateNotes() throws Exception {
        int soundNumbers = 3;
        int soundMutationIndex = 1;
        int noteMutationIndex = 2;
        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G_SHARP, Octave.FOUR_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);
        Harmony cMajorScale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
        ScaleMutation scaleMutation = new ScaleMutation(randomMock, cMajorScale);

        when(randomMock.nextInt(soundNumbers)).thenReturn(soundMutationIndex);
        when(randomMock.nextInt(cMajorScale.getComponents().size())).thenReturn(4);

        scaleMutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(noteMutationIndex);
        assertThat(mutatedSound.getPitch().getNoteName(), is(NoteName.G));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.FOUR_LINED));
        assertThat(mutatedSound.getPitch().getMidiValue(), is(103));

    }

    @Test
    public void testMutateNotes_givenOutOfRangeValue() throws Exception {
        int soundNumbers = 3;
        int soundMutationIndex = 1;
        int noteMutationIndex = 2;
        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G, Octave.SIX_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);
        Harmony cMajorScale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
        ScaleMutation scaleMutation = new ScaleMutation(randomMock, cMajorScale);

        when(randomMock.nextInt(soundNumbers)).thenReturn(soundMutationIndex);
        when(randomMock.nextInt(cMajorScale.getComponents().size())).thenReturn(6);

        scaleMutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(noteMutationIndex);
        assertThat(mutatedSound.getPitch().getNoteName(), is(NoteName.B));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.FIFE_LINED));
        assertThat(mutatedSound.getPitch().getMidiValue(), is(119));
    }
}