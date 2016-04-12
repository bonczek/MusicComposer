package genetic;

import genetic.fitness.FitnessFunction;
import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.util.Converter;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.View;

import java.util.List;
import java.util.Optional;

public class GeneticAlgorithm {

    private InitialPopulationGenerator initialGenerator;

    private NewPopulationGenerator populationGenerator;

    private FitnessFunction fitnessFunction;

    private Niche niche = new Niche();

    public GeneticAlgorithm(InitialPopulationGenerator initialGenerator, NewPopulationGenerator populationGenerator, FitnessFunction fitnessFunction) {
        this.initialGenerator = initialGenerator;
        this.populationGenerator = populationGenerator;
        this.fitnessFunction = fitnessFunction;
    }

    public void run() {
        //initial population
        List<Chromosome> population = initialGenerator.generatePopulation();

        int iteration = 0;
        while (nextPopulation(iteration)) {
            fitnessFunction.calculateFitness(population);
            niche.decreaseFitnessValueForSimilar(population);
            population = populationGenerator.generateNewPopulation(population);
            iteration++;
        }

        fitnessFunction.calculateFitness(population);
        population.forEach(c -> System.out.println(String.format("%s: %d", c.toString(), c.getFitness())));
        humanReadable(population);
        Optional<Chromosome> theBestChromosome = population.stream().max((a, b) -> a.getFitness().compareTo(b.getFitness()));
        if (theBestChromosome.isPresent()) {
            System.out.println("*****THE BEST CHROMOSOME*****");
            Score score = Converter.convertToJMusicScore(theBestChromosome.get());
            View.notate(score);
            Play.midi(score);

        }
    }

    private boolean nextPopulation(int iteration) {
        return iteration <= 100;
    }

    private void humanReadable(List<Chromosome> population) {
        for (Chromosome chromosome : population) {
            String formatted = Converter.humanReadable(chromosome);
            System.out.println(String.format("%s: %d", formatted, chromosome.getFitness()));
        }
    }
}
