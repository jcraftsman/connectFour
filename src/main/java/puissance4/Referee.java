package puissance4;

import static puissance4.CellState.*;

public class Referee {

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
        return this.analyzer.isDraw() || this.analyzer.whoIsTheWinner() != EMPTY_CELL;
    }

    public CellState theWinnerIs() {
        return analyzer.whoIsTheWinner();
    }

    private void switchNextTurn() {
        nextTurn = nextTurn == YELLOW_TOKEN ? RED_TOKEN : YELLOW_TOKEN;
    }
}
