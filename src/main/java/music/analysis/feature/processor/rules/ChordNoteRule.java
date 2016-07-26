package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.harmony.Chord;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;

public class ChordNoteRule extends RuleCounter {

    private final double melodyEndTime;
    private final List<Chord> chordProgression;
    private int currentChordIndex;
    private Chord currentChord;
    private double time = 0.0;

    public ChordNoteRule(List<Chord> chordList) {
        this.chordProgression = new ArrayList<>(chordList);
        this.currentChordIndex = 0;
        this.melodyEndTime = chordList.isEmpty() ? 0.0 : chordList.get(chordList.size() - 1).getEndTime();
        this.currentChord = chordProgression.get(currentChordIndex);
    }

    @Override
    public void processNote(Note note) throws IndexOutOfBoundsException {
        try {
            if (currentChord.finishedEarlierThan(time)) {
                nextChord();
            }
            if (note instanceof Sound) {
                analyzeSoundInChordProgression((Sound) note);
            } else {
                time += note.getRhythmValue();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(String.format("Error during analyzing of chord notes statistic. Sequence of chords " +
                            "might be shorter than melody line. Time: %f, rule counter: %f, melody end time: %f. %s",
                    time, ruleCounter, melodyEndTime, e.getMessage()));
        }
    }

    private void analyzeSoundInChordProgression(Sound sound) {
        double noteEnd = time + sound.getRhythmValue();

        if (currentChord.finishedEarlierThan(noteEnd)) {
            calculateWithChangeChords(sound);
        } else {
            updateTimesResults(sound.getPitch(), sound.getRhythmValue());
        }
    }

    private void calculateWithChangeChords(Sound sound) {
        double noteDuration = sound.getRhythmValue();
        while (Double.compare(noteDuration, 0.0) > 0) {
            double availableTimeOnCurrentChord = currentChord.getEndTime() - time;
            if (Double.compare(availableTimeOnCurrentChord, noteDuration) < 0) {
                noteDuration -= availableTimeOnCurrentChord;
                updateTimesResults(sound.getPitch(), availableTimeOnCurrentChord);
            } else {
                updateTimesResults(sound.getPitch(), noteDuration);
                noteDuration = 0.0;
            }
            if ((Double.compare(time, currentChord.getEndTime()) >= 0) && Double.compare(currentChord
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
            ruleCounter += rhythmValue / Durations.SIXTEENTH_NOTE;
        }
        time += rhythmValue;
    }

    @Override
    public void clear() {
        super.clear();
        currentChordIndex = 0;
        currentChord = chordProgression.get(currentChordIndex);
        time = 0.0;
    }
}
