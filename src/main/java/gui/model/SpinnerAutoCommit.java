package gui.model;

import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

/**
 * Spinner that it will be updated on lost focus. When wrong value is given it return to previous value.
 * Solution is based on Jonathan solution: http://stackoverflow.com/a/29968201
 *
 * @param <T>
 */
public class SpinnerAutoCommit<T> extends Spinner<T> {

    public SpinnerAutoCommit() {
        super();
        addListenerAndAction();
    }

    public SpinnerAutoCommit(int min, int max, int initialValue) {
        super(min, max, initialValue);
        addListenerAndAction();
    }

    public SpinnerAutoCommit(int min, int max, int initialValue, int amountToStepBy) {
        super(min, max, initialValue, amountToStepBy);
        addListenerAndAction();
    }

    public SpinnerAutoCommit(double min, double max, double initialValue) {
        super(min, max, initialValue);
        addListenerAndAction();
    }

    public SpinnerAutoCommit(double min, double max, double initialValue, double amountToStepBy) {
        super(min, max, initialValue, amountToStepBy);
        addListenerAndAction();
    }

    public SpinnerAutoCommit(ObservableList<T> items) {
        super(items);
        addListenerAndAction();
    }

    public SpinnerAutoCommit(SpinnerValueFactory<T> valueFactory) {
        super(valueFactory);
        addListenerAndAction();
    }

    private void addListenerAndAction() {
        getEditor().focusedProperty().addListener(event -> {
            if (isEditable()) {
                commitEditorText();
            }
        });
        getEditor().setOnAction(event -> {
            if (isEditable()) {
                commitEditorText();
            }
        });
    }

    private void commitEditorText() {
        if (!isEditable()) return;
        String text = getEditor().getText();
        SpinnerValueFactory<T> valueFactory = getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                if (!invalidNumber(converter, text)) {
                    T value = converter.fromString(text);
                    valueFactory.setValue(value);
                } else {
                    getEditor().setText(valueFactory.getValue().toString());
                }
            }
        }
    }

    private boolean invalidNumber(StringConverter<T> converter, String text) {
        try {
            T value = converter.fromString(text);
            return value == null;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
