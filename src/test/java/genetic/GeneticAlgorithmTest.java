package genetic;

import genetic.crossover.CrossoverCoordinator;
import genetic.crossover.SimpleCrossover;
import genetic.fitness.function.FitnessFunction;
import genetic.fitness.function.MusicalFitnessFunction;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.GeneticMutation;
import genetic.mutation.MutationCoordinator;
import genetic.mutation.SimpleMutation;
import genetic.mutation.TowseyMutation;
import genetic.selection.BinaryTournamentSelection;
import music.analysis.feature.container.StatisticContainer;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.feature.processor.factory.FeatureProcessorFactory;
import music.analysis.feature.type.StatisticalFeature;
import music.analysis.util.ChordProgressionData;
import music.harmony.Harmony;
import music.harmony.ScaleType;
import music.harmony.chords.Chord;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithmTest {

    private InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(8, 1, new Random());

    private NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
            new MutationCoordinator(new GeneticGuard(0.5), new SimpleMutation(new Random())),
            new CrossoverCoordinator(new GeneticGuard(0.9), new SimpleCrossover(new Random())));

    @Test(enabled = false)
    public void testMutation() throws Exception {
        int numbersOfMeasures = 4;
        int numberOfIterations = 100;
        Harmony cMajorScale = new Harmony(ScaleType.MAJOR_SCALE, NoteName.C);
        List<StatisticalFeature> features = new ArrayList<>();
        List<Chord> chords = ChordProgressionData.prepareFourMeasuresGAndCMajor();
        for (StatisticName stat : StatisticName.values()) {
            DoubleFeatureCounter featureCounter = FeatureProcessorFactory.createStatistic(stat, cMajorScale, chords);
            features.add(new StatisticalFeature(stat, 0.5, 10.0, 0.1, featureCounter));
        }
        FitnessFunction fitnessFunction = new MusicalFitnessFunction<>(new StatisticContainer(features));

        InitialPopulationGenerator initGenerator = new RandomPopulationGenerator(128, numbersOfMeasures, new
                Random());
        GeneticMutation mutation = new TowseyMutation(new Random(), cMajorScale);
        MutationCoordinator mutationCoordinator = new MutationCoordinator(new GeneticGuard(0.3), mutation);
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                mutationCoordinator, new CrossoverCoordinator(new GeneticGuard(0.9), new SimpleCrossover(new Random()
        )));
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(initGenerator, populationGenerator,
                fitnessFunction, numberOfIterations);
        geneticAlgorithm.run();
    }
}