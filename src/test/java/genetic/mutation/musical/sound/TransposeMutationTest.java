package genetic.mutation.musical.sound;

import music.analysis.util.MelodyData;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.PitchInterval;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class TransposeMutationTest {

    private Random randomMock = Mockito.mock(Random.class);

    @Test
    public void testMutateSounds() throws Exception {
        int soundMutationIndex = 1;
        int transposeIntervalIndex = 4;
        List<Note> noteList = MelodyData.prepareOneMeasureCMaj7Chord();
        List<PitchInterval> pitchIntervals = Arrays.asList(PitchInterval.values());
        int soundNumbers = noteList.size();

        when(randomMock.nextInt(soundNumbers)).thenReturn(soundMutationIndex);
        when(randomMock.nextInt(pitchIntervals.size())).thenReturn(transposeIntervalIndex);
        when(randomMock.nextBoolean()).thenReturn(true);

        TransposeMutation mutation = new TransposeMutation(randomMock);
        mutation.mutateNotes(noteList);

        Sound mutatedSound = (Sound) noteList.get(soundMutationIndex);
        assertThat(mutatedSound.getPitch().getNoteName(), is(NoteName.G_SHARP));
        assertThat(mutatedSound.getPitch().getOctave(), is(Octave.THREE_LINED));
    }
}