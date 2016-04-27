package puissance4;

public class Application {

    private static final String WRONG_ENTRY_MSG = "Mais qu'est ce qui vous arrive. Il faut saisir un nombre entre 1 et 7 :) ";
    private Console console;
    private Grid grid;
    private Referee referee;

    public Application(Console console, Grid grid, Referee referee) {
        this.console = console;
        this.grid = grid;
        this.referee = referee;
    }

    public void start() {
        while (!referee.isFinished()) {
            console.display(grid, referee);
            try {
                int playedColumn = console.read();
                referee.play(playedColumn);
            } catch (NumberFormatException ne) {
                console.display(WRONG_ENTRY_MSG);
            }
        }
        console.display(grid, referee);
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        Referee referee = new Referee(grid);
        Console console = new Console();

        Application application = new Application(console, grid, referee);
        application.start();
        System.exit(0);
    }

}
