package puissance4;

public enum CellState {
    RED_TOKEN('0'),
    YELLOW_TOKEN('*'),
    EMPTY_CELL('.');

    private char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }

    public char print() {
        return symbol;
    }
}
