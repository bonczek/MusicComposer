package music.analysis;

import genetic.fitness.function.MusicalFitnessFunction;
import music.analysis.feature.container.RuleContainer;
import music.analysis.feature.container.StatisticContainer;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.feature.processor.factory.FeatureProcessorFactory;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import music.harmony.Harmony;
import music.harmony.ScaleType;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReaderTest {

    private static final Harmony G_MAJOR_SCALE = new Harmony(ScaleType.MAJOR_SCALE, NoteName.G);

    @Test
    public void testAnalyseMidiFile_givenStatisticFeatures() throws Exception {
        String midiTestFile = "yesterday.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        Reader reader = new Reader();
        MusicalFitnessFunction<StatisticContainer> fitnessFunction = new MusicalFitnessFunction<>(prepareStatistics());
        String report = reader.analyseMidiFile(filePath.getPath(), fitnessFunction);

        System.out.println(report);
    }

    @Test
    public void testAnalyseMidiFile_givenRuleFeatures() throws Exception {
        String midiTestFile = "yesterday.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        Reader reader = new Reader();
        MusicalFitnessFunction<RuleContainer> fitnessFunction = new MusicalFitnessFunction<>(prepareRules());
        String report = reader.analyseMidiFile(filePath.getPath(), fitnessFunction);

        System.out.println(report);
    }

    private RuleContainer prepareRules() {
        List<RuleFeature> features = new ArrayList<>();
        List<RuleName> rulesToAnalyze = Arrays.asList(RuleName.values()).stream().filter(rule -> !(rule.equals(RuleName
                .CHORD_NOTE) || rule.equals(RuleName.SCALE_NOTE))).collect(Collectors.toList());
        for (RuleName rule : rulesToAnalyze) {
            DoubleFeatureCounter featureCounter = FeatureProcessorFactory.createRule(rule, G_MAJOR_SCALE,
                    new ArrayList<>());
            features.add(new RuleFeature(rule, 1.0, featureCounter));
        }
        return new RuleContainer(features);
    }

    private StatisticContainer prepareStatistics() {
        List<StatisticalFeature> features = new ArrayList<>();
        for (StatisticName stat : StatisticName.values()) {
            if (stat.equals(StatisticName.CHORD_NOTES) || stat.equals(StatisticName.NON_SCALE_RATING)) {
                continue;
            }
            DoubleFeatureCounter featureCounter = FeatureProcessorFactory.createStatistic(stat, G_MAJOR_SCALE,
                    new ArrayList<>());
            features.add(new StatisticalFeature(stat, 0.5, 10.0, 0.1, featureCounter));
        }
        return new StatisticContainer(features);
    }
}