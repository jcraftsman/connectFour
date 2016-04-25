package puissance4;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static final char EMPTY_CELL = '.';
    public static final char RED_TOKEN = '0';
    public static final char YELLOW_TOKEN = '*';
    private static final int COLUMNS_SIZE = 7;
    private static final int ROWS_SIZE = 6;

    private char lastPlayedToken = EMPTY_CELL;
    private List<List<Character>> grid;

    public Grid() {
        initGrid();
    }

    public char get(int column, int row) {
        if (containsToken(column, row)) {
            return this.grid.get(column).get(row);
        }
        return EMPTY_CELL;
    }

    public void put(int column) {
        if (grid.get(column).size() >= ROWS_SIZE) {
            throw new IndexOutOfBoundsException();
        }
        alternateToken();
        this.grid.get(column).add(lastPlayedToken);
    }

    public void empty() {
        initGrid();
    }

    @Override
    public String toString() {
        StringBuilder prettyGridBuilder = new StringBuilder();
        for (int rowIndex = ROWS_SIZE - 1; rowIndex >= 0; rowIndex--) {
            appendRows(prettyGridBuilder, rowIndex);
        }
        return prettyGridBuilder.toString();
    }

    private void appendRows(StringBuilder prettyGridBuilder, int row) {
        for (int column = 0; column < COLUMNS_SIZE; column++) {
            prettyGridBuilder.append(get(column, row));
        }
        prettyGridBuilder.append("\n");
    }

    private void alternateToken() {
        lastPlayedToken = lastPlayedToken == RED_TOKEN ? YELLOW_TOKEN : RED_TOKEN;
    }

    private boolean containsToken(int column, int row) {
        if (row > ROWS_SIZE) {
            throw new IndexOutOfBoundsException();
        }
        return this.grid.get(column).size() > row;
    }

    private void initGrid() {
        grid = new ArrayList<List<Character>>(COLUMNS_SIZE);
        for (int column = 0; column < COLUMNS_SIZE; column++) {
            grid.add(new ArrayList<Character>(ROWS_SIZE));
        }
    }


}
