package gui.model;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import music.analysis.feature.name.StatisticName;


public class ConfigurationViewBuilder {

    public static TableView<StatisticFeatureModel> createStatisticalFeaturesConfigurationTable() {
        TableView<StatisticFeatureModel> tableView = new javafx.scene.control.TableView<>();
        tableView.setEditable(true);
        tableView.setPrefWidth(600.0);
        TableColumn<StatisticFeatureModel, StatisticName> firstColumn = new TableColumn<>("Feature name");
        TableColumn<StatisticFeatureModel, Boolean> secondColumn = new TableColumn<>("Active");
        TableColumn<StatisticFeatureModel, String> thirdColumn = new TableColumn<>("Expected value");
        TableColumn<StatisticFeatureModel, String> fourthColumn = new TableColumn<>("Weight");

        firstColumn.setCellValueFactory(s -> s.getValue().statisticNameProperty());
        secondColumn.setCellValueFactory(s -> s.getValue().isActiveProperty());
        thirdColumn.setCellValueFactory(s -> s.getValue().expectedValueProperty());
        fourthColumn.setCellValueFactory(s -> s.getValue().weightProperty());

        secondColumn.setCellFactory(CheckBoxTableCell.forTableColumn(secondColumn));
        thirdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fourthColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn);
        return tableView;
    }
}
