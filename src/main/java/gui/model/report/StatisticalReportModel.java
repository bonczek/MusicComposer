package gui.model.report;

import gui.model.TableViewBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticalReportModel {

    private static final String X_AXIS_NAME = "Statistic";

    private static final String Y_AXIS_NAME = "Value";

    private final List<StatisticalResult> statisticalResults;

    private Accordion reportPanes;

    public StatisticalReportModel(List<StatisticalResult> statisticalResults) {
        this.statisticalResults = statisticalResults;
        this.reportPanes = new Accordion();

        reportPanes.getPanes().add(createTablePane());
        reportPanes.getPanes().add(createResultsChartPane());
        reportPanes.getPanes().add(createRewardsChartPane());
    }

    public Accordion getReportPanes() {
        return reportPanes;
    }

    private TitledPane createTablePane() {
        TableView<StatisticalResultModel> tableView = TableViewBuilder.createStatisticalResultTable();
        ObservableList<StatisticalResultModel> tableData = FXCollections.observableArrayList();
        tableData.addAll(statisticalResults.stream().map(StatisticalResultModel::new).collect(Collectors.toList()));
        tableView.setItems(tableData);
        return new TitledPane("Podsumowanie wyników statystycznych", tableView);
    }

    private TitledPane createResultsChartPane() {
        final BarChart<String, Number> chart = prepareChart();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Result");
        series1.getData().addAll(statisticalResults.stream().map(s -> new XYChart.Data<>(s.getStatisticName(),
                s.getResult())).collect(Collectors.toList()));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Expected");
        series2.getData().addAll(statisticalResults.stream().map(s -> new XYChart.Data<>(s.getStatisticName(),
                s.getExpectedValue())).collect(Collectors.toList()));

        chart.getData().addAll(series1, series2);
        return new TitledPane("Wykres wyników i oczekiwanych wartości", chart);
    }

    private TitledPane createRewardsChartPane() {
        final BarChart<String, Number> chart = prepareChart();

        XYChart.Series seriesA = new XYChart.Series();
        seriesA.setName("Reward");
        seriesA.getData().addAll(statisticalResults.stream().map(s -> new XYChart.Data<>(s.getStatisticName(),
                s.getReward())).collect(Collectors.toList()));

        XYChart.Series seriesB = new XYChart.Series();
        seriesB.setName("Weight");
        seriesB.getData().addAll(statisticalResults.stream().map(s -> new XYChart.Data<>(s.getStatisticName(),
                s.getWeight())).collect(Collectors.toList()));

        chart.getData().addAll(seriesA, seriesB);
        return new TitledPane("Wykres wag i otrzymanych nagród", chart);
    }

    private BarChart<String, Number> prepareChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(X_AXIS_NAME);
        yAxis.setLabel(Y_AXIS_NAME);
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        return chart;
    }
}
