package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static puissance4.CellState.*;

public class AnalyzerTests {


    private Analyzer analyzer;
    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = new Grid();
        analyzer = new Analyzer(grid);
    }

    @Test
    public void whoIsTheWinner_should_return_red_when_called_with_4_red_tokens_in_a_row() {
        //Given
        putFourInARow(RED_TOKEN, 0);

        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(RED_TOKEN);
    }

    @Test
    public void whoIsTheWinner_should_return_yellow_when_called_with_4_yellow_tokens_in_a_row() {
        //Given
        putFourInARow(YELLOW_TOKEN, 0);

        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void whoIsTheWinner_should_return_yellow_when_called_with_4_yellow_tokens_in_the_second_row() {
        //Given
        grid.put(0, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        putFourInARow(YELLOW_TOKEN, 0);

        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void whoIsTheWinner_should_return_yellow_when_called_with_4_yellow_tokens_in_the_second_row_starting_from_the_second_cell() {
        //Given
        grid.put(0, RED_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        grid.put(4, YELLOW_TOKEN);
        putFourInARow(YELLOW_TOKEN, 1);

        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void whoIsTheWinner_should_return_yellow_when_called_with_4_yellow_tokens_in_the_second_row_starting_from_the_second_cell2() {
        //Given
        grid.put(0, RED_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        grid.put(4, RED_TOKEN);
        grid.put(5, YELLOW_TOKEN);
        grid.put(6, YELLOW_TOKEN);


        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(EMPTY_CELL);
    }

    @Test
    public void whoIsTheWinner_should_return_empty_when_called_with_a_grid_with_only_2_tokens() {
        //Given
        grid.put(0, RED_TOKEN);
        grid.put(1, RED_TOKEN);

        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(EMPTY_CELL);
    }

    @Test
    public void whoIsTheWinner_should_return_yellow_when_called_with_4_yellow_tokens_stacked_on_the_first_column() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, YELLOW_TOKEN);

        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void whoIsTheWinner_should_return_yellow_when_called_with_4_yellow_tokens_in_diagonal() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);

        //When
        CellState winner = analyzer.whoIsTheWinner();

        //Then
        assertThat(winner).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void draw_should_returnTrue_where_Table_is_full() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, RED_TOKEN);
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, RED_TOKEN);
        grid.put(0, YELLOW_TOKEN);

        grid.put(1, YELLOW_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);

        grid.put(2, RED_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);

        grid.put(3, RED_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        grid.put(3, RED_TOKEN);

        grid.put(4, YELLOW_TOKEN);
        grid.put(4, YELLOW_TOKEN);
        grid.put(4, RED_TOKEN);
        grid.put(4, RED_TOKEN);
        grid.put(4, RED_TOKEN);
        grid.put(4, YELLOW_TOKEN);

        grid.put(5, RED_TOKEN);
        grid.put(5, YELLOW_TOKEN);
        grid.put(5, RED_TOKEN);
        grid.put(5, YELLOW_TOKEN);
        grid.put(5, RED_TOKEN);
        grid.put(5, YELLOW_TOKEN);

        grid.put(6, YELLOW_TOKEN);
        grid.put(6, RED_TOKEN);
        grid.put(6, YELLOW_TOKEN);
        grid.put(6, RED_TOKEN);
        grid.put(6, RED_TOKEN);
        grid.put(6, YELLOW_TOKEN);

        //When
        boolean draw = analyzer.isDraw();

        //Then
        assertThat(draw).isTrue();
    }

    @Test
    public void draw_should_returnFalse_where_Table_is_full_but_there_is_a_win() {
        //Given
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, YELLOW_TOKEN);
        grid.put(0, RED_TOKEN);
        grid.put(0, YELLOW_TOKEN);

        grid.put(1, YELLOW_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);
        grid.put(1, RED_TOKEN);
        grid.put(1, YELLOW_TOKEN);

        grid.put(2, RED_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, RED_TOKEN);
        grid.put(2, YELLOW_TOKEN);

        grid.put(3, RED_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        grid.put(3, RED_TOKEN);
        grid.put(3, YELLOW_TOKEN);
        grid.put(3, RED_TOKEN);

        grid.put(4, YELLOW_TOKEN);
        grid.put(4, YELLOW_TOKEN);
        grid.put(4, RED_TOKEN);
        grid.put(4, RED_TOKEN);
        grid.put(4, RED_TOKEN);
        grid.put(4, YELLOW_TOKEN);

        grid.put(5, RED_TOKEN);
        grid.put(5, YELLOW_TOKEN);
        grid.put(5, RED_TOKEN);
        grid.put(5, YELLOW_TOKEN);
        grid.put(5, RED_TOKEN);
        grid.put(5, YELLOW_TOKEN);

        grid.put(6, YELLOW_TOKEN);
        grid.put(6, RED_TOKEN);
        grid.put(6, YELLOW_TOKEN);
        grid.put(6, RED_TOKEN);
        grid.put(6, RED_TOKEN);
        grid.put(6, YELLOW_TOKEN);

        //When
        boolean draw = analyzer.isDraw();

        //Then
        assertThat(draw).isFalse();
    }

    private void putFourInARow(CellState yellowToken, int offset) {
        grid.put(offset + 0, yellowToken);
        grid.put(offset + 1, yellowToken);
        grid.put(offset + 2, yellowToken);
        grid.put(offset + 3, yellowToken);
    }


}
