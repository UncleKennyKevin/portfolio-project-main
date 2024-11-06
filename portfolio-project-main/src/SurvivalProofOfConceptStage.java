
*

import java.util.Random;

import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;import*components.simplereader.SimpleReader1L;import*components.simplewriter.SimpleWriter;import*components.simplewriter.SimpleWriter1L;**

public class SurvivalProofOfConceptStage {
    ** //predict chance when chance depends on material level private static boolean
    *
   QueryMaterial(int materialLevel) {
 *
 * boolean result = true;
 *
 * Random diceRoll = new Random(); int rollResults = diceRoll.nextInt(7);
 *
 * if (rollResults > materialLevel) { result = false; }
 *
 * return result; }** //predict chance for when chance does not depend on material level private
    *

   static boolean QueryGeneral(int likelihood) {
 *
 * boolean result = true;
 *
 * Random diceRoll = new Random(); int firstRoll = diceRoll.nextInt(10); int
 * secondRoll = diceRoll.nextInt(1 + likelihood);
 *
 * if (firstRoll > secondRoll) { result = false; }
 *
 * return result; }** //this method will cycle through the day when the previous part of the day is
    *over

    private static String nextPhase(String timeOfDay) {
 *
 * String nextPhase = null; if (timeOfDay.equals("morning")) { nextPhase =
 * "afternoon"; } else if (timeOfDay.equals("afternoon")) { nextPhase =
 * "evening"; } else if (timeOfDay.equals("evening")) { nextPhase = "endDay"; }
 *
 * return nextPhase;
 *
 * }** //this method will change the stats of the player, although isn't ready yet
    *

   private void changePlayerStats(int entryNumber, int change, SimpleWriter out)
 * {
 *
 * // this.replaceEntry(entryNumber, change); out.println("CurrentStats: ");
 *
 * }
 *
 * //this method builds up a new player with the user given stats private static
 *

   Sequence<Integer> createCharacter(Sequence<Integer> player, int healthMax,
 * int energyMax, int hungerMax) {
 *
 * player.add(0, healthMax); player.add(1, energyMax); player.add(2, hungerMax);
 *
 * return player;
 *
 * }
 *
 * //will be broken up into seperate methods. Currently just runs morning.
 *

   private static void gameController(Sequence<Integer> player, int days,
 * SimpleReader in, SimpleWriter out) {
 *
 * String timeOfDay = "morning";
 *
 * Random diceRoll = new Random(); int rainChance = diceRoll.nextInt(3); int
 * bearAttackChance = diceRoll.nextInt(1); //method runs through as many days as
 * player gives for (int i = 1; i <= days; i++) {
 *
 * //each time of day will be its own method if (timeOfDay.equals("morning")) {
 *
 * out.println("DAY " + i + " BEGIN: ");
 *
 * boolean rain = QueryGeneral(rainChance); boolean attack =
 * QueryGeneral(bearAttackChance);
 *
 * if (rain) { out.println("Oh no! It rained. Your energy is down by 5."); //
 * player.changePlayerStats(1,-5, out); } else {
 * out.println("The weather is clear and beautiful outside"); }
 *
 * if (attack) { //give player choice to attack or flee. Attack option rolls for
 * landing //or missing based on material level }
 *
 * //when morning is done, cycles to afternoon
 *
 * timeOfDay = nextPhase(timeOfDay); } }
 *
 * }
 *
 * //main method. Asks for user input then calls game controller
 *
 * //possible representation:
 *
 * //character name: John //Character health, energy, hunger: 40,20,95
 *
 *

   public static void main(String[] args) {
 *
 * SimpleReader in = new SimpleReader1L(); SimpleWriter out = new
 * SimpleWriter1L();
 *
 * out.println("WELCOME TO THE SURVIVAL PROJECT!");
 * out.print("Enter the following values: Player Name: ");
 *
 * String playerName = in.nextLine();
 *
 * out.print("Max Health of " + playerName + ":      ");
 *
 * int healthMax = Integer.parseInt(in.nextLine());
 *
 * out.print("Max Energy of " + playerName + ":      ");
 *
 * int energyMax = Integer.parseInt(in.nextLine());
 *
 * out.print("Max Hunger of " + playerName + ":      ");
 *
 * int hungerMax = Integer.parseInt(in.nextLine());
 *
 * out.print("How Many Days Do You Want " + playerName +
 * " To Survive For? :      ");
 *
 * int days = Integer.parseInt(in.nextLine());
 *
 * Sequence<Integer> player = new Sequence1L<Integer>();
 *
 * createCharacter(player, healthMax, energyMax, hungerMax);
 *
 * gameController(player, days, in, out);
 *
 * in.close(); out.close(); }
}
