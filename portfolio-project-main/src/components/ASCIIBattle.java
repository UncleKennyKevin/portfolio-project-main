package components;

import components.simplereader.SimpleReader;
import components.simplewriter.SimpleWriter;

/**
 * {@code ASCIIBattleKernel} enhanced with secondary methods.
 */
public interface ASCIIBattle extends ASCIIBattleKernel {

        /**
         * gives the player a small oppurtunity to be revived. If unsuccesful,
         * calls endMessage method
         *
         * @param roundsPlayed
         *                information on how long the player survived
         * @param in
         *                take in user information
         * @param out
         *                display information to user
         * @return if player is revived
         * @ensures out = [information on player data, option to play again]
         *
         */
        Boolean playerDead(int roundsPlayed, SimpleReader in, SimpleWriter out);

        /**
         * Controls the game, contains a nested for loop that goes for as many
         * battles that the player gives, and prompts the user to make a
         * decision. Then runs the enemy attack decision, then gives player
         * experience if the enemy has run out of health.
         *
         * @param playerName
         *                user given player name
         * @param numBattles
         *                the number f player-given rounds the game will run
         * @param in
         *                take in user information
         * @param out
         *                output information to user
         * @ensures player = [dead] || player = [won battles]
         *
         */
        void roundPlay(String playerName, int numBattles, SimpleReader in,
                        SimpleWriter out);

}
