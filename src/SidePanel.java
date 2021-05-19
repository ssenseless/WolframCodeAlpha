import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SidePanel extends VBox {
    private HBox hBox;
    private Slider slider;
    private Spinner<Integer> spinner;

    public SidePanel(int verticalSize, int sideLength) {
        initialize(verticalSize, sideLength);

        hBox.getChildren().addAll(spinner, slider);
        getChildren().add(hBox);
    }

    public void initialize(int verticalSize, int sideLength) {
        slider = new Slider(-verticalSize, 0, 0);
        hBox = new HBox();
        spinner = new Spinner<>(0, verticalSize, 0);

        slider.setOrientation(Orientation.VERTICAL);
        slider.setDisable(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setPrefHeight(sideLength * verticalSize);

        spinner.valueProperty().addListener((observable, oldValue, newValue) ->
                slider.valueProperty().setValue(-newValue));
    }

    public void register(FullWindow fullWindow) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue.intValue() < newValue.intValue()) {
                fullWindow.sliderChangedNegative(oldValue.intValue());
            }
            fullWindow.sliderChangedPositive(newValue.intValue());
        });
    }
}
