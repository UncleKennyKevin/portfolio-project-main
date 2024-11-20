package components;

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
        private final int specialAttackThreshold = 30;

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
         * health returned to player when using a health potion, negative to
         * show health coming back.
         */
        private final int healthPotion = -35;

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
                final int initialPotions = 0;

                this.createNewRep();
                this.player.add(0, initialPlayerNum);
                this.player.add(1, initialPlayerNum);
                this.player.add(2, initialPotions);
                this.enemy.add(0, initialPlayerNum);
                this.enemy.add(1, initialEnemyNum);
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
         * @param startingPotions
         *                user given potions value
         */
        public ASCIIBattle1L(int playerHealth, int playerEnergy,
                        int enemyHealth, int enemyEnergy, int startingPotions) {
                this.createNewRep();
                assert playerHealth > 0 : "Violation of: player health > 0";
                assert playerEnergy > 0 : "Violation of: player energy > 0";
                assert enemyHealth > 0 : "Violation of: enemy health > 0";
                assert enemyEnergy > 0 : "Violation of: enemy energy > 0";

                this.player.add(0, playerHealth);
                this.player.add(1, playerEnergy);
                this.player.add(2, startingPotions);
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
                out.println("Rolled a " + rollResults + "! ");

                return rollResults;
        }

        @Override
        public final String attackOrDefend(String playerName, SimpleReader in,
                        SimpleWriter out) {

                String userChoice = "";
                String playerChoice = "";

                out.println();
                out.println("It's Your Turn! -------");
                out.println();
                out.println("Pick Your Options (Remember caps lock) : ");
                out.println(" 'A' = Attack");
                out.println(" 'B' = Block");
                out.println(" 'S' = Special Attack ");
                out.println(" 'P' = Use a Potion ");
                out.println("Your current stats: ");
                out.println("Health: " + this.currentHealth());
                out.println("Energy: " + this.currentEnergy());
                out.println("Enemy Stats: ");
                out.println("Enemy Health: " + this.currentEnemyHealth());
                out.println("Enemy Energy: " + this.currentEnemyEnergy());
                out.println("----------------------");
                out.print("Pick an option!!   ");

                boolean correctOption = false;

                //loops until player chooses a correct option
                while (!correctOption) {

                        userChoice = in.nextLine();
                        if (userChoice.equals("S") || (userChoice.equals("B"))
                                        || (userChoice.equals("A"))
                                        || ((userChoice.equals("S") && this
                                                        .currentEnergy() >= this.specialAttackThreshold))
                                        || (userChoice.equals("P") && (this
                                                        .currentPotions() >= 1))) {
                                correctOption = true;
                        } else {
                                out.print("Oops, you've made a mistake, try again!   ");
                        }
                }

                if (userChoice.equals("S")) {
                        int randomVal = this.diceRoll(out);
                        if (randomVal >= this.three) {
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
                } else if (userChoice.equals("P")) {
                        this.editCharacter(this.healthPotion, 0, 1);
                        out.println("You used potion and gained 35 Health!");
                }

                out.print("Press anything to continue!   ");
                userChoice = in.nextLine();

                return playerChoice;
        }

        @Override
        public final void enemyTurn(String playerName, String playerChoice,
                        SimpleWriter out) {
                out.println();
                out.println("Enemy Turn----------");
                out.println();

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
                        out.println("Enemy Chose Block!!");
                } else if ((playChoiceResults >= 2)
                                && (playChoiceResults <= this.four)) {
                        int randomVal = this.diceRoll(out);
                        if (randomVal >= 2) {
                                enemyChoice = "Attack";
                                out.println("Enemy Attacked!");
                        } else {
                                enemyChoice = "Miss";
                                out.println("Enemy Missed Attack!");
                        }
                } else if (playChoiceResults <= this.six) {
                        int randomVal = this.diceRoll(out);
                        if (randomVal >= this.three) {
                                enemyChoice = "SpecialAttack";
                                out.println("Enemy Used Special Attack!");
                        } else {
                                enemyChoice = "Miss";
                                out.println("Enemy Missed a Special Attack!");
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
                        playerEnergyRemoval = this.specialAttackThreshold;
                        if (enemyChoice.equals("Block")) {
                                out.println("Enemy blocks " + playerName
                                                + "'s super attack!");
                                playerDamage = (specialAttackDamage / 2);
                                out.println("Enemy takes " + playerDamage
                                                + " points of damage!");
                        } else {
                                out.println("Enemy is hit by " + playerName
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
                        enemyEnergyRemoval = this.specialAttackThreshold;
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

                this.editCharacter(enemyDamage, playerEnergyRemoval, 0);
                this.editEnemy(playerDamage, enemyEnergyRemoval);

                out.println("Round complete. ");

                out.println();

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
                final int energyMax = 50;
                final int healthPotionChance = 4;

                Random battleOverRandom = new Random();

                int newXp = battleOverRandom.nextInt(xpMax);
                int newEnergy = battleOverRandom.nextInt(energyMax);
                int chanceOfPotion = battleOverRandom
                                .nextInt(healthPotionChance);

                if (chanceOfPotion == this.four) {
                        this.editCharacter(0, 0, -1);
                        out.println("Hooray! You found a potion in the enemy's pocket!");
                }

                this.editCharacter(0, -newEnergy, 0);
                int finalXp = xpValue + newXp;

                out.println("BATTLE NUMBER " + battleNumber
                                + " FINISHED!!!!!!!!");
                out.println("----------------------");
                out.println("It took you " + roundsTaken + " rounds to win.");
                out.println("You earned " + newXp + "XP!!");
                out.println("You regained " + newEnergy + " points of energy!");
                out.println("Get ready for the next round!");

                return finalXp;
        }

        @Override
        public final boolean checkAlive() {
                return this.enemy.entry(0) > 0 && this.player.entry(0) > 0;
        }

        @Override
        public final void editCharacter(int healthChange, int energyChange,
                        int potionsChange) {
                int originalHealth = this.player.entry(0);
                int originalEnergy = this.player.entry(1);
                int originalPotions = this.player.entry(2);

                int removalVal = 0;

                int newHealth = originalHealth - healthChange;
                int newEnergy = originalEnergy - energyChange;
                int newPotions = originalPotions - potionsChange;

                if (newEnergy < 0) {
                        newEnergy = 0;
                }

                removalVal = this.player.replaceEntry(0, newHealth);
                removalVal = this.player.replaceEntry(1, newEnergy);
                removalVal = this.player.replaceEntry(2, newPotions);
        }

        @Override
        public final void editEnemy(int healthChange, int energyChange) {
                int originalHealth = this.enemy.entry(0);
                int originalEnergy = this.enemy.entry(1);

                int removalVal = 0;

                int newHealth = originalHealth - healthChange;
                int newEnergy = originalEnergy - energyChange;

                if (newEnergy < 0) {
                        newEnergy = 0;
                }

                removalVal = this.enemy.replaceEntry(0, newHealth);
                removalVal = this.enemy.replaceEntry(1, newEnergy);
        }

        @Override
        public final void endMessage(int roundsPlayed, int totalDamageDealt,
                        SimpleReader in, SimpleWriter out) {

                //THIS ONE NEEDS MORE WORK

                out.println("GAME OVER");
                out.println("You survived " + roundsPlayed + " rounds");
                out.println("Overall, you dealt " + totalDamageDealt
                                + " points of damage");
                out.print("Play again? Y/N");
                String playerResponse = in.nextLine();

                if (playerResponse.equals("Y")) {
                        this.gameStart();
                } else {
                        out.println("Goodbye!!");
                }

        }

        @Override
        public final void gameStart() {

                SimpleReader in = new SimpleReader1L();
                SimpleWriter out = new SimpleWriter1L();
                out.println("-----------------------");
                out.println();
                out.println("WELCOME TO ASCIIBATTLE!!!!!");
                out.println("Enter the following values:");

                out.print("Player Name: ");
                String playerName = in.nextLine();

                out.print("Player Health: ");
                int playerHealth = Integer.parseInt(in.nextLine());

                out.print("Energy of " + playerName + ": ");
                int playerEnergy = Integer.parseInt(in.nextLine());

                out.print("Initial Enemy Health: ");
                int enemyHealth = Integer.parseInt(in.nextLine());

                out.print("Energy of Initial Enemy: ");
                int enemyEnergy = Integer.parseInt(in.nextLine());
                out.print("How many starting potions do you want?: ");

                int numPotions = Integer.parseInt(in.nextLine());
                out.print("How Many Battles Do You Want To Fight? ");

                int battles = Integer.parseInt(in.nextLine());

                this.editCharacter(-playerHealth, -playerEnergy, -numPotions);
                this.editEnemy(-enemyHealth, -enemyEnergy);

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
        public final int currentPotions() {
                return this.player.entry(2);
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
