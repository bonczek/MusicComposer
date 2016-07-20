package music.analysis.feature.processor.factory;

import jm.constants.Durations;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.rules.ConsonancesRule;
import music.analysis.feature.processor.rules.LessThanOctaveIntervalRule;
import music.analysis.feature.processor.rules.MiddleCGravityRule;
import music.analysis.feature.processor.rules.MusicalRule;
import music.analysis.feature.processor.rules.NotesRhythmRule;

public class RuleProcessorFactory {

    public static MusicalRule createRule(RuleName ruleName) {
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
            case MIDDLE_C_GRAVITY:
                return new MiddleCGravityRule();
            default:
                return null;
        }
    }

}
