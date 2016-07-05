package music.analysis.feature.processor.factory;

import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.rules.MelodicIntervalRule;
import music.analysis.feature.processor.rules.MusicalRule;
import music.notes.pitch.Interval;

public class RuleProcessorFactory {

    public static MusicalRule createRule(RuleName ruleName) {
        switch (ruleName) {
            case PERFECT_CONSONANCE:
                return new MelodicIntervalRule(Interval::perfectConsonance);
            case IMPERFECT_CONSONANCE:
                return new MelodicIntervalRule(Interval::imperfectConsonance);
            case DISSONANCE:
                return new MelodicIntervalRule(Interval::dissonance);
            case LESS_THAN_OCTAVE:
                return new MelodicIntervalRule(i -> !i.moreThanOctave());
            default:
                return null;
        }
    }

}
