public class Generation {
    private boolean[] cellStates;

    public Generation(String cellStates, char trueSymbol) {
        boolean[] copyArray;

        if (cellStates == null || cellStates.equals("")) {
            copyArray = new boolean[] {false};
        }
        else {
            copyArray = new boolean[cellStates.length()];

            for (int i = 0; i < cellStates.length(); i++) {
                copyArray[i] = cellStates.charAt(i) == trueSymbol;
            }
        }
        setCellStates(copyArray);
    }

    public void setCellStates(boolean[] cellStates) {
        this.cellStates = cellStates.clone();
    }

    public boolean getState(int index) {
        return cellStates[index];
    }

    public boolean[] getCellStates() {
        return cellStates;
    }

    public int getSize() {
        return cellStates.length;
    }
}
