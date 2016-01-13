package genetic;

import genetic.crossover.SimpleCrossover;
import genetic.fitness.FitnessFunction;
import genetic.fitness.SimpleCMajorFitness;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.SimpleMutation;
import genetic.representation.Note;
import genetic.selection.BinaryTournamentSelection;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GeneticAlgorithmTest {

    private final List<Integer> AVAILABLE_VALUES = Arrays.asList(Note.values()).stream().map(Note::value).collect(Collectors.toList());

    private InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(new Random(), AVAILABLE_VALUES);

    private NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
            new SimpleMutation(0.1, new Random(), AVAILABLE_VALUES), new SimpleCrossover(0.9));

    private FitnessFunction fitnessFunction = new SimpleCMajorFitness();

    @Test
    public void testRun() throws Exception {

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, fitnessFunction);

        algorithm.run();

    }
}