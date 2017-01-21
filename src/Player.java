/**
 * The major behavior of a player/dealer(we can also treat the dealer as a player) in the Game.<br>
 * Basically this class has no info about BlackJack, comes to play the game with mouth(decision) and eyes(display) and money in the future
 * @author CJC
 *
 */
public class Player {
	
	protected String name;
	
	/**
	 * creates a new player with his name.
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * accessor method for the name of the player
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sometimes the BlackJack will broadcast messages to all players in the game, this method determines the way this player reads the message.<br>
	 * If the player is a human player, then simply prints the message on the screen<br>
	 * If the player is an AI player, then it would split the string and extract the necessary info for decision making<br>
	 * If the player is a dealer, It seems okay to leave this method empty. Because the dealer's strategy is really simple.
	 * @param message the message that is broadcasted
	 */
	public void display(String message) {
		
	}
	
	/**
	 * Sometimes the player needs to make decision (Yes or no) when the game prompts.<br>
	 * A human player's decision will base on the input from the keyboard.<br>
	 * An AI player will analyze what it knows from the broadcast and the prompt message, then make a decision.<br>
	 * The dealer is the same as an AI player in this case.<br>
	 * @param message the message prompts the player to make a decision
	 * @return true if the player answers yes
	 */
	public boolean decision(String message) {
		return true;
	}

}
