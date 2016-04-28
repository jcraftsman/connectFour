package puissance4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleTest {

    @InjectMocks
    private Console console;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void display_should_print_grid() {
        //Given
        Grid grid = mock(Grid.class);
        Referee referee = mock(Referee.class);

        //When
        console.display(grid, referee);

        //Then
        verify(grid).emojiPrint();
    }

    @Test
    public void display_should_print_who_will_play() {
        //Given
        Grid grid = mock(Grid.class);
        Referee referee = mock(Referee.class);

        //When
        console.display(grid, referee);

        //Then
        verify(referee).print();
    }


}