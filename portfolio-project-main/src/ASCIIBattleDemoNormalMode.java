
import components.ASCIIBattle;
import components.ASCIIBattle1L;

/**
 *
 * Extremely basic demo for ASCIIBATTLE. This user wants to jump into the
 * default "Normal" game mode that allows the user to choose how many rounds
 * that they want to play.
 */
public class ASCIIBattleDemoNormalMode {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        ASCIIBattle gameDemo = new ASCIIBattle1L();
        gameDemo.gameStart("Normal");

    }
}
