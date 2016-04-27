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
        wins(CellState.RED_TOKEN);

        //When
        boolean finished = referee.isFinished();

        //Then
        assertThat(finished).isTrue();
    }

    @Test
    public void theWinnerIs_should_return_YellowToken_when_he_wins() {
        //Given
        wins(CellState.YELLOW_TOKEN);

        //When
        CellState theWinner = referee.theWinnerIs();

        //Then
        assertThat(theWinner).isEqualTo(YELLOW_TOKEN);
    }

    @Test
    public void print_should_return_next_player_red_when_who_will_play_is_red_token() {
        //Given
        referee.play(0);

        //When
        String message = referee.print();

        //Then
        assertThat(message).isEqualTo("Rouge colonne [1-7] : ");
    }

    @Test
    public void print_should_return_next_player_yellow_when_who_will_play_is_yellow_token() {
        //When
        String message = referee.print();

        //Then
        assertThat(message).isEqualTo("Jaune colonne [1-7] : ");
    }

    @Test
    public void print_should_return_winner_red_when_red_wins() {
        //Given
        wins(RED_TOKEN);

        //When
        String message = referee.print();

        //Then
        assertThat(message).startsWith("Rouge gagne la partie, bravo :)");
    }

    @Test
    public void print_should_return_winner_yellow_when_yellow_wins() {
        //Given
        wins(YELLOW_TOKEN);

        //When
        String message = referee.print();

        //Then
        assertThat(message).startsWith("Jaune gagne la partie, bravo :)");
    }

    @Test
    public void print_should_return_draw_when_no_wins() {
        //Given
        draw();

        //When
        String message = referee.print();

        //Then
        assertThat(message).isEqualTo("Egalité : fin de partie");
    }

    private void wins(CellState redToken) {
        grid.put(0, redToken);
        grid.put(0, redToken);
        grid.put(0, redToken);
        grid.put(0, redToken);
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
