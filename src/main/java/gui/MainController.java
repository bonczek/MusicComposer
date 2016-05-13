package gui;

import genetic.GeneticAlgorithm;
import genetic.GeneticGuard;
import genetic.NewPopulationGenerator;
import genetic.crossover.CrossoverCoordinator;
import genetic.crossover.SimpleCrossover;
import genetic.fitness.FitnessFunction;
import genetic.fitness.statistic.MusicalStatisticsFitness;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.GeneticMutation;
import genetic.mutation.MutationCoordinator;
import genetic.mutation.SimpleMutation;
import genetic.mutation.TowseyMutation;
import genetic.selection.BinaryTournamentSelection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import music.Harmony;
import music.Scale;
import music.notes.pitch.NoteName;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String RANDOM_MUTATION = "Random mutation";
    private static final String MUSICAL_MUTATION = "Musical mutation";
    @FXML
    private ChoiceBox<String> mutations;
    @FXML
    private ChoiceBox<Scale> scaleType;
    @FXML
    private ChoiceBox<NoteName> baseScaleNote;
    @FXML
    private TextField mutationRateTextField;
    @FXML
    private TextField crossoverRateTextField;
    @FXML
    private TextField populationSizeTextField;
    @FXML
    private TextField numbersOfMeasuresTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mutations.setItems(FXCollections.observableArrayList(MUSICAL_MUTATION, RANDOM_MUTATION));
        mutations.getSelectionModel().selectFirst();
        scaleType.setItems(FXCollections.observableArrayList(Scale.values()));
        scaleType.getSelectionModel().selectFirst();
        baseScaleNote.setItems(FXCollections.observableArrayList(NoteName.values()));
        baseScaleNote.getSelectionModel().selectFirst();
    }

    @FXML
    private void runAlgorithm(ActionEvent event) {
        //@todo error handling
        double mutationRate = Double.parseDouble(mutationRateTextField.getText());
        int populationSize = Integer.parseInt(populationSizeTextField.getText());
        int numbersOfMeasures = Integer.parseInt(numbersOfMeasuresTextField.getText());
        double crossoverRate = Double.parseDouble(crossoverRateTextField.getText());


        InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(populationSize, numbersOfMeasures, new
                Random());
        Harmony scale = new Harmony(scaleType.getValue().intervals(), baseScaleNote.getValue());
        GeneticMutation mutation;
        if (mutations.getValue().equals(MUSICAL_MUTATION)) {
            mutation = new TowseyMutation(new Random(), scale);
        } else {
            mutation = new SimpleMutation(new Random());
        }
        MutationCoordinator mutationCoordinator = new MutationCoordinator(new GeneticGuard(mutationRate), mutation);
        CrossoverCoordinator crossoverCoordinator = new CrossoverCoordinator(new GeneticGuard(crossoverRate), new
                SimpleCrossover(new Random()));
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                mutationCoordinator, crossoverCoordinator);
//        FitnessFunction pentatonicFitness = new ScaleFitness(new Harmony(scaleType.getValue().intervals(),
//                baseScaleNote.getValue()), scaleReward);
        FitnessFunction statisticalFitness = new MusicalStatisticsFitness(scale);

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, statisticalFitness);
        algorithm.run();
    }

}
