import javafx.scene.layout.GridPane;

public class WolframCode extends GridPane {
    private static WolframCodeCell[][] wolframCodeCells;
    private static Automaton automaton;

    public WolframCode(int ruleNum, int verticalSize, int sideLength, int size) {
        automaton = new Automaton(ruleNum, verticalSize + 1, size);
        wolframCodeCells = new WolframCodeCell[verticalSize + 1][size];

        for (int i = 0; i < verticalSize + 1; i++) {
            for (int j = 0; j < size; j++) {
                WolframCodeCell wolframCodeCell = new WolframCodeCell(i, j, sideLength);
                wolframCodeCells[i][j] = wolframCodeCell;
            }
            addRow(i, wolframCodeCells[i]);
        }

        setWolframCode(automaton.getGeneration(0), 0);
    }

    public void setWolframCode(Generation gen, int index) {
        for (int i = 0; i < gen.getSize(); i++) {
            if (gen.getState(i)) {
                wolframCodeCells[index][i].setState(CellState.FILLED);
            }
        }
    }

    public void evolve(int index) {
        automaton.evolve(index);
        setWolframCode(automaton.getGeneration(index), index);
    }

    public void remove(int index) {
        for (WolframCodeCell key : wolframCodeCells[index]) {
            key.setState(CellState.EMPTY);
        }
    }
}
