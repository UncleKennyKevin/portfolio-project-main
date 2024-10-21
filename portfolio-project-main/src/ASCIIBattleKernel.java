import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplewriter.SimpleWriter;
import components.standard.Standard;

/**
 * ASCIIBattle kernel component with primary methods.
 */
public interface ASCIIBattleKernel extends Standard<ASCIIBattle> {

        /**
         * Returns a randomly generated integer from 1 to 6, and displays a die
         * to the user.
         *
         * @param out
         *                display information to user
         * @return random integer from 1 to 6
         * @ensures diceRollNumber = [1,6] and out = diceRollNumber
         *
         */
        int diceRoll(SimpleWriter out);

        /**
         * When player runs out of health, ends the game, and gives some
         * information about the playtime, and the total damage. Then, asks if
         * the player wants to try again or not.
         *
         * @param roundsPlayed
         *                information on how long the player survived
         * @param totalDamageDealt
         *                information on how much damage the player dealt while
         *                alive
         * @param in
         *                take in user information
         * @param out
         *                display information to user
         * @ensures out = [information on player data, option to play again]
         *
         */
        void playerDead(int roundsPlayed, int totalDamageDealt, SimpleReader in,
                        SimpleWriter out);

        /**
         * Gives player options on their turn: attack, strongAttack, and defend.
         * Then using that info, either rolls to see strength of attack and
         * lands on enemy, or temporarily increases player defense. Attacks
         * deplete all energy, so players may decide to defend in order to save
         * up for a devistating attack
         *
         * @param player
         *                player information as to decide whether or not
         *                strongAttack can be used and to deplete energy levels
         *                after an attack
         * @param enemy
         *                reduce enemy health if attack lands
         *
         * @param in
         *                take in user information
         * @param out
         *                display information to user
         * @ensures enemyHealth -= playerAttack and playerEnergy = 0 ||
         *          playerDefense++
         *
         */
        void attackOrDefend(Sequence<Integer> player, Sequence<Integer> enemy,
                        SimpleReader in, SimpleWriter out);

        /**
         * Similar to attackOrDefend, but from the perspective of an AI enemy.
         * Method will decide whether or not to attack or defend based on the
         * level of enemy energy. The decision to save for a strong attack will
         * be random.
         *
         * @param enemy
         *                enemy information as to decide whether or not
         *                strongAttack can be used and to deplete energy levels
         *                after an attack
         * @param player
         *                reduce player health if attack lands
         * @param out
         *                display information to user
         * @returns true if attack, false if defend
         * @ensures playerHealth -= enemyAttack and enemyEnergy = 0 ||
         *          enemyDefense++
         *
         */
        void enemyTurn(Sequence<Integer> enemy, Sequence<Integer> player,
                        SimpleWriter out);

}
