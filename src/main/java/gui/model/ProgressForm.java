package gui.model;

import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressForm {
    private final Stage dialogStage;
    private final ProgressBar progressBar = new ProgressBar();
    private final ProgressIndicator progressIndicator = new ProgressIndicator();

    public ProgressForm() {
        dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setTitle("Evolution process");
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        progressBar.setProgress(-1F);
        progressIndicator.setProgress(-1F);

        final HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(progressBar, progressIndicator);

        Scene scene = new Scene(hBox);
        dialogStage.setScene(scene);
    }

    public void activateProgressBar(final Task<?> task) {
        progressBar.progressProperty().bind(task.progressProperty());
        progressIndicator.progressProperty().bind(task.progressProperty());
        dialogStage.show();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
}
