package genetic;

import genetic.crossover.SimpleCrossover;
import genetic.fitness.FitnessFunction;
import genetic.fitness.rules.ScaleFitness;
import genetic.fitness.towsey.TowseyMusicalFitness;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.GeneticMutation;
import genetic.mutation.MutationCoordinator;
import genetic.mutation.SimpleMutation;
import genetic.mutation.TowseyMutation;
import genetic.selection.BinaryTournamentSelection;
import music.Harmony;
import music.Scale;
import music.notes.pitch.NoteName;
import org.testng.annotations.Test;

import java.util.Random;

public class GeneticAlgorithmTest {

    private InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(8, 1, new Random());

    private NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
            new MutationCoordinator(new GeneticGuard(0.5), new SimpleMutation(new Random())),
            new SimpleCrossover(0.9, new Random()));

    private FitnessFunction pentatonicFitness = new ScaleFitness(new Harmony(Scale.MINOR_PENTATONIC_SCALE.intervals(), NoteName.A),
            100);

    @Test(enabled = false)
    public void testRun() throws Exception {

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, pentatonicFitness);

        algorithm.run();

    }


    @Test(enabled = false)
    public void testMutation() throws Exception {
        int numbersOfMeasures = 4;
        Harmony cMajorScale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
        FitnessFunction fitnessFunction = new TowseyMusicalFitness(cMajorScale, numbersOfMeasures);
        InitialPopulationGenerator initGenerator = new RandomPopulationGenerator(128, numbersOfMeasures, new
                Random());
        GeneticMutation mutation = new TowseyMutation(new Random(), cMajorScale);
        MutationCoordinator mutationCoordinator = new MutationCoordinator(new GeneticGuard(0.3), mutation);
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                mutationCoordinator, new SimpleCrossover(0.9, new Random()));
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(initGenerator, populationGenerator,
                fitnessFunction);
        geneticAlgorithm.run();
    }
}