package gui;

import genetic.GeneticAlgorithm;
import genetic.GeneticGuard;
import genetic.NewPopulationGenerator;
import genetic.crossover.CrossoverCoordinator;
import genetic.crossover.SimpleCrossover;
import genetic.fitness.function.FitnessFunction;
import genetic.fitness.function.MusicalFitnessFunction;
import genetic.initial.InitialPopulationGenerator;
import genetic.initial.RandomPopulationGenerator;
import genetic.mutation.GeneticMutation;
import genetic.mutation.MutationCoordinator;
import genetic.mutation.SimpleMutation;
import genetic.mutation.TowseyMutation;
import genetic.selection.BinaryTournamentSelection;
import gui.model.StatisticFeatureModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import music.Harmony;
import music.Scale;
import music.analysis.feature.container.RuleContainer;
import music.analysis.feature.container.StatisticContainer;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import music.notes.pitch.NoteName;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String RANDOM_MUTATION = "Random mutation";
    private static final String MUSICAL_MUTATION = "Musical mutation";

    private static final String STATISTICAL = "Statystyczna";
    private static final String RULE_BASED = "Regułowa";

    @FXML
    private ChoiceBox<String> mutations;
    @FXML
    private ChoiceBox<Scale> scaleType;
    @FXML
    private ChoiceBox<NoteName> baseScaleNote;
    @FXML
    private ChoiceBox<String> fitnessFunctionType;

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
        fitnessFunctionType.setItems(FXCollections.observableArrayList(STATISTICAL, RULE_BASED));
        fitnessFunctionType.getSelectionModel().selectFirst();
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


        FitnessFunction fitnessFunction;
        if (fitnessFunctionType.getValue().equals(STATISTICAL)) {
            List<StatisticalFeature> features = new ArrayList<>();
            for (StatisticName stat : StatisticName.values()) {
                features.add(new StatisticalFeature(stat, 0.5, 10.0, scale));
            }
            fitnessFunction = new MusicalFitnessFunction<>(new StatisticContainer(features));
        } else {
            List<RuleFeature> features = new ArrayList<>();
            for (RuleName rule : RuleName.values()) {
                features.add(new RuleFeature(rule, 10));
            }
            fitnessFunction = new MusicalFitnessFunction<>(new RuleContainer(features));
        }

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, fitnessFunction);
        algorithm.run();
    }

    @FXML
    private void openStatisticalFitnessConfiguration() {
        Stage newStage = new Stage();
        newStage.setTitle("Statistical Fitness Function Configuration");
        newStage.initModality(Modality.APPLICATION_MODAL);

//        @todo does fxml file should be removed?
        //Parent root = FXMLLoader.load(getClass().getResource("../statisticalFitness.fxml"));
        ScrollPane mainView = new ScrollPane();

        ObservableList<StatisticFeatureModel> statData = FXCollections.observableArrayList();
        Harmony scale = new Harmony(scaleType.getValue().intervals(), baseScaleNote.getValue());
        for (StatisticName stat : StatisticName.values()) {
            statData.add(new StatisticFeatureModel(new StatisticalFeature(stat, 0.5, 10.0, scale)));
        }

        TableView<StatisticFeatureModel> tableView = new TableView<>();
        tableView.setEditable(true);
        tableView.setPrefWidth(800.0);
        TableColumn<StatisticFeatureModel, StatisticName> firstColumn = new TableColumn<>("Feature name");
        TableColumn<StatisticFeatureModel, Boolean> secondColumn = new TableColumn<>("Active");
        TableColumn<StatisticFeatureModel, String> thirdColumn = new TableColumn<>("Expected value");
        TableColumn<StatisticFeatureModel, String> fourthColumn = new TableColumn<>("Weight");

        secondColumn.setEditable(true);

        firstColumn.setCellValueFactory(s -> s.getValue().statisticNameProperty());
        secondColumn.setCellValueFactory(s -> s.getValue().isActiveProperty());
        thirdColumn.setCellValueFactory(s -> s.getValue().expectedValueProperty());
        fourthColumn.setCellValueFactory(s -> s.getValue().weightProperty());

        secondColumn.setCellFactory(CheckBoxTableCell.forTableColumn(secondColumn));
//        secondColumn.setOnEditCommit(e -> e.getRowValue().setIsActive(e.getNewValue()));
        thirdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fourthColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        tableView.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn);
        tableView.setItems(statData);

        mainView.contentProperty().setValue(tableView);
//        listView.setItems(hBoxes);
        Scene scene = new Scene(mainView, 800, 400);
        newStage.setScene(scene);
        newStage.show();


    }

}
