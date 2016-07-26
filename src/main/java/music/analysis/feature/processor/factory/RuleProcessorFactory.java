package music.analysis.feature.processor.factory;

import jm.constants.Durations;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.rules.ChordNoteRule;
import music.analysis.feature.processor.rules.MusicalRule;
import music.analysis.feature.processor.rules.NotesRhythmRule;
import music.analysis.feature.processor.rules.OneLinedOctaveGravityRule;
import music.analysis.feature.processor.rules.RestRule;
import music.analysis.feature.processor.rules.ScaleNoteRule;
import music.analysis.feature.processor.rules.StrongBeatRule;
import music.analysis.feature.processor.rules.interval.ConsonancesRule;
import music.analysis.feature.processor.rules.interval.DiatonicIntervalRule;
import music.analysis.feature.processor.rules.interval.LessThanOctaveIntervalRule;
import music.harmony.Chord;
import music.harmony.Harmony;

import java.util.List;

public class RuleProcessorFactory {

    public static MusicalRule createRule(RuleName ruleName, Harmony scale, List<Chord> chordList) {
        switch (ruleName) {
            case CONSONANCES:
                return new ConsonancesRule();
            case HALF_NOTES:
                return new NotesRhythmRule(Durations.HALF_NOTE);
            case EIGHT_NOTES:
                return new NotesRhythmRule(Durations.EIGHTH_NOTE);
            case QUARTER_NOTES:
                return new NotesRhythmRule(Durations.QUARTER_NOTE);
            case SIXTEENTH_NOTES:
                return new NotesRhythmRule(Durations.SIXTEENTH_NOTE);
            case LESS_THAN_OCTAVE:
                return new LessThanOctaveIntervalRule();
            case ONE_LINED_GRAVITY:
                return new OneLinedOctaveGravityRule();
            case SCALE_NOTE:
                return new ScaleNoteRule(scale);
            case DIATONIC_NOTES:
                return new DiatonicIntervalRule();
            case CHORD_NOTES:
                return new ChordNoteRule(chordList);
            case REST_NOTES:
                return new RestRule();
            case STRONG_BEAT:
                return new StrongBeatRule();
            default:
                return null;
        }
    }

}
