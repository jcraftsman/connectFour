package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static puissance4.Grid.*;

public class GridTest {

    public static final int GRID_MAX_COLUMNS = 7;
    public static final int GRID_MAX_ROWS = 6;
    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = new Grid();

    }

    @Test
    public void get_should_always_return_empty_cell_when_grid_is_empty() {
        //Given

        //When
        char cell = grid.get(0, 0);

        //Then
        assertThat(cell).isEqualTo(EMPTY_CELL);
    }

    @Test
    public void put_should_set_given_value_to_cell() {
        //Given
        grid.put(2);

        //When
        char cell = grid.get(2, 0);

        //Then
        assertThat(cell).isEqualTo(RED_TOKEN);
    }

    @Test
    public void put_should_altern_token_color() {
        //Given
        grid.put(1);
        grid.put(2);

        //When
        char cell = grid.get(2, 0);

        //Then
        assertThat(cell).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void put_should_stack_tokens() {
        //Given
        grid.put(2);
        grid.put(2);

        //When
        char cell = grid.get(2, 1);

        //Then
        assertThat(cell).isEqualTo(YELLOW_TOKEN);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_rowIndex_is_less_than_zero() {

        grid.get(0, -1);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_rowIndex_is_more_than_6() {

        grid.get(0, GRID_MAX_ROWS + 1);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_columnIndex_is_less_than_zero() {

        grid.get(-1, 0);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_should_throw_indexOutOfBoundException_when_columnIndex_is_more_than_ColumnSize() {

        grid.get(GRID_MAX_COLUMNS + 1, 0);

    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void put_should_throw_IndexOutOfBoundException_when_column_exceeds_6_tokens() {
        //Given
        grid.put(1);
        grid.put(1);
        grid.put(1);
        grid.put(1);
        grid.put(1);
        grid.put(1);

        //When
        grid.put(1);

    }

    @Test
    public void empty_should_empty_the_grid() {
        //Given
        grid.put(1);

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
        grid.put(1);
        grid.put(2);
        grid.put(2);
        grid.put(3);

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
        grid.put(1);
        grid.put(2);
        grid.put(2);
        grid.put(3);
        grid.put(3);


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
}
