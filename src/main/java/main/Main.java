package main;

import genetic.GeneticAlgorithm;
import genetic.NewPopulationGenerator;
import genetic.crossover.SimpleCrossover;
import genetic.fitness.FitnessFunction;
import genetic.fitness.PentatonicMinorFitness;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.SimpleMutation;
import genetic.selection.BinaryTournamentSelection;
import jm.JMC;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by adam on 31.12.15.
 */
public class Main implements JMC {

    public static void main(String[] args) {
        susProject();
//        Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
//        Score s = prepareTestMelody();
//        Part p = new Part();
//        Phrase ph = new Phrase();
//        //Read.xml(ph, "src/main/resources/example.xml");
//
//
//        //Read.midi(s);
////        Note[] n = s.getPart(0).getPhrase(0).getNoteArray();
////
//        //Write.jm(score, "untitled.jm");
//        Instrument inst = new SawtoothInst(44100);
//        Instrument[] instruments = {new SawtoothInst(44100)};
//
//        Play.audio(s, instruments);
////        Write.xml(s);
////        Write.midi(s);
    }

    private static void susProject() {
        List<Integer> AVAILABLE_VALUES = Arrays.asList(genetic.representation.Note.values()).stream().map(genetic.representation.Note::value).collect(Collectors.toList());
        InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(new Random(), AVAILABLE_VALUES);
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                new SimpleMutation(0.5, new Random(), AVAILABLE_VALUES), new SimpleCrossover(0.9, new Random()));
        FitnessFunction pentatonicFitness = new PentatonicMinorFitness(genetic.representation.Note.A_1);

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, pentatonicFitness);
        algorithm.run();

    }

    private static Score prepareTestMelody() {
        Score score = new Score();
        Part part = new Part();
        Phrase phrase = new Phrase();
        phrase.setTempo(120.0);

        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(G5, HALF_NOTE));

        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(C5, HALF_NOTE));

        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, HALF_NOTE));

        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, HALF_NOTE));

        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(G5, HALF_NOTE));

        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(C5, HALF_NOTE));

        part.add(phrase);
        score.add(part);
        return score;
    }
}
