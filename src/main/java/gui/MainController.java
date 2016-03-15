package gui;

import genetic.GeneticAlgorithm;
import genetic.NewPopulationGenerator;
import genetic.crossover.SimpleCrossover;
import genetic.fitness.FitnessFunction;
import genetic.fitness.PentatonicMinorFitness;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.SimpleMutation;
import genetic.selection.BinaryTournamentSelection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<String> mutations;

    @FXML
    private TextField mutationRateTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mutations.setItems(FXCollections.observableArrayList("Random mutation"));
    }

    @FXML
    private void runAlgorithm(ActionEvent event) {
        double mutationRate = Double.parseDouble(mutationRateTextField.getText());

        List<Integer> AVAILABLE_VALUES = Arrays.asList(genetic.representation.Note.values()).stream().map(genetic.representation.Note::value).collect(Collectors.toList());
        InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(new Random(), AVAILABLE_VALUES);
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                new SimpleMutation(mutationRate, new Random(), AVAILABLE_VALUES), new SimpleCrossover(0.9, new Random()));
        FitnessFunction pentatonicFitness = new PentatonicMinorFitness(genetic.representation.Note.A_1);

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, pentatonicFitness);
        algorithm.run();
    }

}
