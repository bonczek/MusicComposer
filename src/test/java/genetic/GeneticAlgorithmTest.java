package genetic;

import genetic.crossover.SimpleCrossover;
import genetic.fitness.FitnessFunction;
import genetic.fitness.rules.ScaleFitness;
import genetic.fitness.towsey.TowseyMusicalFitness;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.SimpleMutation;
import genetic.mutation.TowseyMutation;
import genetic.selection.BinaryTournamentSelection;
import music.Harmony;
import music.Pitch;
import music.Scale;
import org.testng.annotations.Test;

import java.util.Random;

public class GeneticAlgorithmTest {

    private InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(128, 1, new Random());

    private NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
            new SimpleMutation(0.5, new Random()), new SimpleCrossover(0.9, new Random()));

    private FitnessFunction pentatonicFitness = new ScaleFitness(new Harmony(Scale.MINOR_PENTATONIC_SCALE.intervals(), Pitch.A),
            100);

    @Test(enabled = false)
    public void testRun() throws Exception {

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, pentatonicFitness);

        algorithm.run();

    }

    @Test(enabled = false)
    public void testTowseyFitness() throws Exception {
        FitnessFunction fitnessFunction = new TowseyMusicalFitness();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator,
                fitnessFunction);
        geneticAlgorithm.run();
    }

    @Test(enabled = false)
    public void testMutation() throws Exception {
        FitnessFunction fitnessFunction = new TowseyMusicalFitness();
        InitialPopulationGenerator initGenerator = new RandomPopulationGenerator(128, 4, new
                Random());
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                new TowseyMutation(0.5, new Random()), new SimpleCrossover(0.9, new Random()));
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(initGenerator, populationGenerator,
                fitnessFunction);
        geneticAlgorithm.run();
    }
}