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

public class NoteLengthMutationTest {

    private Random randomMock = Mockito.mock(Random.class);

    @Test
    public void testMutateNotes() throws Exception {
        Note[] testData = {new Sound(Pitch.createWithNames(NoteName.C, Octave.CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G_SHARP, Octave.FOUR_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.GREAT), Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(testData);
        NoteLengthMutation mutation = new NoteLengthMutation(randomMock);

        when(randomMock.nextInt(noteList.size())).thenReturn(1, 2);
        mutation.mutateNotes(noteList);

        assertThat(noteList.get(1).getRhythmValue(), is(1.75));
        assertThat(noteList.get(2).getRhythmValue(), is(0.75));
    }
}