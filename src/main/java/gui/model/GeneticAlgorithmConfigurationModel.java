package gui.model;

import javafx.scene.control.SpinnerValueFactory;

import java.util.Properties;

public class GeneticAlgorithmConfigurationModel {

    private SpinnerValueFactory<Double> mutationRateModel;
    private SpinnerValueFactory<Integer> numberOfMeasuresModel;
    private SpinnerValueFactory<Double> crossoverRateModel;
    private SpinnerValueFactory<Integer> populationSizeModel;
    private SpinnerValueFactory<Integer> numberOfIterationsModel;

    public GeneticAlgorithmConfigurationModel() {
        this.mutationRateModel = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.2, 0.1);
        this.numberOfMeasuresModel = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 256, 12, 1);
        this.crossoverRateModel = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.8, 0.1);
        this.populationSizeModel = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 65356, 512, 2);
        this.numberOfIterationsModel = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 500, 1);
    }

    public void setConfiguration(Properties configuration) throws IllegalArgumentException {
        try {
            mutationRateModel.setValue(Double.parseDouble(configuration.getProperty("gui.model.mutationRate")));
            numberOfMeasuresModel.setValue(Integer.parseInt(configuration.getProperty("gui.model.numberOfMeasures")));
            crossoverRateModel.setValue(Double.parseDouble(configuration.getProperty("gui.model.crossoverRate")));
            populationSizeModel.setValue(Integer.parseInt(configuration.getProperty("gui.model.populationSize")));
            numberOfIterationsModel.setValue(Integer.parseInt(configuration.getProperty("gui.model.numberOfIterations")));
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException(String.format(
                    "Failed to set GA configuration from given properties file. %s", e.getMessage()));
        }
    }

    public SpinnerValueFactory<Integer> getNumberOfIterationsModel() {
        return numberOfIterationsModel;
    }

    public int getNumberOfIterations() {
        return numberOfIterationsModel.getValue();
    }

    public SpinnerValueFactory<Double> getMutationRateModel() {
        return mutationRateModel;
    }

    public SpinnerValueFactory<Integer> getNumberOfMeasuresModel() {
        return numberOfMeasuresModel;
    }

    public SpinnerValueFactory<Double> getCrossoverRateModel() {
        return crossoverRateModel;
    }

    public SpinnerValueFactory<Integer> getPopulationSizeModel() {
        return populationSizeModel;
    }

    public double getCrossoverRate() {
        return crossoverRateModel.getValue();
    }

    public double getMutationRate() {
        return mutationRateModel.getValue();
    }

    public int getNumberOfMeasures() {
        return numberOfMeasuresModel.getValue();
    }

    public int getPopulationSize() {
        return populationSizeModel.getValue();
    }
}
