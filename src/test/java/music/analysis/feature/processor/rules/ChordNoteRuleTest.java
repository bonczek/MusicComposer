package music.analysis.feature.processor.rules;

import music.analysis.feature.processor.statistics.density.ChordNotesDensityStatistic;
import music.analysis.util.ChordProgressionData;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChordNoteRuleTest extends RuleCounterTest<ChordNoteRule> {

    @Override
    protected ChordNoteRule initFeatureCounter() {
        return new ChordNoteRule(ChordProgressionData.prepareFourMeasuresGAndCMajor());
    }

    @Override
    protected double getExpectedResult() {
        return 13.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        super.afterClearAsserts();
        ChordNotesDensityStatistic statistic = featureCounter.getChordNoteDensityStatistic();
        assertThat(statistic.getNumerator(), is(0.0));
        assertThat(statistic.getDenominator(), is(0.0));
    }

}