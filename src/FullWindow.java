import javafx.scene.layout.BorderPane;

public class FullWindow extends BorderPane {
    private WolframCode wolframCode;
    private SidePanel sidePanel;
    private int sideLength;

    public FullWindow(int ruleNum, int verticalSize) {
        int size;

        if (verticalSize < 15) { sideLength = 40; size = 19; }
        else if (verticalSize < 30) { sideLength = 20; size = 31;}
        else if (verticalSize < 45) { sideLength = 14; size = 41; }
        else { sideLength = 7; size = 157; }

        wolframCode = new WolframCode(ruleNum, verticalSize, sideLength, size);
        sidePanel = new SidePanel(verticalSize, sideLength);

        setLeft(sidePanel);
        setCenter(wolframCode);

        sidePanel.register(FullWindow.this);
    }

    public void sliderChangedPositive(int newValue) {
        wolframCode.evolve(Math.abs(newValue));
    }

    public void sliderChangedNegative(int oldValue) {
        wolframCode.remove(Math.abs(oldValue));
    }
}
