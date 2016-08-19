package music.analysis.feature.processor.rules;

import music.harmony.Harmony;
import music.harmony.ScaleType;
import music.notes.pitch.NoteName;

public class ScaleNoteRuleTest extends RuleCounterTest<ScaleNoteRule> {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleType.MAJOR_SCALE, NoteName.C);

    @Override
    protected ScaleNoteRule initFeatureCounter() {
        return new ScaleNoteRule(C_MAJOR_SCALE);
    }

    @Override
    protected double getExpectedResult() {
        return 36.0;
    }
}