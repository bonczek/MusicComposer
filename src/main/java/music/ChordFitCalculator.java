package music;

import music.notes.Note;
import music.notes.Sound;

import java.util.List;

public class ChordFitCalculator {
    private final List<Chord> chordList;
    private final double endTime;
    private int currentChordIndex;
    private Chord currentChord;
    private double time = 0.0;

    public ChordFitCalculator(List<Chord> chordList) {
        this.chordList = chordList;
        this.currentChord = chordList.get(0);
        this.endTime = chordList.get(chordList.size() - 1).getEndTime();
    }

    public double getTime() {
        return time;
    }

    public double calculateTimeFittingHarmony(List<Note> melodyLine) {
        double chordFittingTime = 0.0;
        for (Note note : melodyLine) {
            if (Double.compare(time, currentChord.getEndTime()) >= 0) {
                nextChord();
            }
            if (note instanceof Sound) {
                Sound sound = (Sound) note;
                double timeStep = note.getRhythmValue();
                //@todo double compare
                double noteEnd = time + timeStep;

                if (noteEnd > currentChord.getEndTime()) {
                    double noteDuration = sound.getRhythmValue();
                    while (noteDuration > 0.0) {
                        double diff = currentChord.getEndTime() - time;
                        noteDuration -= diff;
                        if (currentChord.fitHarmony(sound.getPitch())) {
                            chordFittingTime += diff;
                        }
                        if (Double.compare(currentChord.getEndTime(), endTime) != 0) {
                            nextChord();
                        }
                        time += diff;
                    }
                } else {
                    if (currentChord.fitHarmony(sound.getPitch())) {
                        chordFittingTime += sound.getRhythmValue();
                    }
                    time += sound.getRhythmValue();
                }
            } else {
                time += note.getRhythmValue();
            }
        }
        return chordFittingTime;
    }

    public void nextChord() throws IndexOutOfBoundsException {
        currentChordIndex++;
        currentChord = chordList.get(currentChordIndex);
    }
}
