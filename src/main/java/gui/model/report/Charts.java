package gui.model.report;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TitledPane;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.RuleType;

import java.util.List;
import java.util.stream.Collectors;

public final class Charts {

    public static TitledPane createRuleRewardsChartPane(List<RuleResult> ruleResults) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Rule");
        yAxis.setLabel("Value");

        XYChart.Series series = new XYChart.Series();
        series.setName("Reward");
        series.getData().addAll(ruleResults.stream().map(r -> new XYChart.Data<>(r.getName(), r.getReward()))
                .collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres otrzymanych nagród", chart);
    }

    public static TitledPane createIntervalRuleChartPane(List<RuleResult> ruleResults) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Rule");
        yAxis.setLabel("Value");

        XYChart.Series series = new XYChart.Series();
        series.setName("Rule count");
        series.getData().addAll(ruleResults.stream()
                .filter(r -> RuleName.valueOf(r.getName()).isGivenType(RuleType.INTERVAL))
                .map(r -> new XYChart.Data<>(r.getName(), r.getCount())).collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres wystąpień reguł interwałowych", chart);
    }

    public static TitledPane createRhythmicalRuleChartPane(List<RuleResult> ruleResults) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Rule");
        yAxis.setLabel("Value");

        XYChart.Series series = new XYChart.Series();
        series.setName("Rule count");
        series.getData().addAll(ruleResults.stream()
                .filter(r -> RuleName.valueOf(r.getName()).isGivenType(RuleType.RHYTHMICAL))
                .map(r -> new XYChart.Data<>(r.getName(), r.getCount())).collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres wystąpień reguł rytmicznych", chart);
    }

    public static TitledPane createTimeBasedRuleChartPane(List<RuleResult> ruleResults) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Rule");
        yAxis.setLabel("Value");

        XYChart.Series series = new XYChart.Series();
        series.setName("Rule count");
        series.getData().addAll(ruleResults.stream()
                .filter(r -> RuleName.valueOf(r.getName()).isGivenType(RuleType.TIME_BASED))
                .map(r -> new XYChart.Data<>(r.getName(), r.getCount())).collect(Collectors.toList()));
        chart.getData().add(series);
        return new TitledPane("Wykres wystąpień reguł mierzonych w jednostce czasu", chart);
    }
}
