package music.analysis.statistics;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public abstract class StatisticCounterTest<T extends MusicalStatistic> {

    private static final double PRECISION = 0.0001;

    protected T statistic = initStatistic();

    public static List<Note> preparedNoteList() {
        Note[] notes = {
                new Sound(Pitch.createWithNames(NoteName.G, Octave.FOUR_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C, Octave.SUB_CONTRA), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.E, Octave.SUB_CONTRA), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.SUB_CONTRA), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A, Octave.SUB_CONTRA), Durations.EIGHTH_NOTE),
                new Rest(Durations.QUARTER_NOTE),

                new Sound(Pitch.createWithNames(NoteName.G, Octave.FOUR_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C, Octave.SUB_CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),

                new Sound(Pitch.createWithNames(NoteName.G_SHARP, Octave.GREAT), Durations.WHOLE_NOTE),

                new Sound(Pitch.createWithNames(NoteName.F, Octave.THREE_LINED), Durations.WHOLE_NOTE)
        };

        return Arrays.asList(notes);
    }

    protected abstract T initStatistic();

    @Test
    public void testStatisticComputation() throws Exception {
        List<Note> melody = preparedNoteList();
        melody.forEach(statistic::processNote);
        assertEquals(statistic.getResult(), getExpectedResult(), PRECISION);
        statistic.clear();
        afterClearAsserts();
    }

    protected abstract double getExpectedResult();

    protected abstract void afterClearAsserts() throws Exception;

}
