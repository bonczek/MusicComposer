package music.analysis.feature.factory;

import music.Harmony;
import music.Scale;
import music.analysis.feature.name.StatisticName;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatisticProcessorFactoryTest {

    private static final Harmony C_MAJOR_SCALE = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);

    @Test
    public void testCreateStatistic_givenAllStatisticNames() throws Exception {
        for (StatisticName statisticName : StatisticName.values()) {
            assertThat(StatisticProcessorFactory.createStatistic(statisticName, C_MAJOR_SCALE), is(notNullValue()));
        }
    }
}