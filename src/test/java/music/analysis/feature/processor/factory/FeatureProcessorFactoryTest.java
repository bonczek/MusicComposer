package music.analysis.feature.processor.factory;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.util.ChordProgressionData;
import music.harmony.Harmony;
import music.harmony.ScaleType;
import music.harmony.chords.Chord;
import music.harmony.chords.ChordType;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeatureProcessorFactoryTest {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleType.MAJOR_SCALE, NoteName.C);

    @Test
    public void testCreateStatistic_givenAllStatisticNames() throws Exception {
        for (StatisticName statisticName : StatisticName.values()) {
            assertThat(FeatureProcessorFactory.createStatistic(statisticName, C_MAJOR_SCALE,
                    ChordProgressionData.prepareFourMeasuresGAndCMajor()), is(notNullValue()));
        }
    }

    @Test
    public void testCreateRule() throws Exception {
        Chord[] chords = {new Chord(new Harmony(ChordType.MAJOR, NoteName.C), 0.0, Durations.WHOLE_NOTE)};
        for (RuleName ruleName : RuleName.values()) {
            assertThat(FeatureProcessorFactory.createRule(ruleName, C_MAJOR_SCALE, Arrays.asList(chords)),
                    is(notNullValue()));
        }
    }
}