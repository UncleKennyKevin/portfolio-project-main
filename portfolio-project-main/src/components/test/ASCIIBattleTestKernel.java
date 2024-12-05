package components.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.ASCIIBattle;
import components.ASCIIBattle1L;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * JUnit text fixture for {@code ASCIIBATTLE}'s kernel and standard methods.
 *
 * @author Kevin Roback
 */
public class ASCIIBattleTestKernel {

        /**
         * default value 10.
         */
        private final int df = 10;

        //Test Standard

        //TEST FOR NEW INSTANCE

        /**
         * Tests new instance default.
         */
        @Test
        public final void testNewInstanceDefault() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);

                ASCIIBattle sNew = s.newInstance();
                ASCIIBattle s2 = new ASCIIBattle1L();

                assertEquals(sNew, s2);
        }

        /**
         * Tests new instance default.
         */
        @Test
        public final void testNewInstanceVarying() {
                ASCIIBattle s = new ASCIIBattle1L(14, 15, 87, 2354, 2525);

                ASCIIBattle sNew = s.newInstance();
                ASCIIBattle s2 = new ASCIIBattle1L();

                assertEquals(sNew, s2);
        }

        //TEST FOR CLEAR

        /**
         * Tests clear default.
         */
        @Test
        public final void testClearDefault() {
                ASCIIBattle s = new ASCIIBattle1L();
                s.clear();
                ASCIIBattle s2 = new ASCIIBattle1L();

                assertEquals(s, s2);
        }

        /**
         * Tests clear with different inputted values.
         */
        @Test
        public final void testClearDifferent() {
                ASCIIBattle s = new ASCIIBattle1L(12, 14, 15, 83, 49);
                s.clear();
                ASCIIBattle s2 = new ASCIIBattle1L();

                assertEquals(s, s2);
        }

        //TESTS FOR TRANSFERFROM

        /**
         * Tests transferFrom empty.
         */
        @Test
        public final void testTransferFromDefault() {
                ASCIIBattle s = new ASCIIBattle1L();
                ASCIIBattle sCopy = new ASCIIBattle1L();

                ASCIIBattle s2 = new ASCIIBattle1L();
                ASCIIBattle sCleared = new ASCIIBattle1L();

                s2.transferFrom(s);

                assertEquals(s, sCleared);
                assertEquals(s2, sCopy);
        }

        /**
         * Tests transferFrom default.
         */
        @Test
        public final void testTransferFrom10s() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);

                ASCIIBattle s2 = new ASCIIBattle1L();
                ASCIIBattle sCleared = new ASCIIBattle1L();

                s2.transferFrom(s);

                assertEquals(s, sCleared);
                assertEquals(s2, sCopy);
        }

        /**
         * Tests transferFrom default.
         */
        @Test
        public final void testTransferFromDifferent() {
                ASCIIBattle s = new ASCIIBattle1L(23, 56, 243, 625, 2432);
                ASCIIBattle sCopy = new ASCIIBattle1L(23, 56, 243, 625, 2432);

                ASCIIBattle s2 = new ASCIIBattle1L();
                ASCIIBattle sCleared = new ASCIIBattle1L();

                s2.transferFrom(s);

                assertEquals(s, sCleared);
                assertEquals(s2, sCopy);
        }

        /*
         * dice roll and fight visuals methods do not change the component, only
         * add visuals, and have been tested thoroughly through demos
         */

        /*
         * enemy turn has been thoroughly tested through the demos, and call the
         * edit character and edit enemy kernel methods when editing the
         * components
         */

        /**
         * Test update enemy with default vals.
         */
        @Test
        public final void testUpdateEnemyBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df, 12, 11,
                                this.df);
                s.updateEnemy(this.df, this.df);
                assertEquals(s, sCopy);
        }

        /**
         * Test update enemy with mid-game vals.
         */
        @Test
        public final void testUpdateEnemyMidGame() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, 100, 100,
                                this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df, 120,
                                112, this.df);
                s.updateEnemy(100, 100);
                assertEquals(s, sCopy);
        }

        /**
         * Test update enemy with late-game vals.
         */
        @Test
        public final void testUpdateEnemyLateGame() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, 500, 500,
                                this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df, 600,
                                562, this.df);
                s.updateEnemy(500, 500);
                assertEquals(s, sCopy);
        }

        ////TESTS FOR BATTLE OVER

        /**
         * Test how xp is added when it's 0.
         */
        @Test
        public final void testBattleOverXP0() {
                SimpleWriter out = new SimpleWriter1L();

                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);

                int xp = 0;
                int battleNumber = 1;
                int roundsTaken = 1;

                xp = s.battleOver(xp, battleNumber, roundsTaken, out);
                boolean addedCorrectCheck = (xp >= 1 && xp <= 250);
                assertEquals(true, addedCorrectCheck);

                out.close();
        }

        /**
         * Test how xp is added when it's 0.
         */
        @Test
        public final void testBattleOverXP150() {
                SimpleWriter out = new SimpleWriter1L();

                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);

                int xp = 150;
                int battleNumber = 1;
                int roundsTaken = 1;

                xp = s.battleOver(xp, battleNumber, roundsTaken, out);

                boolean addedCorrectCheck = (xp >= 151 && xp <= 400);
                assertEquals(true, addedCorrectCheck);

                out.close();
        }

        //tests for check player alive

        /**
         * Test check player alive where true.
         */
        @Test
        public final void testCheckPlayerAliveTrue() {
                SimpleReader in = new SimpleReader1L();
                SimpleWriter out = new SimpleWriter1L();

                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                boolean testVal = true;

                boolean results = s.checkPlayerAlive(1, in, out);
                assertEquals(results, testVal);

                in.close();
                out.close();
        }

        //add test for edit enemy

        /**
         * Test changing nothing to enemy.
         */
        @Test
        public final void testEditEnemyBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                s.editEnemy(0, 0);
                assertEquals(s, sCopy);
        }

        /**
         * Test changing small values to enemy.
         */
        @Test
        public final void testEditEnemySmall() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df,
                                this.df - 2, this.df - 2, this.df);
                s.editEnemy(2, 2);
                assertEquals(s, sCopy);
        }

        /**
         * Test changing varied values to enemy.
         */
        @Test
        public final void testEditEnemyVarying() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df,
                                this.df - 6, this.df - 4, this.df);
                s.editEnemy(6, 4);
                assertEquals(s, sCopy);
        }

        /**
         * Test adding values to enemy.
         */
        @Test
        public final void testEditEnemyAddition() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df,
                                this.df + 6, this.df + 4, this.df);
                s.editEnemy(-6, -4);
                assertEquals(s, sCopy);
        }

        //EDIT CHARACTER TESTS

        /**
         * Test adding nothing to player.
         */
        @Test
        public final void testCharacterBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                s.editCharacter(0, 0, 0);
                assertEquals(s, sCopy);
        }

        /**
         * Test small values to editing player.
         */
        @Test
        public final void testCharacterSmall() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df - 2, this.df - 2,
                                this.df, this.df, this.df - 1);
                s.editCharacter(2, 2, 1);
                assertEquals(s, sCopy);
        }

        /**
         * Test varying values to editing player.
         */
        @Test
        public final void testCharacterVarying() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df - 4, this.df - 5,
                                this.df, this.df, this.df - 6);
                s.editCharacter(4, 5, 6);
                assertEquals(s, sCopy);
        }

        /**
         * Test adding values to editing player.
         */
        @Test
        public final void testCharacterAdding() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                ASCIIBattle sCopy = new ASCIIBattle1L(this.df + 4, this.df + 5,
                                this.df, this.df, this.df + 6);
                s.editCharacter(-4, -5, -6);
                assertEquals(s, sCopy);
        }
        //add test for endmessage

        //add test for gameStart

        //TESTS FOR CURRENT HEALTH

        /**
         * Test with default current health.
         */
        @Test
        public final void testCurrentHealthDefault() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                int testVal = 10;
                int returnVal = s.currentHealth();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with base case.
         */
        @Test
        public final void testCurrentHealthBase() {
                ASCIIBattle s = new ASCIIBattle1L(1, this.df, this.df, this.df,
                                this.df);
                int testVal = 1;
                int returnVal = s.currentHealth();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with high value.
         */
        @Test
        public final void testCurrentHealthHigh() {
                ASCIIBattle s = new ASCIIBattle1L(13534, this.df, this.df,
                                this.df, this.df);
                int testVal = 13534;
                int returnVal = s.currentHealth();
                assertEquals(testVal, returnVal);
        }

        //CURRENT ENERGY TESTS

        /**
         * Test with default current energy.
         */
        @Test
        public final void testCurrentEnergyDefault() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                int testVal = 10;
                int returnVal = s.currentEnergy();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with base case.
         */
        @Test
        public final void testCurrentEnergyBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, 1, this.df, this.df,
                                this.df);
                int testVal = 1;
                int returnVal = s.currentEnergy();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with high value.
         */
        @Test
        public final void testCurrentEnergyHigh() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, 13534, this.df,
                                this.df, this.df);
                int testVal = 13534;
                int returnVal = s.currentEnergy();
                assertEquals(testVal, returnVal);
        }

        //TESTS FOR CURRENT POTIONS

        /**
         * Test with default current potions.
         */
        @Test
        public final void testCurrentPotionsDefault() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                int testVal = 10;
                int returnVal = s.currentPotions();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with base case.
         */
        @Test
        public final void testCurrentPotionsBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, 1);
                int testVal = 1;
                int returnVal = s.currentPotions();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with high value.
         */
        @Test
        public final void testCurrentPotionsHigh() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, 13534);
                int testVal = 13534;
                int returnVal = s.currentPotions();
                assertEquals(testVal, returnVal);
        }

        //TESTS FOR CAN USE SPECIAL ATTACK

        /**
         * Test that is true.
         */
        @Test
        public final void testCanUseSpecialAttackTrue() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, 30, this.df, this.df,
                                this.df);
                boolean returnVal = s.canUseSpecialAttack();
                assertEquals(true, returnVal);
        }

        /**
         * Test that is false.
         */
        @Test
        public final void testCanUseSpecialAttackFalse() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, 25, this.df, this.df,
                                this.df);
                boolean returnVal = s.canUseSpecialAttack();
                assertEquals(false, returnVal);
        }

        /**
         * Test that is false.
         */
        @Test
        public final void testCanUseSpecialAttackBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, 1, this.df, this.df,
                                this.df);
                boolean returnVal = s.canUseSpecialAttack();
                assertEquals(false, returnVal);
        }

        //CURRENT ENEMY HEALTH TESTS

        /**
         * Test with default current enemy health.
         */
        @Test
        public final void testCurrentEnemyHealthDefault() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                int testVal = 10;
                int returnVal = s.currentEnemyHealth();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with base case.
         */
        @Test
        public final void testCurrentEnemyHealthBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, 1, this.df,
                                this.df);
                int testVal = 1;
                int returnVal = s.currentEnemyHealth();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with high value.
         */
        @Test
        public final void testCurrentEnemyHealthHigh() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, 13534,
                                this.df, this.df);
                int testVal = 13534;
                int returnVal = s.currentEnemyHealth();
                assertEquals(testVal, returnVal);
        }

        //CURRENT ENEMY ENERGY TESTS

        /**
         * Test with default current enemy health.
         */
        @Test
        public final void testCurrentEnemyEnergyDefault() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                this.df, this.df);
                int testVal = 10;
                int returnVal = s.currentEnemyEnergy();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with base case.
         */
        @Test
        public final void testCurrentEnemyEnergyBase() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df, 1,
                                this.df);
                int testVal = 1;
                int returnVal = s.currentEnemyEnergy();
                assertEquals(testVal, returnVal);
        }

        /**
         * Test with high value.
         */
        @Test
        public final void testCurrentEnemyEnergyHigh() {
                ASCIIBattle s = new ASCIIBattle1L(this.df, this.df, this.df,
                                13534, this.df);
                int testVal = 13534;
                int returnVal = s.currentEnemyEnergy();
                assertEquals(testVal, returnVal);
        }

}
