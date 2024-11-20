import java.util.Random;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implementation of Kernel and and primary methods.
 *
 * @convention this.player is a Sequence && this.enemy is a Sequence and health
 *             and energy >= 0
 * @correspondence this.player entry 0 is health, entry 1 is energy, same with
 *                 enemy
 * @author Kevin Roback
 */
public class ASCIIBattle1L extends ASCIIBattleSecondary {

        /**
         * special attack energy value.
         */
        private final int specialAttackThreshold = 50;

        /**
         * allow the number 3 to not be considered magic in processing attacks
         * and dice rolls.
         */
        private final int three = 3;

        /**
         * allow the number 4 to not be considered magic in processing attacks
         * and dice rolls.
         */
        private final int four = 4;

        /**
         * allow the number 5 to not be considered magic in processing attacks
         * and dice rolls.
         */
        private final int five = 5;

        /**
         * allow the number 6 to not be considered magic in processing attacks
         * and dice rolls.
         */
        private final int six = 6;

        /**
         * value to hopefully reset energy value after using a special attack.
         */
        private final int specialAttackReset = 10000;
        /**
         * user-controlled player representation.
         */
        private Sequence<Integer> player;

        /**
         * Generic enemy that player will battle against representation.
         */
        private Sequence<Integer> enemy;

        /**
         * Creating first representation.
         */
        private void createNewRep() {

                this.player = new Sequence1L<Integer>();
                this.enemy = new Sequence1L<Integer>();
        }

        /**
         * Constructor for player and enemy with default values.
         */
        public ASCIIBattle1L() {
                final int initialPlayerNum = 10;
                final int initialEnemyNum = 5;

                this.createNewRep();
                this.player.add(0, initialPlayerNum);
                this.player.add(1, initialPlayerNum);
                this.enemy.add(0, initialPlayerNum);
                this.player.add(1, initialEnemyNum);
        }

        /**
         * Constructor for player given user presented values.
         *
         * @param playerHealth
         *                user given player health value
         * @param playerEnergy
         *                user given player energy value
         * @param enemyHealth
         *                user given enemy health value
         * @param enemyEnergy
         *                user given enemy energy value
         */
        public ASCIIBattle1L(int playerHealth, int playerEnergy,
                        int enemyHealth, int enemyEnergy) {
                this.createNewRep();
                assert playerHealth > 0 : "Violation of: player health > 0";
                assert playerEnergy > 0 : "Violation of: player energy > 0";
                assert enemyHealth > 0 : "Violation of: enemy health > 0";
                assert enemyEnergy > 0 : "Violation of: enemy energy > 0";

                this.player.add(0, playerHealth);
                this.player.add(1, playerEnergy);
                this.enemy.add(0, enemyHealth);
                this.player.add(1, enemyEnergy);
        }

        /*
         * Standard methods
         * -------------------------------------------------------
         */

        @Override
        public final ASCIIBattle1L newInstance() {
                return new ASCIIBattle1L();
        }

        @Override
        public final void clear() {
                this.player.clear();
                this.enemy.clear();
        }

        @Override
        public final void transferFrom(ASCIIBattle source) {
                assert source != null : "Violation of: source is not null";
                assert source != this : "Violation of: source is not this";

                ASCIIBattle1L localSource = (ASCIIBattle1L) source;
                this.player.transferFrom(localSource.player);
                this.enemy.transferFrom(localSource.enemy);
        }

        @Override
        public final int diceRoll(SimpleWriter out) {

                final int diceMax = 6;

                Random diceRoll = new Random();
                int rollResults = diceRoll.nextInt(diceMax);

                switch (rollResults) {
                        case 1:
                                out.println("-------");
                                out.println("|     |");
                                out.println("|  *  |");
                                out.println("|     |");
                                out.println("-------");
                                break;
                        case 2:
                                out.println("-------");
                                out.println("| *   |");
                                out.println("|     |");
                                out.println("|   * |");
                                out.println("-------");
                                break;
                        case three:
                                out.println("-------");
                                out.println("| *   |");
                                out.println("|  *  |");
                                out.println("|   * |");
                                out.println("-------");
                                break;
                        case four:
                                out.println("-------");
                                out.println("| * * |");
                                out.println("|     |");
                                out.println("| * * |");
                                out.println("-------");
                                break;
                        case five:
                                out.println("-------");
                                out.println("| * * |");
                                out.println("|  *  |");
                                out.println("| * * |");
                                out.println("-------");
                                break;
                        case six:
                                out.println("-------");
                                out.println("| * * |");
                                out.println("| * * |");
                                out.println("| * * |");
                                out.println("-------");
                                break;
                        default:
                                break;
                }

                out.println();
                out.println("You rolled a " + rollResults + "! ");

                return rollResults;
        }

        @Override
        public final String attackOrDefend(String playerName, SimpleReader in,
                        SimpleWriter out) {

                String userChoice = "";
                String playerChoice = "";

                out.println("It's Your Turn! -------");
                out.println("Pick Your Options (Remember caps lock) : ");
                out.println(" 'A' = Attack");
                out.println(" 'B' = Block");
                out.println(" 'S' = Special Attack ");
                out.println("Your current stats: ");
                out.println("Health: " + this.currentHealth());
                out.println("Energy: " + this.currentEnergy());
                out.println("Enemy Stats: ");
                out.println("Enemy Health: " + this.currentEnemyHealth());
                out.println("Enemy Energy: " + this.currentEnemyEnergy());
                out.println("----------------------");
                out.print("Pick an option!!");

                boolean correctOption = false;

                //loops until player chooses a correct option
                while (!correctOption) {

                        userChoice = in.nextLine();
                        if (userChoice.equals("S") || (userChoice.equals("B"))
                                        || ((userChoice.equals("S") && this
                                                        .currentEnergy() >= this.specialAttackThreshold))) {
                                correctOption = true;
                        } else {
                                out.print("Oops, you've made a mistake, try again!");
                        }
                }

                if (userChoice.equals("S")) {
                        int randomVal = this.diceRoll(out);
                        if (randomVal >= 3) {
                                out.println("WOW!!! You used Special Attack against the enemy!");
                                playerChoice = "SpecialAttack";
                        } else {
                                out.println("You sadly missed your attack!");
                                playerChoice = "Miss";
                        }

                } else if (userChoice.equals("A")) {
                        int randomVal = this.diceRoll(out);
                        if (randomVal >= 2) {
                                out.println("You used attack!");
                                playerChoice = "Attack";
                        } else {
                                out.println("Sadly, you missed the attack!");
                                playerChoice = "Miss";
                        }
                } else if (userChoice.equals("B")) {
                        playerChoice = "Block";
                }

                in.close();
                out.close();
                return playerChoice;
        }

        @Override
        public final void enemyTurn(String playerName, String playerChoice,
                        SimpleWriter out) {
                out.println("Enemy Turn----------");

                final int attackDamage = 3;
                final int specialAttackDamage = 8;

                int enemyDamage = 0;
                int playerDamage = 0;

                int enemyEnergyRemoval = 0;
                int playerEnergyRemoval = 0;

                String enemyChoice = "";

                int energy = this.enemy.entry(1);
                int specialAttackOption = 0;

                if (energy >= this.specialAttackThreshold) {
                        specialAttackOption = 2;
                }

                final int options = 4;

                Random playChoice = new Random();
                int playChoiceResults = playChoice
                                .nextInt(options + specialAttackOption);

                //calculate choice
                if (playChoiceResults == 1) {
                        enemyChoice = "Block";
                } else if ((playChoiceResults >= 2)
                                && (playChoiceResults <= this.four)) {
                        int randomVal = this.diceRoll(out);
                        if (randomVal >= 2) {
                                enemyChoice = "Attack";
                        } else {
                                enemyChoice = "Miss";
                        }
                } else if (playChoiceResults <= this.six) {
                        int randomVal = this.diceRoll(out);
                        if (randomVal >= this.three) {
                                enemyChoice = "SpecialAttack";
                        } else {
                                enemyChoice = "Miss";
                        }
                }

                //gather player information, process choice
                if (playerChoice.equals("Attack")) {
                        if (enemyChoice.equals("Block")) {
                                out.println("Enemy blocks attack!");
                                playerDamage = 0;
                                out.println("Enemy takes no damage!");
                        } else {
                                out.println(playerName + " lands attack!");
                                playerDamage = attackDamage;
                                out.println("Enemy takes " + playerDamage
                                                + " points of damage!");
                        }
                }

                if (playerChoice.equals("SpecialAttack")) {
                        playerEnergyRemoval = this.specialAttackReset;
                        if (enemyChoice.equals("Block")) {
                                out.println("Enemy blocks + " + playerName
                                                + "'s super attack!");
                                playerDamage = (specialAttackDamage / 2);
                                out.println("Enemy takes " + playerDamage
                                                + " points of damage!");
                        } else {
                                out.println("Enemy is hit by + " + playerName
                                                + "'s super attack!");
                                playerDamage = specialAttackDamage;
                                out.println("Enemy takes " + playerDamage
                                                + " points of damage!");
                        }
                }

                if (enemyChoice.equals("Attack")) {
                        if (playerChoice.equals("Block")) {
                                out.println(playerName
                                                + " blocks the enemy's attack!");
                                enemyDamage = 0;
                                out.println(playerName + " takes no damage!");
                        } else {
                                out.println(playerName
                                                + " is hit by the enemy's attack!");
                                enemyDamage = attackDamage;
                                out.println(playerName + " takes " + enemyDamage
                                                + " points of damage!");
                        }
                }

                if (enemyChoice.equals("SpecialAttack")) {
                        enemyEnergyRemoval = this.specialAttackReset;
                        if (playerChoice.equals("Block")) {
                                out.println(playerName
                                                + " blocks super attack!");
                                enemyDamage = (specialAttackDamage / 2);
                                out.println("Player takes " + enemyDamage
                                                + " points of damage!");
                        } else {
                                out.println(playerName
                                                + " is hit by super attack!!");
                                enemyDamage = specialAttackDamage;
                                out.println(playerName + " takes " + enemyDamage
                                                + " points of damage!");
                        }
                }

                this.editCharacter(enemyDamage, playerEnergyRemoval);
                this.editEnemy(playerDamage, enemyEnergyRemoval);

                out.println("Stats after this round: ");
                out.println(this.toString());

                out.close();
        }

        @Override
        public final void updateEnemy(int healthMax, int energyMax) {
                final int healthIncrease = 5;
                final int energyIncrease = 8;

                int newHealthMax = (healthMax / healthIncrease);
                int newEnergyMax = (energyMax / energyIncrease);

                int startingHealthMax = healthMax + newHealthMax;
                int startingEnergyMax = energyMax + newEnergyMax;

                int changedStartingHealth = this.enemy.replaceEntry(0,
                                startingHealthMax);
                int changedStartingEnergy = this.enemy.replaceEntry(1,
                                startingEnergyMax);

        }

        @Override
        public final int battleOver(int xpValue, int battleNumber,
                        int roundsTaken, SimpleWriter out) {

                final int xpMax = 250;

                Random xpGained = new Random();
                int newXp = xpGained.nextInt(xpMax);

                int finalXp = xpValue + newXp;

                out.println("BATTLE NUMBER " + battleNumber
                                + " FINISHED!!!!!!!!");
                out.println("----------------------");
                out.println("It took you " + roundsTaken + " rounds to win.");
                out.println("You earned " + xpValue + "XP!!");
                out.println("Get ready for the next round!");

                return finalXp;
        }

        @Override
        public final boolean checkAlive() {
                return this.enemy.entry(0) > 0 || this.player.entry(0) > 0;
        }

        @Override
        public final void editCharacter(int healthChange, int energyChange) {
                int originalHealth = this.player.entry(0);
                int originalEnergy = this.player.entry(1);

                int newHealth = originalHealth - healthChange;
                int newEnergy = originalEnergy - energyChange;

                if (newEnergy < 0) {
                        newEnergy = 0;
                }

                int changedHealth = this.player.replaceEntry(0, newHealth);
                int changedEnergy = this.player.replaceEntry(1, newEnergy);
        }

        @Override
        public final void editEnemy(int healthChange, int energyChange) {
                int originalHealth = this.enemy.entry(0);
                int originalEnergy = this.enemy.entry(1);

                int newHealth = originalHealth - healthChange;
                int newEnergy = originalEnergy - energyChange;

                if (newEnergy < 0) {
                        newEnergy = 0;
                }

                int changedHealth = this.enemy.replaceEntry(0, newHealth);
                int changedEnergy = this.enemy.replaceEntry(1, newEnergy);
        }

        @Override
        public final void endMessage(int roundsPlayed, int totalDamageDealt,
                        SimpleReader in, SimpleWriter out) {

                //THIS ONE NEEDS MORE WORK

                out.println("GAME OVER");
                out.println("You survived " + roundsPlayed + " rounds");
                out.println("Overall, you dealt " + totalDamageDealt
                                + " points of damage");
                out.print("Play again? y/n");
                String playerResponse = in.nextLine();

                if (playerResponse.equals("y")) {
                        this.gameStart();
                } else {
                        out.println("Goodbye!!");
                }

        }

        @Override
        public final void gameStart() {

                SimpleReader in = new SimpleReader1L();
                SimpleWriter out = new SimpleWriter1L();

                out.println("WELCOME TO THE SURVIVAL PROJECT!");
                out.println("Enter the following values:");
                out.print("Player Name: ");

                String playerName = in.nextLine();

                out.print("Player Health:");

                int playerHealth = Integer.parseInt(in.nextLine());

                out.print("Energy of " + playerName + ":      ");

                int playerEnergy = Integer.parseInt(in.nextLine());

                out.print("Initial Enemy Health: ");

                int enemyHealth = Integer.parseInt(in.nextLine());

                out.print("Energy of Initial Enemy: ");

                int enemyEnergy = Integer.parseInt(in.nextLine());

                out.print("How Many Battles Do You Want To Fight? ");

                int battles = Integer.parseInt(in.nextLine());

                new ASCIIBattle1L(playerHealth, playerEnergy, enemyHealth,
                                enemyEnergy);

                this.roundPlay(playerName, battles, in, out);

                in.close();
                out.close();
        }

        //info grabber methods

        @Override
        public final int currentHealth() {
                return this.player.entry(0);
        }

        @Override
        public final int currentEnergy() {
                return this.player.entry(1);
        }

        @Override
        public final boolean canUseSpecialAttack() {

                return this.player.entry(1) > this.specialAttackThreshold;
        }

        @Override
        public final int currentEnemyHealth() {
                return this.enemy.entry(0);
        }

        @Override
        public final int currentEnemyEnergy() {
                return this.enemy.entry(1);
        }

}
