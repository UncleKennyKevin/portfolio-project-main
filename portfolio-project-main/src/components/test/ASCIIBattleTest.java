package components.test;

import static org.junit.Assert.*;

import org.junit.Test;

import components.ASCIIBattle;
import components.ASCIIBattle1L;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * JUnit text fixture for {@code ASCIIBATTLE}'s abstract methods.
 *
 * @author Kevin Roback
 */
public class ASCIIBattleTest {

        /**
         * default value 10.
         */
        private final int df = 10;

        //tests for tostring

        /**
         * test to string with default representation.
         */
        @Test
        public final void testToStringDefault() {
                ASCIIBattle s = new ASCIIBattle1L();
                ASCIIBattle sCopy = new ASCIIBattle1L();
                String finalResult = s.toString();
                String testVal = "Stats(PlayerHealth- 0, PlayerEnergy- 0, CanUseSpecialAttack- false, Number of Potions- 0) Enemy Stats(EnemyHealth- 0, EnemyEnergy- 0)";

                assertEquals(finalResult, testVal);
                assertEquals(s, sCopy);
        }

        /**
         * test to string with 10s representation.
         */
        @Test
        public final void testToString10s() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                String finalResult = s.toString();
                String testVal = "Stats(PlayerHealth- 10, PlayerEnergy- 10, CanUseSpecialAttack- false, Number of Potions- 10) Enemy Stats(EnemyHealth- 10, EnemyEnergy- 10)";

                assertEquals(finalResult, testVal);
                assertEquals(s, sCopy);
        }

        /**
         * test to string with varied representation.
         */
        @Test
        public final void testToStringVaried() {
                ASCIIBattle s = new ASCIIBattle1L(12, 16, 18, 20, 22);
                ASCIIBattle sCopy = new ASCIIBattle1L(12, 16, 18, 20, 22);
                String finalResult = s.toString();
                String testVal = "Stats(PlayerHealth- 12, PlayerEnergy- 16, CanUseSpecialAttack- false, Number of Potions- 22) Enemy Stats(EnemyHealth- 18, EnemyEnergy- 20)";

                assertEquals(finalResult, testVal);
                assertEquals(s, sCopy);
        }

        /**
         * test to string with varied representation and can use special attack.
         */
        @Test
        public final void testToStringCanUseSpecialAttack() {
                ASCIIBattle s = new ASCIIBattle1L(12, 40, 18, 20, 22);
                ASCIIBattle sCopy = new ASCIIBattle1L(12, 40, 18, 20, 22);

                String finalResult = s.toString();
                String testVal = "Stats(PlayerHealth- 12, PlayerEnergy- 40, CanUseSpecialAttack- true, Number of Potions- 22) Enemy Stats(EnemyHealth- 18, EnemyEnergy- 20)";

                assertEquals(finalResult, testVal);
                assertEquals(s, sCopy);
        }

        //tests for equals

        /**
         * test equals default.
         */
        @Test
        public final void testEqualsDefault() {
                ASCIIBattle s = new ASCIIBattle1L();
                ASCIIBattle sCopy = new ASCIIBattle1L();

                assertTrue(s.equals(sCopy));

        }

        /**
         * test equals with one changed.
         */
        @Test
        public final void testEqualsChangedOne() {
                ASCIIBattle s = new ASCIIBattle1L();
                ASCIIBattle sCopy = new ASCIIBattle1L();

                s.editCharacter(1, 1, 1);
                assertTrue(!s.equals(sCopy));
        }

        /**
         * test equals with both changed.
         */
        @Test
        public final void testEqualsChangedBoth() {
                ASCIIBattle s = new ASCIIBattle1L();
                ASCIIBattle sCopy = new ASCIIBattle1L();

                s.editCharacter(1, 1, 1);
                sCopy.editCharacter(1, 1, 1);

                assertTrue(s.equals(sCopy));
        }

        /*
         * Round Play method has been thoroughly tested through the demos. Round
         * play does not affect components. It calls kernel methods depending on
         * the round number and if the player and enemy health is above 0.
         */

        //test for playerdead

        /**
         * test playerDead if player health is above 0.
         */
        @Test
        public final void testPlayerDeadFalse() {
                ASCIIBattle s = new ASCIIBattle1L(1, 1, 1, 1, 1);

                SimpleWriter out = new SimpleWriter1L();
                SimpleReader in = new SimpleReader1L();
                int roundsPlayed = 20;

                boolean finalResult = s.playerDead(roundsPlayed, in, out);

                assertEquals(false, finalResult);

                in.close();
                out.close();
        }

        /**
         * test playerDead if player health is above 0.
         */
        @Test
        public final void testPlayerDeadFalseRounds500() {
                ASCIIBattle s = new ASCIIBattle1L(1, 1, 1, 1, 1);

                SimpleWriter out = new SimpleWriter1L();
                SimpleReader in = new SimpleReader1L();
                int roundsPlayed = 500;

                boolean finalResult = s.playerDead(roundsPlayed, in, out);

                assertEquals(false, finalResult);

                in.close();
                out.close();
        }
}
