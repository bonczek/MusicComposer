package genetic;

import genetic.fitness.FitnessFunction;
import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.representation.Note;
import jm.constants.RhythmValues;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        population.forEach(c -> System.out.println(String.format("%s: %d",
                c.toString(), fitnessFunction.rateChromosome(c))));
        humanReadable(population);
        Optional<Chromosome> theBestChromosome = population.stream().max((a, b) -> a.getFitness().compareTo(b.getFitness()));
        if (theBestChromosome.isPresent()) {
            System.out.println("*****THE BEST CHROMOSOME*****");
            Score score = convert(theBestChromosome.get());
            View.notate(score);
            Play.midi(score);

        }
    }

    private boolean nextPopulation(int iteration) {
        return iteration <= 100;
    }

    private void humanReadable(List<Chromosome> population) {
        Map<Integer, Note> notesMap = new HashMap<>();
        for (Note n : Note.values()) {
            notesMap.put(n.value(), n);
        }

        for (Chromosome chromosome : population) {
            String formatted = chromosome.getGenesValues().stream().map(val -> notesMap.get(val).name()).collect(Collectors.joining("|"));
            System.out.println(String.format("%s: %d", formatted, fitnessFunction.rateChromosome(chromosome)));
        }
    }

    private Score convert(Chromosome theBest) {
        Phrase phrase = new Phrase();
        for (Integer genValue : theBest.getGenesValues()) {
            jm.music.data.Note note;
            if (genValue == -1) {
                note = new jm.music.data.Note(jm.music.data.Note.REST, RhythmValues.SIXTEENTH_NOTE);
            } else {
                note = new jm.music.data.Note(genValue, RhythmValues.SIXTEENTH_NOTE);
            }
            phrase.add(note);
        }
        Part part = new Part(phrase);
        return new Score(part);
    }
}
