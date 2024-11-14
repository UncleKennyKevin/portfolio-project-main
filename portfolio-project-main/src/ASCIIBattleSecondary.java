import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplewriter.SimpleWriter;

/**
 * Layered implementations of secondary methods for {@code DiscGolfScorecard}.
 */

public abstract class ASCIIBattleSecondary implements ASCIIBattle {

    /*
     * Common methods (from Object) -------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Stats(");
        result.append("PlayerHealth-").append(this.currentHealth())
                .append(", ");
        result.append("PlayerEnergy- ").append(this.currentEnergy())
                .append(", ");
        result.append("CanUseSpecialAttack- ")
                .append(this.canUseSpecialAttack());

        return result.append(")").toString();

    }

    //will complete equals method as kernel implementation is completed

    /*
     * Other non-kernel methods ------------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void roundPlay(Sequence<Integer> player, int numBattles,
            SimpleReader in, SimpleWriter out) {

        final int enemyStartingHealth = 10;
        final int enemyStartingEnergy = 10;

        Sequence<Integer> enemy = new Sequence1L<Integer>();
        int enemyHealthMax = enemyStartingHealth;
        int enemyEnergyMax = enemyStartingEnergy;
        int roundsTaken = 0;
        int playerExperience = 0;

        boolean roundCheck = true;

        for (int i = 0; i < numBattles; i++) {
            while (roundCheck) {
                this.updateEnemy(enemy, enemyHealthMax, enemyEnergyMax);
                this.attackOrDefend(player, enemy, in, out);
                this.enemyTurn(enemy, player, out);
                roundCheck = this.checkAlive(player, enemy);
                roundsTaken++;
            }
            playerExperience += this.battleOver(i, roundsTaken, out);
        }

    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void playerDead(Sequence<Integer> player, int roundsPlayed,
            int totalDamageDealt, SimpleReader in, SimpleWriter out) {
        int chanceToRevive = this.diceRoll(out);
        if (chanceToRevive > 5) {
            this.updatePlayer(player, 1, 1);
        } else {

            this.endMessage(roundsPlayed, totalDamageDealt, in, out);

            /*
             * Note To Self: End message method will look like this
             * out.println("GAME OVER"); out.println("You survived " +
             * roundsPlayed + " rounds"); out.println("Overall, you dealt " +
             * totalDamageDealt + " points of damage");
             * out.println("Play again? y/n"); String playerResponse =
             * in.nextLine();
             * 
             * if(playerResponse.equals("y")){ gameStart(); } else{
             * out.println("Goodbye!!"); }
             */
        }

    }
}
