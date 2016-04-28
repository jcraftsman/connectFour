package puissance4;

public enum CellState {
    RED_TOKEN('0', "\uD83D\uDD34"),
    YELLOW_TOKEN('*', "\uD83C\uDFBE"),
    NONE('.', "\uD83D\uDD18");

    private char symbol;
    private String emoji;

    CellState(char symbol, String emoji) {
        this.symbol = symbol;
        this.emoji = emoji;
    }

    public char print() {
        return symbol;
    }

    public String printEmoji() {
        return emoji;
    }
}
