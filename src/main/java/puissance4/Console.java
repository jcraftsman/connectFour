package puissance4;

public class Console {

    public void display(Grid grid, Referee referee) {
        System.out.println(grid.prettyPrint());
        System.out.println(referee.print());
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
