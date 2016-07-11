package music.harmony;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ChordFitCalculator {

    //@todo empty list, exception handling

    private final double melodyEndTime;
    private final Queue<Chord> chordProgression;
    private Chord currentChord;
    private double time = 0.0;
    private double chordFittingTime = 0.0;

    public ChordFitCalculator(List<Chord> chordList) {
        this.chordProgression = new LinkedList<>(chordList);
        this.melodyEndTime = chordList.get(chordList.size() - 1).getEndTime();
        this.currentChord = chordProgression.poll();
    }

    public double getTime() {
        return time;
    }

    public double calculateTimeFittingHarmony(List<Note> melodyLine) {
        for (Note note : melodyLine) {
            if (currentChord.finishedEarlierThan(time)) {
                nextChord();
            }
            if (note instanceof Sound) {
                analyzeSoundInChordProgression((Sound) note);
            } else {
                time += note.getRhythmValue();
            }
        }
        return chordFittingTime;
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
            double timeOnCurrentChord = currentChord.getEndTime() - time;
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
            chordFittingTime += rhythmValue;
        }
        time += rhythmValue;
    }



}
