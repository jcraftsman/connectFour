package puissance4;

import static puissance4.CellState.*;

public class Referee {

    private static final String RED_NEXT_PLAYER_MSG = "Rouge colonne [1-7] : ";
    private static final String YELLOW_NEXT_PLAYER_MSG = "Jaune colonne [1-7] : ";
    private static final String DRAW_MSG = "Egalité : fin de partie";
    private static final String WIN_CONGRATS_MSG = "gagne la partie, bravo :) ";
    private static final String YELLOW = "Jaune ";
    private static final String RED = "Rouge ";

    private CellState nextTurn = YELLOW_TOKEN;
    private Grid grid;
    private Analyzer analyzer;

    public Referee(Grid grid) {
        this.grid = grid;
        this.analyzer = new Analyzer(grid);
    }

    public CellState whoWillPlay() {
        return nextTurn;
    }

    public void play(int columnIndex) {
        this.grid.put(columnIndex, nextTurn);
        switchNextTurn();
    }

    public boolean isFinished() {
        return this.analyzer.isDraw() || this.analyzer.whoIsTheWinner() != NONE;
    }

    public CellState theWinnerIs() {
        return analyzer.whoIsTheWinner();
    }

    public String print() {
        if (this.analyzer.isDraw()) {
            return DRAW_MSG;
        }
        if (this.theWinnerIs() != NONE) {
            return printWinner();
        }
        return printNextPlayer();
    }

    private String printNextPlayer() {
        if (RED_TOKEN == this.whoWillPlay()) {
            return RED_NEXT_PLAYER_MSG;
        } else {
            return YELLOW_NEXT_PLAYER_MSG;
        }
    }

    private String printWinner() {
        if (this.theWinnerIs() == YELLOW_TOKEN) {
            return YELLOW + WIN_CONGRATS_MSG;
        } else {
            return RED + WIN_CONGRATS_MSG;
        }
    }

    private void switchNextTurn() {
        nextTurn = nextTurn == YELLOW_TOKEN ? RED_TOKEN : YELLOW_TOKEN;
    }

}

