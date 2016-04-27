package puissance4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @InjectMocks
    private Application application;
    @Mock
    private Console console;
    @Mock
    private Grid grid;
    @Mock
    private Referee referee;

    @Before
    public void setUp() throws Exception {
        when(referee.isFinished()).thenReturn(false, true);

    }

    @Test
    public void start_should_display_play() {
        //When
        application.start();

        //Then
        verify(console, times(2)).display(grid, referee);
    }

    @Test
    public void start_should_read_column() {
        //When
        application.start();

        //Then
        verify(console).read();
    }

    @Test
    public void start_should_play_on_played_column() {
        //Given
        int playedColumn = 42;
        when(console.read()).thenReturn(playedColumn);

        //When
        application.start();

        //Then
        verify(referee).play(playedColumn);
    }

    @Test
    public void start_should_end_when_finished() {
        //Given
        when(referee.isFinished()).thenReturn(false, false, true);

        //When
        application.start();

        //Then
        verify(referee, times(2)).play(anyInt());
    }

    @Test
    public void start_should_display_final_status_when_finished() {
        //Given
        when(referee.isFinished()).thenReturn(true);

        //When
        application.start();

        //Then
        verify(console).display(grid, referee);
    }

    @Test
    public void start_should_replay_when_numberFormatException() {
        //Given
        when(console.read()).thenThrow(NumberFormatException.class);

        //When
        application.start();

        //Then
        verify(referee,never()).play(anyInt());
    }

    @Test
    public void start_should_display_TryAgain_when_numberFormatException() {
        //Given
        when(console.read()).thenThrow(NumberFormatException.class);

        //When
        application.start();

        //Then
        verify(console).display("Mais qu'est ce qui vous arrive. Il faut saisir un nombre entre 1 et 7 :) ");
    }
}
