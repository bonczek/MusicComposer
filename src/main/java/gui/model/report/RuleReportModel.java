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
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.RuleType;

import java.util.List;
import java.util.stream.Collectors;

public class RuleReportModel {

    private static final String X_AXIS_NAME = "Rule";

    private static final String Y_AXIS_NAME = "Rule count";

    private final List<RuleResult> ruleResults;

    private Accordion reportPanes;

    public RuleReportModel(List<RuleResult> ruleResults) {
        this.ruleResults = ruleResults;
        this.reportPanes = new Accordion();

        reportPanes.getPanes().add(createTablePane());
        reportPanes.getPanes().add(createRewardsChartPane());
        reportPanes.getPanes().add(createIntervalChartPane());
        reportPanes.getPanes().add(createRhythmicalChartPane());
        reportPanes.getPanes().add(createTimeBasedRuleChartPane());
    }

    public Accordion getReportPanes() {
        return reportPanes;
    }

    private TitledPane createTablePane() {
        TableView<RuleResultModel> tableView = TableViewBuilder.createRuleResultTable();
        ObservableList<RuleResultModel> tableData = FXCollections.observableArrayList();
        tableData.addAll(ruleResults.stream().map(RuleResultModel::new).collect(Collectors.toList()));
        tableView.setItems(tableData);
        return new TitledPane("Podsumowanie wyników reguł", tableView);
    }

    private TitledPane createRewardsChartPane() {
        final BarChart<String, Number> chart = prepareChart();
        chart.getYAxis().setLabel("Reward");

        XYChart.Series series = new XYChart.Series();
        series.setName("Reward");
        series.getData().addAll(ruleResults.stream().map(r -> new XYChart.Data<>(r.getName(), r.getReward()))
                .collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres otrzymanych nagród", chart);
    }

    private TitledPane createIntervalChartPane() {
        final BarChart<String, Number> chart = prepareChart();

        XYChart.Series series = new XYChart.Series();
        series.setName("Rule count");
        series.getData().addAll(ruleResults.stream()
                .filter(r -> RuleName.valueOf(r.getName()).isGivenType(RuleType.INTERVAL))
                .map(r -> new XYChart.Data<>(r.getName(), r.getCount())).collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres wystąpień reguł interwałowych", chart);
    }

    private TitledPane createRhythmicalChartPane() {
        final BarChart<String, Number> chart = prepareChart();

        XYChart.Series series = new XYChart.Series();
        series.setName("Rule count");
        series.getData().addAll(ruleResults.stream()
                .filter(r -> RuleName.valueOf(r.getName()).isGivenType(RuleType.RHYTHMICAL))
                .map(r -> new XYChart.Data<>(r.getName(), r.getCount())).collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres wystąpień reguł rytmicznych", chart);
    }

    private TitledPane createTimeBasedRuleChartPane() {
        final BarChart<String, Number> chart = prepareChart();

        XYChart.Series series = new XYChart.Series();
        series.getData().addAll(ruleResults.stream()
                .filter(r -> RuleName.valueOf(r.getName()).isGivenType(RuleType.TIME_BASED))
                .map(r -> new XYChart.Data<>(r.getName(), r.getCount())).collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres wystąpień reguł mierzonych w jednostce czasu", chart);
    }

    private BarChart<String, Number> prepareChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(X_AXIS_NAME);
        yAxis.setLabel(Y_AXIS_NAME);

        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);
        return chart;
    }
}
