package puissance4;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static puissance4.CellState.*;

public class GridTest {

    private static final int GRID_MAX_COLUMNS = 7;
    private static final int GRID_MAX_ROWS = 6;
    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = new Grid();

    }

    @Test
    public void get_should_always_return_empty_cell_when_grid_is_empty() {
        //When
        CellState cell = grid.get(0, 0);

        //Then
        assertThat(cell).isEqualTo(EMPTY_CELL);
    }

    @Test
    public void put_should_set_given_value_to_cell() {
        //Given
        grid.put(2, RED_TOKEN);

        //When
        CellState cell = grid.get(2, 0);

        //Then
        assertThat(cell).isEqualTo(RED_TOKEN);
    }

    @Test
    public void put_should_stack_tokens() {
        //Given
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);

        //When
        CellState cell = grid.get(2, 1);

        //Then
        assertThat(cell).isEqualTo(YELLOW_TOKEN);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_rowIndex_is_less_than_zero() {

        grid.get(0, -1);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_rowIndex_is_more_than_6() {

        grid.get(0, GRID_MAX_ROWS);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_columnIndex_is_less_than_zero() {

        grid.get(-1, 0);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_columnIndex_is_more_than_ColumnSize() {

        grid.get(GRID_MAX_COLUMNS, 0);

    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void put_should_throw_IndexOutOfBoundException_when_column_exceeds_6_tokens() {
        //Given
        grid.put(1, RED_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, RED_TOKEN);

        //When
        grid.put(1, RED_TOKEN);

    }

    @Test
    public void empty_should_empty_the_grid() {
        //Given
        grid.put(1, RED_TOKEN);

        //When
        grid.empty();

        //Then
        for (int column = 0; column < GRID_MAX_COLUMNS; column++) {
            for (int row = 0; row < GRID_MAX_ROWS; row++) {
                assertThat(grid.get(column, row)).isEqualTo(EMPTY_CELL);

            }
        }
    }

    @Test
    public void toString_should_print_all_rows_in_the_grid() {
        //Given
        grid.put(1, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);

        //When
        String stringGrid = grid.toString();

        //Then
        String prettyGrid = ".......\n" +
                ".......\n" +
                ".......\n" +
                ".......\n" +
                "..0....\n" +
                ".0**...\n";
        assertThat(stringGrid).isEqualTo(prettyGrid);
    }

    @Test
    public void toString_should_print_all_rows_in_the_gridwith_another_grid() {
        //Given
        grid.put(1, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        grid.put(3, RED_TOKEN);


        //When
        String stringGrid = grid.toString();

        //Then
        String prettyGrid = ".......\n" +
                ".......\n" +
                ".......\n" +
                ".......\n" +
                "..00...\n" +
                ".0**...\n";
        assertThat(stringGrid).isEqualTo(prettyGrid);
    }

    @Test
    public void columns_should_return_an_array_of_cells() {
        //When
        List<List<CellState>> columns = grid.columns();

        //Then
        assertThat(columns.size()).isEqualTo(GRID_MAX_COLUMNS);
    }

    @Test
    public void columns_should_return_an_array_contianing_all_cells() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, RED_TOKEN);

        //When
        List<List<CellState>> columns = grid.columns();

        //Then
        assertThat(columns.get(0)).containsOnly(RED_TOKEN, YELLOW_TOKEN);
    }

    @Test
    public void rows_should_return_an_array_containing_all_rows() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(1, RED_TOKEN);

        //When
        List<List<CellState>> rows = grid.rows();

        //Then
        assertThat(rows.get(0)).containsOnly(RED_TOKEN, YELLOW_TOKEN, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL);
    }


    @Test
    public void diagonals_should_return_diagonal_starting_from_origin() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        List<CellState> topLeftCornerDiagonal = Arrays.asList(YELLOW_TOKEN, YELLOW_TOKEN, YELLOW_TOKEN, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL);

        //When
        List<List<CellState>> diagonals = grid.diagonals();

        //Then
        assertThat(diagonals).contains(topLeftCornerDiagonal);
    }

    @Test
    public void diagonals_should_return_diagonal_starting_right_after_origin() {
        //Given
        grid.put(1, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        List<CellState> topLeftCornerDiagonal = Arrays.asList(YELLOW_TOKEN, YELLOW_TOKEN, YELLOW_TOKEN, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL);

        //When
        List<List<CellState>> diagonals = grid.diagonals();

        //Then
        assertThat(diagonals).contains(topLeftCornerDiagonal);
    }


    @Test
    public void diagonals_should_return_left_down_diagonals() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, RED_TOKEN);
        grid.put(1, RED_TOKEN);
        List<CellState> leftDownDiagonal = Arrays.asList(RED_TOKEN, RED_TOKEN);

        //When
        List<List<CellState>> diagonals = grid.diagonals();

        //Then
        assertThat(diagonals).contains(leftDownDiagonal);
    }
}
