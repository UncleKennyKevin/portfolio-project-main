package components;

import components.simplereader.SimpleReader;
import components.simplewriter.SimpleWriter;

/**
 * Layered implementations of secondary methods for {@code ASCIIBattle}.
 */

public abstract class ASCIIBattleSecondary implements ASCIIBattle {

    /*
     * Common methods (from Object) -------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Stats(");
        result.append("PlayerHealth- ").append(this.currentHealth())
                .append(", ");
        result.append("PlayerEnergy- ").append(this.currentEnergy())
                .append(", ");
        result.append("CanUseSpecialAttack- ")
                .append(this.canUseSpecialAttack()).append(", ");
        result.append("Number of Potions- ").append(this.currentPotions());
        result.append(")  Enemy Stats(");
        result.append("EnemyHealth- ").append(this.currentEnemyHealth())
                .append(", ");
        result.append("EnemyEnergy- ").append(this.currentEnemyEnergy());

        return result.append(")").toString();

    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ASCIIBattle)) {
            return false;
        }

        boolean returnVal = true;
        ASCIIBattle example = (ASCIIBattle) obj;
        if ((this.currentHealth() != example.currentHealth())
                || (this.currentEnergy() != example.currentEnergy())
                || (this.currentEnemyHealth() != example.currentEnemyHealth())
                || (this.currentEnemyEnergy() != example.currentEnemyEnergy())
                || (this.currentPotions() != example.currentPotions())) {
            returnVal = false;
        }

        return returnVal;
    }

    //will complete equals method as kernel implementation is completed

    /*
     * Other non-kernel methods ------------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void roundPlay(String playerName, int numBattles, SimpleReader in,
            SimpleWriter out) {

        int roundsTaken = 0;
        int playerExperience = 0;
        String playerChoice = "";

        int enemyHealth = this.currentEnemyHealth();
        int enemyEnergy = this.currentEnemyEnergy();

        for (int i = 1; i <= numBattles; i++) {

            while (this.checkEnemyAlive()
                    && this.checkPlayerAlive(i, in, out)) {

                playerChoice = this.attackOrDefend(playerName, in, out);
                this.enemyTurn(playerName, playerChoice, out);

                roundsTaken++;
            }

            if (!this.checkEnemyAlive()) {
                this.updateEnemy(enemyHealth, enemyEnergy);
                enemyHealth = this.currentEnemyHealth();
                enemyEnergy = this.currentEnemyEnergy();

                playerExperience += this.battleOver(playerExperience, i,
                        roundsTaken, out);
            }

            if (this.currentHealth() <= 0) {
                i = numBattles + 1;
            }

        }
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public Boolean playerDead(int numBattles, SimpleReader in,
            SimpleWriter out) {

        boolean revived = false;

        final int reviveFive = 5;

        int chanceToRevive = this.diceRoll(out);
        if (chanceToRevive > reviveFive) {
            this.editCharacter(-reviveFive * 2, -reviveFive, 0);
            revived = true;
        } else {

            this.endMessage(numBattles, in, out);

        }

        return revived;

    }

}
