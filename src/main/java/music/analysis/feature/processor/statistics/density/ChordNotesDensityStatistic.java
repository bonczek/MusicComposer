package music.analysis.feature.processor.statistics.density;


import music.harmony.Chord;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ChordNotesDensityStatistic extends RhythmDensityStatistic {

    private final double melodyEndTime;
    private final Queue<Chord> chordProgression;
    private Chord currentChord;

    public ChordNotesDensityStatistic(List<Chord> chordList) {
        this.chordProgression = new LinkedList<>(chordList);
        this.melodyEndTime = chordList.isEmpty() ? 0.0 : chordList.get(chordList.size() - 1).getEndTime();
        this.currentChord = chordProgression.poll();
    }

    @Override
    public void processNote(Note note) {
        try {
            if (currentChord.finishedEarlierThan(denominator)) {
                nextChord();
            }
            if (note instanceof Sound) {
                analyzeSoundInChordProgression((Sound) note);
            } else {
                denominator += note.getRhythmValue();
            }
        } catch (NoSuchElementException e) {
            System.out.println(String.format("Error during analyzing of chord notes statistic. Sequence of chords " +
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
            double timeOnCurrentChord = currentChord.getEndTime() - denominator;
            noteDuration -= timeOnCurrentChord;
            updateTimesResults(sound.getPitch(), timeOnCurrentChord);
            if (Double.compare(currentChord.getEndTime(), melodyEndTime) != 0) {
                nextChord();
            }
        }
    }

    private void nextChord() throws NoSuchElementException {
        currentChord = chordProgression.remove();
    }

    private void updateTimesResults(Pitch pitch, double rhythmValue) {
        if (currentChord.fitHarmony(pitch)) {
            numerator += rhythmValue;
        }
        denominator += rhythmValue;
    }
}
