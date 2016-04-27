package puissance4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static puissance4.CellState.NONE;

public class Analyzer {

    public static final int WIN_CELLS_COUNT = 4;
    private Grid grid;

    public Analyzer(Grid grid) {
        this.grid = grid;
    }

    public CellState whoIsTheWinner() {
        Stream<List<CellState>> connectedCells = allConnectedCells();
        CellState columnWinner = connectedCells
                .map(this::winnerInAList)
                .reduce((cellState, cellState2) -> validWinner(cellState2) ? cellState2 : cellState)
                .get();
        return columnWinner;
    }

    public boolean isDraw() {
        for (int rowIndex = 0; rowIndex < Grid.COLUMN_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < Grid.COLUMNS_NUMBER; columnIndex++) {
                if (grid.get(columnIndex, rowIndex) == NONE)
                    return false;
            }
        }
        return whoIsTheWinner() == NONE;
    }

    private Stream<List<CellState>> allConnectedCells() {
        List<List<CellState>> lists = new ArrayList<>();
        lists.addAll(grid.columns());
        lists.addAll(grid.rows());
        lists.addAll(grid.diagonals());
        return lists.stream();
    }

    private CellState winnerInAList(List<CellState> cellStates) {
        int points = 1;
        CellState previousState = NONE;
        for (CellState cellState : cellStates) {
            if (cellState == previousState) {
                points++;
                if (points == WIN_CELLS_COUNT) {
                    return cellState;
                }
            } else {
                points = 1;
            }
            previousState = cellState;
        }
        return NONE;
    }

    private boolean validWinner(CellState columnWinner) {
        return columnWinner == CellState.RED_TOKEN || columnWinner == CellState.YELLOW_TOKEN;
    }
}
