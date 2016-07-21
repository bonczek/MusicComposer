package gui.model;

import javafx.scene.control.SpinnerValueFactory;

public class GeneticAlgorithmConfigurationModel {

    private SpinnerValueFactory<Double> mutationRateModel;
    private SpinnerValueFactory<Integer> numberOfMeasuresModel;
    private SpinnerValueFactory<Double> crossoverRateModel;
    private SpinnerValueFactory<Integer> populationSizeModel;
    private SpinnerValueFactory<Integer> numberOfIterationsModel;

    public GeneticAlgorithmConfigurationModel() {
        this.mutationRateModel = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.3, 0.1);
        this.numberOfMeasuresModel = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 256, 4, 1);
        this.crossoverRateModel = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.9, 0.1);
        this.populationSizeModel = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 65356, 128, 2);
        this.numberOfIterationsModel = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 100, 1);
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
