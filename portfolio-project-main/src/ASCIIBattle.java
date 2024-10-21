import components.sequence.Sequence;
import components.simplewriter.SimpleWriter;

/**
 * {@code ASCIIBattleKernel} enhanced with secondary methods.
 */
public interface ASCIIBattle extends ASCIIBattleKernel {

    /**
     * When enemy health is depleted, gives player experience, tells them the
     * number of rounds it took, as well of which battle the player is on.
     * 
     * @param battleNumber
     *            the i value of the roundPlay for loop that gives the current
     *            round value
     * @param roundsTaken
     *            the number of rounds it has taken to defeat an enemy
     * @param out
     *            output information to user
     * @return player experience value
     * @ensures totalPlayerExperience += experience
     *
     */
    int battleOver(int battleNumber, int roundsTaken, SimpleWriter out);

    /**
     * after a sequence is created, sets the values for the max health and
     * energy that the user gives themselves.
     * 
     * @param player
     *            the sequence to be set up
     * @param healthMax
     *            the user-given health to set as max for the player
     * @param energyMax
     *            user-given energy to set as max for the player
     * @return player sequence with updated information
     * @ensures player = #player + healthMax and energyMax
     *
     */
    Sequence<Integer> createCharacter(Sequence<Integer> player, int healthMax,
            int energyMax);

    /**
     * At the start of each battle, creates a new enemy with a new set of health
     * and energy values.
     * 
     * @param enemy
     *            the sequence to be set up or re-set up
     * @param healthMax
     *            health that increases by 1 or 2 by each new battle fought
     * @param energyMax
     *            energy that gradually increases every few battles fought
     * @return enemy sequence with updated information
     * @ensures enemy = #enemy + newHealthMax and newEnergyMax
     *
     */
    Sequence<Integer> updateEnemy(Sequence<Integer> enemy, int healthMax,
            int energyMax);

    /**
     * Controls the game, contains a nested for loop that goes for as many
     * battles that the player gives, and prompts the user to make a decision.
     * Then runs the enemy attack decision, then gives player experience if the
     * enemy has run out of health.
     * 
     * @param player
     *            player information so that the game can function
     * @param numBattles
     *            the numbeor f player-given rounds the game will run
     * @param out
     *            output information to user
     * @ensures player = [dead] || player = [won battles]
     *
     */
    void roundPlay(Sequence<Integer> player, int numBattles, SimpleWriter out);

}
