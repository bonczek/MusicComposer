package gui.model;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.name.StatisticName;


public class TableViewBuilder {

    public static TableView<StatisticalFeatureModel> createStatisticalFeaturesConfigurationTable() {
        TableView<StatisticalFeatureModel> tableView = new javafx.scene.control.TableView<>();
        tableView.setEditable(true);
        tableView.setPrefWidth(600.0);
        TableColumn<StatisticalFeatureModel, StatisticName> firstColumn = new TableColumn<>("Cecha");
        TableColumn<StatisticalFeatureModel, Boolean> secondColumn = new TableColumn<>("Aktywna");
        TableColumn<StatisticalFeatureModel, String> thirdColumn = new TableColumn<>("Oczekiwana wartość");
        TableColumn<StatisticalFeatureModel, String> fourthColumn = new TableColumn<>("Waga");
        TableColumn<StatisticalFeatureModel, String> fifthColumn = new TableColumn<>("Odchylenie standardowe");

        firstColumn.setCellValueFactory(s -> s.getValue().statisticNameProperty());
        secondColumn.setCellValueFactory(s -> s.getValue().isActiveProperty());
        thirdColumn.setCellValueFactory(s -> s.getValue().expectedValueProperty());
        fourthColumn.setCellValueFactory(s -> s.getValue().weightProperty());
        fifthColumn.setCellValueFactory(s -> s.getValue().standardDeviationProperty());

        secondColumn.setCellFactory(CheckBoxTableCell.forTableColumn(secondColumn));
        thirdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fourthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fifthColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn);
        return tableView;
    }

    public static TableView<StatisticalResultModel> createStatisticalResultTable() {
        TableView<StatisticalResultModel> tableView = new javafx.scene.control.TableView<>();
        tableView.setPrefWidth(600.0);
        TableColumn<StatisticalResultModel, String> nameColumn = new TableColumn<>("Cecha");
        TableColumn<StatisticalResultModel, String> resultColumn = new TableColumn<>("Wynik");
        TableColumn<StatisticalResultModel, String> expectedColumn = new TableColumn<>("Oczekiwana wartość");
        TableColumn<StatisticalResultModel, String> diffColumn = new TableColumn<>("Różnica");
        TableColumn<StatisticalResultModel, String> weightColumn = new TableColumn<>("Waga");
        TableColumn<StatisticalResultModel, String> stdevColumn = new TableColumn<>("Odchylenie standardowe");
        TableColumn<StatisticalResultModel, String> rewardColumn = new TableColumn<>("Nagroda");

        nameColumn.setCellValueFactory(s -> s.getValue().statisticNameProperty());
        resultColumn.setCellValueFactory(s -> s.getValue().resultProperty());
        expectedColumn.setCellValueFactory(s -> s.getValue().expectedValueProperty());
        diffColumn.setCellValueFactory(s -> s.getValue().differenceProperty());
        weightColumn.setCellValueFactory(s -> s.getValue().weightProperty());
        stdevColumn.setCellValueFactory(s -> s.getValue().standardDeviationProperty());
        rewardColumn.setCellValueFactory(s -> s.getValue().rewardProperty());

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        resultColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        expectedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        diffColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        stdevColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rewardColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(nameColumn, resultColumn, expectedColumn, diffColumn, weightColumn,
                stdevColumn, rewardColumn);
        return tableView;
    }

    public static TableView<RuleResultModel> createRuleResultTable() {
        TableView<RuleResultModel> tableView = new javafx.scene.control.TableView<>();
        tableView.setPrefWidth(600.0);
        TableColumn<RuleResultModel, String> nameColumn = new TableColumn<>("Reguła");
        TableColumn<RuleResultModel, String> resultColumn = new TableColumn<>("Wynik");
        TableColumn<RuleResultModel, String> weightColumn = new TableColumn<>("Waga");
        TableColumn<RuleResultModel, String> rewardColumn = new TableColumn<>("Nagroda");

        nameColumn.setCellValueFactory(s -> s.getValue().statisticNameProperty());
        resultColumn.setCellValueFactory(s -> s.getValue().countProperty());
        weightColumn.setCellValueFactory(s -> s.getValue().weightProperty());
        rewardColumn.setCellValueFactory(s -> s.getValue().rewardProperty());

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        resultColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rewardColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(nameColumn, resultColumn, weightColumn, rewardColumn);
        return tableView;
    }

    public static TableView<RuleFeatureModel> createRuleFeaturesConfigurationTable() {
        TableView<RuleFeatureModel> tableView = new javafx.scene.control.TableView<>();
        tableView.setEditable(true);
        tableView.setPrefWidth(600.0);
        TableColumn<RuleFeatureModel, RuleName> firstColumn = new TableColumn<>("Reguła");
        TableColumn<RuleFeatureModel, Boolean> secondColumn = new TableColumn<>("Aktywna");
        TableColumn<RuleFeatureModel, String> thirdColumn = new TableColumn<>("Waga");

        firstColumn.setCellValueFactory(s -> s.getValue().ruleNameProperty());
        secondColumn.setCellValueFactory(s -> s.getValue().isActiveProperty());
        thirdColumn.setCellValueFactory(s -> s.getValue().weightProperty());

        secondColumn.setCellFactory(CheckBoxTableCell.forTableColumn(secondColumn));
        thirdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        thirdColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(firstColumn, secondColumn, thirdColumn);
        return tableView;
    }
}
