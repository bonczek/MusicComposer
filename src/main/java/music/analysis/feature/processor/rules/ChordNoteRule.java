package music.analysis.feature.processor.rules;

import jm.constants.Durations;
import music.analysis.feature.processor.statistics.density.ChordNotesDensityStatistic;
import music.harmony.Chord;
import music.notes.Note;

import java.util.List;

public class ChordNoteRule extends RuleCounter {

    private final ChordNotesDensityStatistic chordNoteDensityStatistic;

    public ChordNoteRule(List<Chord> chordList) {
        this.chordNoteDensityStatistic = new ChordNotesDensityStatistic(chordList);
    }

    public ChordNotesDensityStatistic getChordNoteDensityStatistic() {
        return chordNoteDensityStatistic;
    }

    @Override
    public void processNote(Note note) throws IndexOutOfBoundsException {
        chordNoteDensityStatistic.processNote(note);
        ruleCounter = chordNoteDensityStatistic.getNumerator() / Durations.SIXTEENTH_NOTE;
    }

    @Override
    public void clear() {
        super.clear();
        chordNoteDensityStatistic.clear();
    }
}
