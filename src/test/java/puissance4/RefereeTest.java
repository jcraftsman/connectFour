package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static puissance4.CellState.RED_TOKEN;
import static puissance4.CellState.YELLOW_TOKEN;

public class RefereeTest {


    private Referee referee;
    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = new Grid();
        referee = new Referee(grid);
    }

    @Test
    public void whoWillPlay_should_start_with_yellowToken() {
        //When
        CellState turn = referee.whoWillPlay();

        //Then
        assertThat(turn).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void whoWillPlay_should_return_redToken_after_playing() {
        //Given
        referee.play(0);

        //When
        CellState turn = referee.whoWillPlay();

        //Then
        assertThat(turn).isEqualTo(RED_TOKEN);
    }

    @Test
    public void whoWillPlay_should_return_yellowToken_after_redToken() {
        //Given
        referee.play(0);
        referee.play(1);

        //When
        CellState turn = referee.whoWillPlay();

        //Then
        assertThat(turn).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void play_should_put_on_the_same_column_on_the_grid() {
        //When
        referee.play(0);

        //Then
        CellState cellState = grid.get(0, 0);
        assertThat(cellState).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void isFinished_should_return_true_when_there_is_a_draw() {
        //Given
        draw();

        //When
        boolean finished = referee.isFinished();

        //Then
        assertThat(finished).isTrue();
    }

    @Test
    public void isFinished_should_return_true_when_there_is_a_win() {
        //Given
        grid.put(0, CellState.RED_TOKEN);
        grid.put(0, CellState.RED_TOKEN);
        grid.put(0, CellState.RED_TOKEN);
        grid.put(0, CellState.RED_TOKEN);

        //When
        boolean finished = referee.isFinished();

        //Then
        assertThat(finished).isTrue();
    }

    @Test
    public void theWinnerIs_should_return_YellowToken_when_he_wins() {
        //Given
        grid.put(0, CellState.YELLOW_TOKEN);
        grid.put(0, CellState.YELLOW_TOKEN);
        grid.put(0, CellState.YELLOW_TOKEN);
        grid.put(0, CellState.YELLOW_TOKEN);

        //When
        CellState theWinner = referee.theWinnerIs();

        //Then
        assertThat(theWinner).isEqualTo(YELLOW_TOKEN);
    }

    private void draw() {
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

    }
}
