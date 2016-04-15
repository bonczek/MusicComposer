package gui;

import genetic.GeneticAlgorithm;
import genetic.NewPopulationGenerator;
import genetic.crossover.SimpleCrossover;
import genetic.fitness.FitnessFunction;
import genetic.fitness.rules.ScaleFitness;
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
import music.Harmony;
import music.Pitch;
import music.Scale;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<String> mutations;

    @FXML
    private ChoiceBox<Scale> scaleType;

    @FXML
    private ChoiceBox<Pitch> baseScaleNote;

    @FXML
    private TextField mutationRateTextField;

    @FXML
    private TextField populationSizeTextField;

    @FXML
    private TextField numbersOfMeasuresTextField;

    @FXML
    private TextField scaleRewardTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mutations.setItems(FXCollections.observableArrayList("Random mutation"));
        mutations.getSelectionModel().selectFirst();
        scaleType.setItems(FXCollections.observableArrayList(Scale.values()));
        scaleType.getSelectionModel().selectFirst();
        baseScaleNote.setItems(FXCollections.observableArrayList(Pitch.values()));
        baseScaleNote.getSelectionModel().selectFirst();
    }

    @FXML
    private void runAlgorithm(ActionEvent event) {
        //@todo error handling
        double mutationRate = Double.parseDouble(mutationRateTextField.getText());
        int populationSize = Integer.parseInt(populationSizeTextField.getText());
        int numbersOfMeasures = Integer.parseInt(numbersOfMeasuresTextField.getText());
        int scaleReward = Integer.parseInt(scaleRewardTextField.getText());


        InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(populationSize, numbersOfMeasures, new
                Random());
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                new SimpleMutation(mutationRate, new Random()), new SimpleCrossover(0.9, new Random()));
        FitnessFunction pentatonicFitness = new ScaleFitness(new Harmony(scaleType.getValue().intervals(),
                baseScaleNote.getValue()), scaleReward);

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, pentatonicFitness);
        algorithm.run();
    }

}