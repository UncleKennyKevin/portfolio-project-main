
import components.ASCIIBattle;
import components.ASCIIBattle1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * Custom demo of ASCIIBATTLE. Here, the player can set options to play before
 * the game starts. This could be used if a player has a saved playstyle and
 * doesn't want to go through the hassle of starting the game and typing all of
 * these values in themselves. If they want to skip straight to the action, they
 * can lay out the values they want beforehand.
 */
public class ASCIIBattleDemoAlternateStart {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String myName = "Stefano";
        int battlesIWant = 12;

        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        int healthIdWant = 50;
        int energyIdWant = 85;
        int enemyStartingHealthIWant = 12;
        int enemyStartingEnergyIWant = 50;
        int numStartingPotionsIWant = 4;

        ASCIIBattle customGameDemo = new ASCIIBattle1L(healthIdWant,
                energyIdWant, enemyStartingHealthIWant,
                enemyStartingEnergyIWant, numStartingPotionsIWant);

        out.println(customGameDemo.toString());
        customGameDemo.roundPlay(myName, battlesIWant, in, out);

        in.close();
        out.close();
    }
}
