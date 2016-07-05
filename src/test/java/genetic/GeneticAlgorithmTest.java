package genetic;

import genetic.crossover.CrossoverCoordinator;
import genetic.crossover.SimpleCrossover;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.MutationCoordinator;
import genetic.mutation.SimpleMutation;
import genetic.selection.BinaryTournamentSelection;
import org.testng.annotations.Test;

import java.util.Random;

public class GeneticAlgorithmTest {

    private InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(8, 1, new Random());

    private NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
            new MutationCoordinator(new GeneticGuard(0.5), new SimpleMutation(new Random())),
            new CrossoverCoordinator(new GeneticGuard(0.9), new SimpleCrossover(new Random())));

    @Test(enabled = false)
    public void testMutation() throws Exception {
//        int numbersOfMeasures = 4;
//        Harmony cMajorScale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
//        FitnessFunction fitnessFunction = new MusicalStatisticsFitness(cMajorScale);
//        InitialPopulationGenerator initGenerator = new RandomPopulationGenerator(128, numbersOfMeasures, new
//                Random());
//        GeneticMutation mutation = new TowseyMutation(new Random(), cMajorScale);
//        MutationCoordinator mutationCoordinator = new MutationCoordinator(new GeneticGuard(0.3), mutation);
//        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
//                mutationCoordinator, new CrossoverCoordinator(new GeneticGuard(0.9), new SimpleCrossover(new Random()
//        )));
//        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(initGenerator, populationGenerator,
//                fitnessFunction);
//        geneticAlgorithm.run();
    }
}