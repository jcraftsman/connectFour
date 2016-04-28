package puissance4;

import static puissance4.CellState.RED_TOKEN;
import static puissance4.CellState.YELLOW_TOKEN;

public class Console {

    public void display(Grid grid, Referee referee) {
        System.out.println(grid.emojiPrint());
        System.out.println(referee.print());
    }

    private void display(Grid grid) {
        String format = grid.prettyPrint().replace("" + RED_TOKEN.print(), RED_TOKEN.printEmoji()).replace("" + YELLOW_TOKEN.print(), YELLOW_TOKEN.printEmoji());
        System.out.println(format);
    }

    public int read() throws NumberFormatException {
        int number = Integer.valueOf(System.console().readLine()) - 1;
        clear();
        return number;
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
    }

    public void display(String message) {
        System.out.println(message);
    }
}
