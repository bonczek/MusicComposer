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
import gui.model.ConfigurationViewBuilder;
import gui.model.GeneticAlgorithmConfigurationModel;
import gui.model.RuleFeatureModel;
import gui.model.SpinnerAutoCommit;
import gui.model.StatisticFeatureModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import music.analysis.feature.container.RuleContainer;
import music.analysis.feature.container.StatisticContainer;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import music.harmony.Chord;
import music.harmony.ChordProgressionParser;
import music.harmony.Harmony;
import music.harmony.ScaleName;
import music.notes.pitch.NoteName;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private static final String RANDOM_MUTATION = "Random mutation";
    private static final String MUSICAL_MUTATION = "Musical mutation";

    private static final String STATISTICAL = "Statystyczna";
    private static final String RULE_BASED = "Regułowa";

    private ObservableList<StatisticFeatureModel> statData = FXCollections.observableArrayList();
    private ObservableList<RuleFeatureModel> ruleData = FXCollections.observableArrayList();

    private GeneticAlgorithmConfigurationModel configurationModel = new GeneticAlgorithmConfigurationModel();

    @FXML
    private ChoiceBox<String> mutations;
    @FXML
    private ChoiceBox<ScaleName> scaleType;
    @FXML
    private ChoiceBox<NoteName> baseScaleNote;
    @FXML
    private ChoiceBox<String> fitnessFunctionType;
    @FXML
    private SpinnerAutoCommit<Double> mutationRateField;
    @FXML
    private SpinnerAutoCommit<Double> crossoverRateField;
    @FXML
    private SpinnerAutoCommit<Integer> populationSizeField;
    @FXML
    private SpinnerAutoCommit<Integer> numbersOfMeasuresField;
    @FXML
    private TextArea chordProgressionField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mutationRateField.setValueFactory(configurationModel.getMutationRateModel());
        crossoverRateField.setValueFactory(configurationModel.getCrossoverRateModel());
        populationSizeField.setValueFactory(configurationModel.getPopulationSizeModel());
        numbersOfMeasuresField.setValueFactory(configurationModel.getNumberOfMeasuresModel());

        mutationRateField.setEditable(true);
        crossoverRateField.setEditable(true);
        populationSizeField.setEditable(true);
        numbersOfMeasuresField.setEditable(true);

        mutations.setItems(FXCollections.observableArrayList(MUSICAL_MUTATION, RANDOM_MUTATION));
        mutations.getSelectionModel().selectFirst();
        fitnessFunctionType.setItems(FXCollections.observableArrayList(STATISTICAL, RULE_BASED));
        fitnessFunctionType.getSelectionModel().selectFirst();
        scaleType.setItems(FXCollections.observableArrayList(ScaleName.values()));
        scaleType.getSelectionModel().selectFirst();
        baseScaleNote.setItems(FXCollections.observableArrayList(NoteName.values()));
        baseScaleNote.getSelectionModel().selectFirst();
        chordProgressionField.setText("G|C|D|C");
        initStatData();
        initRuleData();
    }

    @FXML
    private void runAlgorithm(ActionEvent event) {
        try {
            GeneticAlgorithm algorithm = prepareAlgorithmConfiguration();
            algorithm.run();
        } catch (Exception e) {
            showErrorWindow(e);
        }
    }

    private GeneticAlgorithm prepareAlgorithmConfiguration() throws Exception {
        ChordProgressionParser progressionParser = new ChordProgressionParser();
        List<Chord> progression = progressionParser.parseProgressionText(chordProgressionField.getText(),
                configurationModel.getNumberOfMeasures());

        InitialPopulationGenerator initialPopulationGenerator = new RandomPopulationGenerator(
                configurationModel.getPopulationSize(), configurationModel.getNumberOfMeasures(), new Random());
        Harmony scale = new Harmony(scaleType.getValue(), baseScaleNote.getValue());
        GeneticMutation mutation;
        if (mutations.getValue().equals(MUSICAL_MUTATION)) {
            mutation = new TowseyMutation(new Random(), scale);
        } else {
            mutation = new SimpleMutation(new Random());
        }
        MutationCoordinator mutationCoordinator = new MutationCoordinator(
                new GeneticGuard(configurationModel.getMutationRate()), mutation);
        CrossoverCoordinator crossoverCoordinator = new CrossoverCoordinator(
                new GeneticGuard(configurationModel.getCrossoverRate()), new SimpleCrossover(new Random()));
        NewPopulationGenerator populationGenerator = new NewPopulationGenerator(new BinaryTournamentSelection(new Random()),
                mutationCoordinator, crossoverCoordinator);

        FitnessFunction fitnessFunction;
        if (fitnessFunctionType.getValue().equals(STATISTICAL)) {
            fitnessFunction = new MusicalFitnessFunction<>(prepareStatisticalFitnessFunction());
        } else {
            fitnessFunction = new MusicalFitnessFunction<>(prepareRuleFitnessFunction());
        }

        return new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, fitnessFunction);
    }

    private void showErrorWindow(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(e.getMessage());
        if (e.getCause() != null) {
            alert.setContentText(e.getCause().getMessage());
        }
        alert.showAndWait();
    }

    @FXML
    private void openFitnessConfiguration() {
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        ScrollPane mainView;
        if (fitnessFunctionType.getValue().equals(STATISTICAL)) {
            newStage.setTitle("Statistical Fitness Function Configuration");

            TableView<StatisticFeatureModel> tableView = ConfigurationViewBuilder
                    .createStatisticalFeaturesConfigurationTable();
            tableView.setItems(statData);
            mainView = new ScrollPane(tableView);
        } else {
            newStage.setTitle("Rule Fitness Function Configuration");

            TableView<RuleFeatureModel> tableView = ConfigurationViewBuilder
                    .createRuleFeaturesConfigurationTable();
            tableView.setItems(ruleData);
            mainView = new ScrollPane(tableView);
        }

        Scene scene = new Scene(mainView, 800, 400);

        newStage.setScene(scene);
        newStage.show();
    }

    private void initStatData() {
        Harmony scale = new Harmony(scaleType.getValue(), baseScaleNote.getValue());
        List<Chord> chords = parseProgression();
        for (StatisticName stat : StatisticName.values()) {
            statData.add(new StatisticFeatureModel(new StatisticalFeature(stat, 0.5, 10.0, scale, chords)));
        }
    }

    private void initRuleData() {
        for (RuleName ruleName : RuleName.values()) {
            ruleData.add(new RuleFeatureModel(new RuleFeature(ruleName, 10)));
        }
    }

    private StatisticContainer prepareStatisticalFitnessFunction() {
        List<StatisticalFeature> features = new ArrayList<>();
        List<Chord> chords = parseProgression();
        Harmony scale = new Harmony(scaleType.getValue(), baseScaleNote.getValue());
        features.addAll(statData.stream().filter(StatisticFeatureModel::getIsActive)
                .map(featureModel -> new StatisticalFeature(featureModel.getStatisticName(),
                        Double.parseDouble(featureModel.getExpectedValue()),
                        Double.parseDouble(featureModel.getWeight()), scale, chords))
                .collect(Collectors.toList()));

        return new StatisticContainer(features);
    }

    private RuleContainer prepareRuleFitnessFunction() {
        List<RuleFeature> features = new ArrayList<>();
        features.addAll(ruleData.stream().filter(RuleFeatureModel::getIsActive)
                .map(featureModel -> new RuleFeature(featureModel.getRuleName(),
                        Integer.parseInt(featureModel.getWeight()))).collect(Collectors.toList()));
        return new RuleContainer(features);
    }

    private List<Chord> parseProgression() {
        ChordProgressionParser progressionParser = new ChordProgressionParser();
        return progressionParser.parseProgressionText(chordProgressionField.getText(),
                configurationModel.getNumberOfMeasures());
    }

}
