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

        /**
         * At the start of each battle, creates a new enemy with a new set of
         * health and energy values.
         *
         * @param enemy
         *                the sequence to be set up or re-set up
         * @param healthMax
         *                health that increases by 1 or 2 by each new battle
         *                fought
         * @param energyMax
         *                energy that gradually increases every few battles
         *                fought
         * @return enemy sequence with updated information
         * @ensures enemy = #enemy + newHealthMax and newEnergyMax
         *
         */

        Sequence<Integer> updateEnemy(Sequence<Integer> enemy, int healthMax,
                        int energyMax);

        /**
         * At the start of each battle, creates a new enemy with a new set of
         * health and energy values.
         *
         * @param player
         *                the sequence to be updated
         * @param health
         *                health to be updated
         * @param energy
         *                energy to be updated
         * @return player sequence with updated information
         * @ensures player = #player + newHealth and newEnergy
         *
         */

        Sequence<Integer> updatePlayer(Sequence<Integer> player, int health,
                        int energy);

        /**
         * When enemy health is depleted, gives player experience, tells them
         * the number of rounds it took, as well of which battle the player is
         * on.
         *
         * @param battleNumber
         *                the i value of the roundPlay for loop that gives the
         *                current round value
         * @param roundsTaken
         *                the number of rounds it has taken to defeat an enemy
         * @param out
         *                output information to user
         * @return player experience value
         * @ensures totalPlayerExperience += experience
         *
         */
        int battleOver(int battleNumber, int roundsTaken, SimpleWriter out);

        /**
         * Similar to attackOrDefend, but from the perspective of an AI enemy.
         * Method will decide whether or not to attack or defend based on the
         * level of enemy energy. The decision to save for a strong attack will
         * be random.
         *
         * @param enemy
         *                checks enemy health
         * @param player
         *                checks player health
         * @return true if both have their health, and false if one or both is
         *         dead
         * @ensures playerHealth && enemyHealth > 0 == true and false otherwise
         *
         */
        boolean checkAlive(Sequence<Integer> player, Sequence<Integer> enemy);

        /**
         * after a sequence is created, sets the values for the max health and
         * energy that the user gives themselves.
         *
         * @param player
         *                the sequence to be set up
         * @param healthMax
         *                the user-given health to set as max for the player
         * @param energyMax
         *                user-given energy to set as max for the player
         * @return player sequence with updated information
         * @ensures player = #player + healthMax and energyMax
         *
         */
        Sequence<Integer> createCharacter(Sequence<Integer> player,
                        int healthMax, int energyMax);

        /**
         * When reaching end message, displays information about the game, and
         * asks if the player wants to play again
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
        void endMessage(int roundsPlayed, int totalDamageDealt, SimpleReader in,
                        SimpleWriter out);

        /**
         * Begins the game, collects user input to customize the experience and
         * create the player character.
         *
         * @ensures player == (user Input) && rounds = (user input)
         *
         */
        void gameStart();

        //info grabber methods

        /**
         * Returns the players current health.
         *
         * @return the players current health
         * @ensures currentHealth = player.health
         */
        int currentHealth();

        /**
         * Returns the players current energy.
         *
         * @return the players current energy
         * @ensures currentEnergy = player.energy
         */
        int currentEnergy();

        /**
         * Returns whether the player's energy is high enough to use a special
         * attack
         *
         * @return true if energy > specialAttackThreshhold
         * @ensures canUseSpecialAttack = (playerEnergy >
         *          SpecialAttackThreshhold)
         */
        boolean canUseSpecialAttack();

        /**
         * Returns the players current energy.
         *
         * @return the enemy's current health
         * @ensures currentEnemyHealth = enemy.health
         */
        int currentEnemyHealth();

        /**
         * Returns the enemy's current energy.
         *
         * @return the enemy's current energy
         * @ensures currentEnemyEnergy = enemy.energy
         */
        int currentEnemyEnergy();

}
