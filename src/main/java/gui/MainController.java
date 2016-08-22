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
import genetic.representation.Chromosome;
import genetic.selection.BinaryTournamentSelection;
import genetic.util.Converter;
import gui.model.ConfigurationViewBuilder;
import gui.model.GeneticAlgorithmConfigurationModel;
import gui.model.RuleFeatureModel;
import gui.model.SpinnerAutoCommit;
import gui.model.StatisticalFeatureModel;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.View;
import music.analysis.Reader;
import music.analysis.feature.container.FeatureContainer;
import music.analysis.feature.container.RuleContainer;
import music.analysis.feature.container.StatisticContainer;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.feature.processor.factory.FeatureProcessorFactory;
import music.analysis.feature.type.MelodicFeature;
import music.analysis.feature.type.RuleFeature;
import music.analysis.feature.type.StatisticalFeature;
import music.harmony.Harmony;
import music.harmony.ScaleType;
import music.harmony.chords.Chord;
import music.harmony.chords.ChordProgressionParser;
import music.notes.pitch.NoteName;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private static final String RANDOM_MUTATION = "Random mutation";
    private static final String MUSICAL_MUTATION = "Musical mutation";

    private static final String STATISTICAL = "Statystyczna";
    private static final String RULE_BASED = "Regułowa";

    private static final String SINGLE_FEATURE_GA_CONFIGURATION_FILE = "configuration/feature-test-config.properties";
    private static final String MULTIPLE_FEATURES_GA_CONFIGURATION_FILE = "configuration/feature-weight-test-config" +
            ".properties";
    final FileChooser fileChooser = new FileChooser();
    private ObservableList<StatisticalFeatureModel> statData = FXCollections.observableArrayList();
    private ObservableList<RuleFeatureModel> ruleData = FXCollections.observableArrayList();
    private GeneticAlgorithmConfigurationModel configurationModel = new GeneticAlgorithmConfigurationModel();
    @FXML
    private ChoiceBox<String> mutations;
    @FXML
    private ChoiceBox<ScaleType> scaleType;
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
    @FXML
    private SpinnerAutoCommit<Integer> numberOfIterationsField;
    @FXML
    private SpinnerAutoCommit<Integer> tempoField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mutationRateField.setValueFactory(configurationModel.getMutationRateModel());
        crossoverRateField.setValueFactory(configurationModel.getCrossoverRateModel());
        populationSizeField.setValueFactory(configurationModel.getPopulationSizeModel());
        numbersOfMeasuresField.setValueFactory(configurationModel.getNumberOfMeasuresModel());
        numberOfIterationsField.setValueFactory(configurationModel.getNumberOfIterationsModel());
        tempoField.setValueFactory(configurationModel.getTempoModel());

        mutationRateField.setEditable(true);
        crossoverRateField.setEditable(true);
        populationSizeField.setEditable(true);
        numbersOfMeasuresField.setEditable(true);
        numberOfIterationsField.setEditable(true);
        tempoField.setEditable(true);

        mutations.setItems(FXCollections.observableArrayList(MUSICAL_MUTATION, RANDOM_MUTATION));
        mutations.getSelectionModel().selectFirst();
        fitnessFunctionType.setItems(FXCollections.observableArrayList(STATISTICAL, RULE_BASED));
        fitnessFunctionType.getSelectionModel().selectFirst();
        scaleType.setItems(FXCollections.observableArrayList(ScaleType.values()));
        scaleType.getSelectionModel().selectFirst();
        baseScaleNote.setItems(FXCollections.observableArrayList(NoteName.values()));
        baseScaleNote.getSelectionModel().selectFirst();
        chordProgressionField.setText("G|C|D|C");

        statData.addAll(FitnessFunctionConfiguration.initStatisticConfiguration());
        ruleData.addAll(FitnessFunctionConfiguration.initRuleConfiguration());
        try {
            loadConfigurationFromPropertiesFile(MULTIPLE_FEATURES_GA_CONFIGURATION_FILE);
        } catch (Exception e) {
            showErrorWindow(e);
        }
    }

    @FXML
    private void runAlgorithm(ActionEvent event) {
        try {
            GeneticAlgorithm algorithm = prepareAlgorithmConfiguration();
            Chromosome result = algorithm.run();
            Score score = Converter.convertToJMusicScore(result);
            score.setTempo(configurationModel.getTempo());
            View.notate(score);
            Play.midi(score);
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

        FeatureContainer<? extends MelodicFeature> featureContainer;
        if (fitnessFunctionType.getValue().equals(STATISTICAL)) {
            featureContainer = new StatisticContainer(prepareStatisticalFitnessFunction());
        } else {
            featureContainer = new RuleContainer(prepareRuleFitnessFunction());
        }

        FitnessFunction fitnessFunction = new MusicalFitnessFunction<>(featureContainer);
        return new GeneticAlgorithm(initialPopulationGenerator, populationGenerator, fitnessFunction,
                configurationModel.getNumberOfIterations());
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

            TableView<StatisticalFeatureModel> tableView = ConfigurationViewBuilder
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

    @FXML
    private void chooseFileToAnalyse() {
        Stage newStage = new Stage();
        File file = fileChooser.showOpenDialog(newStage);
        if (file != null) {
            Reader reader = new Reader();
            FeatureContainer<? extends MelodicFeature> featureContainer;

            if (fitnessFunctionType.getValue().equals(STATISTICAL)) {
                List<StatisticalFeature> features = prepareStatisticalFitnessFunction().stream().filter(f -> !(f
                        .getName().equals(StatisticName.CHORD_NOTES) || f.getName().equals(StatisticName
                        .NON_SCALE_RATING))).collect(Collectors.toList());
                featureContainer = new StatisticContainer(features);
            } else {
                List<RuleFeature> features = prepareRuleFitnessFunction().stream().filter(f -> !(f.getName().equals
                        (RuleName.CHORD_NOTE) || f.getName().equals(RuleName.SCALE_NOTE))).collect(Collectors.toList());
                featureContainer = new RuleContainer(features);
            }
            MusicalFitnessFunction<? extends FeatureContainer> fitnessFunction = new MusicalFitnessFunction<>
                    (featureContainer);
            try {
                String report = reader.analyseMidiFile(file.getPath(), fitnessFunction);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Analysis Dialog");
                alert.setHeaderText(report);
                alert.showAndWait();
            } catch (IllegalArgumentException e) {
                showErrorWindow(e);
            }

        }
    }

    private List<StatisticalFeature> prepareStatisticalFitnessFunction() {
        List<StatisticalFeature> features = new ArrayList<>();
        List<Chord> chords = parseProgression();
        Harmony scale = new Harmony(scaleType.getValue(), baseScaleNote.getValue());

        statData.stream().filter(StatisticalFeatureModel::getIsActive).forEach(statistic -> {
            DoubleFeatureCounter featureCounter = FeatureProcessorFactory.createStatistic(
                    statistic.getStatisticName(), scale, chords);
            features.add(new StatisticalFeature(statistic.getStatisticName(),
                    Double.parseDouble(statistic.getExpectedValue()), Double.parseDouble(statistic.getWeight()),
                    Double.parseDouble(statistic.getStandardDeviation()), featureCounter));
        });
        return features;
    }

    private List<RuleFeature> prepareRuleFitnessFunction() {
        List<RuleFeature> features = new ArrayList<>();
        Harmony scale = new Harmony(scaleType.getValue(), baseScaleNote.getValue());
        List<Chord> chords = parseProgression();

        ruleData.stream().filter(RuleFeatureModel::getIsActive).forEach(rule -> {
            DoubleFeatureCounter featureCounter = FeatureProcessorFactory.createRule(rule.getRuleName(), scale, chords);
            features.add(new RuleFeature(rule.getRuleName(), Double.parseDouble(rule.getWeight()), featureCounter));
        });
        return features;
    }

    private List<Chord> parseProgression() {
        ChordProgressionParser progressionParser = new ChordProgressionParser();
        return progressionParser.parseProgressionText(chordProgressionField.getText(),
                configurationModel.getNumberOfMeasures());
    }

    private void loadConfigurationFromPropertiesFile(String filename) {
        Properties properties = new Properties();
        try (InputStream inputStream = MainController.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IllegalArgumentException(
                        String.format("Error during configuration loading. Unable to find file: %s", filename));
            }
            properties.load(inputStream);
            configurationModel.setConfiguration(properties);

        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Failed to read and set configuration from: %s file. " +
                    "%s", filename, e.getMessage()));
        }
    }


}
