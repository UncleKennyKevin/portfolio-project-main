
import components.ASCIIBattle;
import components.ASCIIBattle1L;

/**
 *
 * Endless mode demo for ASCIIBATTLE. Players do not have to choose how many
 * rounds they would like to play. Instead, there will be 2,147,483,647, the
 * highest integer value possible. It is not technically endless, but unlikely
 * that any player would ever play that long. That value is also the highest
 * number of rounds that a player can choose in the normal mode as well.
 */
public class ASCIIBattleDemoEndlessMode {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        ASCIIBattle gameDemo = new ASCIIBattle1L();
        gameDemo.gameStart("Endless");

    }
}
