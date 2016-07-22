package music.analysis.feature.processor.factory;

import music.analysis.feature.name.StatisticName;
import music.analysis.util.ChordProgressionData;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatisticProcessorFactoryTest {

    private static final Harmony C_MAJOR_SCALE = new Harmony(ScaleName.MAJOR_SCALE, NoteName.C);

    @Test
    public void testCreateStatistic_givenAllStatisticNames() throws Exception {
        for (StatisticName statisticName : StatisticName.values()) {
            assertThat(StatisticProcessorFactory.createStatistic(statisticName, C_MAJOR_SCALE,
                    ChordProgressionData.prepareFourMeasuresGAndCMajor(), 4), is(notNullValue()));
        }
    }
}