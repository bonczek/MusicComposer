package music.analysis.feature.processor.factory;

import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.rules.ConsonancesRule;
import music.analysis.feature.processor.rules.LongNotesRule;
import music.analysis.feature.processor.rules.MusicalRule;

public class RuleProcessorFactory {

    public static MusicalRule createRule(RuleName ruleName) {
        switch (ruleName) {
            case CONSONANCES:
                return new ConsonancesRule();
            case LONG_NOTES:
                return new LongNotesRule();
            default:
                return null;
        }
    }

}
