import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class WolframCodeCell extends StackPane {
    int rowIdx, colIdx;
    private Rectangle square;
    private static final String STYLE_CLASS = "cell-view";
    private static final String EMPTY_STYLE_CLASS = "cell-view-empty";
    private static final String FILLED_STYLE_CLASS = "cell-view-filled";

    public WolframCodeCell(int rowIdx, int colIdx, int sideLength) {
        getStyleClass().add(STYLE_CLASS);
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        setState(CellState.EMPTY);

        square = new Rectangle(sideLength, sideLength);
        getChildren().add(square);
    }

    public void setState(CellState state) {
        ObservableList<String> styleClasses = getStyleClass();
        styleClasses.removeAll(EMPTY_STYLE_CLASS, FILLED_STYLE_CLASS);
        switch (state) {
            case EMPTY:
                styleClasses.add(EMPTY_STYLE_CLASS);
                break;
            case FILLED:
                styleClasses.add(FILLED_STYLE_CLASS);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
