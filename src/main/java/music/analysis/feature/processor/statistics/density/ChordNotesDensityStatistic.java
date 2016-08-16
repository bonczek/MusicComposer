package music.analysis.feature.processor.statistics.density;


import music.harmony.Chord;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;

public class ChordNotesDensityStatistic extends RhythmDensityStatistic {

    private final double melodyEndTime;
    private final List<Chord> chordProgression;
    private int currentChordIndex;
    private Chord currentChord;

    public ChordNotesDensityStatistic(List<Chord> chordList) {
        this.chordProgression = new ArrayList<>(chordList);
        this.melodyEndTime = chordList.isEmpty() ? 0.0 : chordList.get(chordList.size() - 1).getEndTime();
        this.currentChordIndex = 0;
        this.currentChord = chordProgression.get(currentChordIndex);
    }

    @Override
    public void processNote(Note note) throws IndexOutOfBoundsException {
        //@todo very complicated statistic - it should be refactored in future
        try {
            if (currentChord.finishedEarlierThan(denominator)) {
                nextChord();
            }
            if (note instanceof Sound) {
                analyzeSoundInChordProgression((Sound) note);
            } else {
                denominator += note.getRhythmValue();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(String.format("Error during analyzing of chord notes statistic. Sequence of chords " +
                            "might be shorter than melody line. Numerator: %f, denominator: %f, melody end time: %f. %s",
                    numerator, denominator, melodyEndTime, e.getMessage()));
        }
    }

    private void analyzeSoundInChordProgression(Sound sound) {
        double noteEnd = denominator + sound.getRhythmValue();

        if (currentChord.finishedEarlierThan(noteEnd)) {
            calculateWithChangeChords(sound);
        } else {
            updateTimesResults(sound.getPitch(), sound.getRhythmValue());
        }
    }

    private void calculateWithChangeChords(Sound sound) {
        double noteDuration = sound.getRhythmValue();
        while (Double.compare(noteDuration, 0.0) > 0) {
            double availableTimeOnCurrentChord = currentChord.getEndTime() - denominator;
            if (Double.compare(availableTimeOnCurrentChord, 0.0) != 0 &&
                    Double.compare(availableTimeOnCurrentChord, noteDuration) < 0) {
                noteDuration -= availableTimeOnCurrentChord;
                updateTimesResults(sound.getPitch(), availableTimeOnCurrentChord);
            } else {
                updateTimesResults(sound.getPitch(), noteDuration);
                noteDuration = 0.0;
            }
            if ((Double.compare(denominator, currentChord.getEndTime()) >= 0) && Double.compare(currentChord
                    .getEndTime(), melodyEndTime) != 0) {
                nextChord();
            }
        }
    }

    private void nextChord() throws IndexOutOfBoundsException {
        currentChordIndex++;
        currentChord = chordProgression.get(currentChordIndex);
    }

    private void updateTimesResults(Pitch pitch, double rhythmValue) {
        if (currentChord.fitHarmony(pitch)) {
            numerator += rhythmValue;
        }
        denominator += rhythmValue;
    }

    @Override
    public void clear() {
        super.clear();
        currentChordIndex = 0;
        currentChord = chordProgression.get(currentChordIndex);
    }
}
