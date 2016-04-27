package puissance4;

import java.util.ArrayList;
import java.util.List;

import static puissance4.CellState.NONE;

public class Grid {

    public static final int COLUMNS_NUMBER = 7;
    public static final int COLUMN_SIZE = 6;
    private List<List<CellState>> grid;

    public Grid() {
        initGrid();
    }

    public CellState get(int columnIndex, int rowIndex) {
        if (isEmpty(columnIndex, rowIndex)) {
            return NONE;
        }
        return column(columnIndex).get(rowIndex);
    }

    public void put(int columnIndex, CellState token) {
        List<CellState> column = column(columnIndex);
        if (isFull(column)) {
            throw new IndexOutOfBoundsException();
        }
        column.add(token);
    }

    public void empty() {
        initGrid();
    }

    public List<List<CellState>> columns() {
        return this.grid;
    }

    public List<List<CellState>> rows() {
        List<List<CellState>> rows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < COLUMN_SIZE; rowIndex++) {
            List<CellState> newRow = extractRow(rowIndex);
            rows.add(newRow);
        }
        return rows;
    }

    public List<List<CellState>> diagonals() {

        List<List<CellState>> diagonalLists = diagonals(Direction.RIGHT);
        diagonalLists.addAll(diagonals(Direction.LEFT));
        return diagonalLists;
    }

    public String prettyPrint() {
        StringBuilder prettyGridBuilder = new StringBuilder();
        appendColumns(prettyGridBuilder);
        return prettyGridBuilder.toString();
    }

    private void appendColumns(StringBuilder prettyGridBuilder) {
        prettyGridBuilder.append("\n");
        for (int cellIndex = COLUMN_SIZE - 1; cellIndex >= 0; cellIndex--) {
            appendCells(prettyGridBuilder, cellIndex);
        }
    }

    private void appendCells(StringBuilder prettyGridBuilder, int rowIndex) {
        prettyGridBuilder.append(" ");
        for (int column = 0; column < COLUMNS_NUMBER; column++) {
            prettyGridBuilder.append(printCell(rowIndex, column)).append(" ");
        }
        prettyGridBuilder.append("\n");
    }

    private char printCell(int rowIndex, int column) {
        CellState cellState = get(column, rowIndex);
        return cellState.print();
    }

    private boolean isEmpty(int columnIndex, int rowIndex) {
        checkRow(rowIndex);
        int numberOfTokensInTheColumn = column(columnIndex).size();
        return numberOfTokensInTheColumn <= rowIndex;
    }

    private List<CellState> column(int columnIndex) {
        return this.grid.get(columnIndex);
    }

    private void checkRow(int rowIndex) {
        if (rowIndex >= COLUMN_SIZE) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void initGrid() {
        grid = new ArrayList<>();
        for (int column = 0; column < COLUMNS_NUMBER; column++) {
            grid.add(new ArrayList<>());
        }
    }

    private boolean isFull(List<CellState> column) {
        return column.size() == COLUMN_SIZE;
    }

    private List<CellState> extractRow(int rowIndex) {
        List<CellState> newRow = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < COLUMNS_NUMBER; columnIndex++) {
            newRow.add(get(columnIndex, rowIndex));
        }
        return newRow;
    }

    private List<List<CellState>> diagonals(Direction direction) {
        List<List<CellState>> diagonalLists = new ArrayList<>();
        for (int rowIndex = 1 - COLUMN_SIZE; rowIndex < COLUMN_SIZE; rowIndex++) {
            List<CellState> oneDiagonal = new ArrayList<>();
            for (int offSet = 0; offSet <= COLUMN_SIZE; offSet++) {
                int currenCellRowIndex = rowIndex + offSet;
                if (currenCellRowIndex < COLUMN_SIZE && currenCellRowIndex >= 0) {
                    int currentColumnIndex = getCurrentColumnIndex(direction, offSet);
                    oneDiagonal.add(get(currentColumnIndex, currenCellRowIndex));
                }
            }
            diagonalLists.add(oneDiagonal);
        }
        return diagonalLists;
    }

    private int getCurrentColumnIndex(Direction direction, int offSet) {
        return direction == Direction.LEFT ? COLUMNS_NUMBER - 1 - offSet : offSet;
    }

    private enum Direction {
        LEFT,
        RIGHT
    }
}
