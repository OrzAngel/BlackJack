import java.util.ArrayList;
/**
 * represents a hand of cards, and link it to one of the players
 * @author CJC
 *
 */
public class Hand {
	
	private ArrayList<Card> hand;
	private Player holder;
	
	/**
	 * create the hand with its holder
	 * @param player the player holding the hand
	 */
	public Hand(Player player) {
		hand = new ArrayList<Card>();
		holder = player;
	}
	
	/**
	 * add a new card to this hand
	 * @param card the new card to be added
	 * @return the added card itself
	 */
	public Card addCard(Card card) {
		hand.add(card);
		return card;
	}
	
	/**
	 * remove a card from the hand<br>
	 * for further implement of split.
	 * @param card the card to be removed
	 * @return the card that is removed
	 */
	public Card removeCard(Card card) {
		hand.remove(card);
		return card;
	}
	
	/**
	 * accessor method for the card arraylist
	 * @return the arraylist of cards
	 */
	public ArrayList<Card> toArrayList() {
		return hand;
	}
	
	/**
	 * accessor method for the player holding the hand
	 * @return
	 */
	public Player holder() {
		return holder;
	}
	
	public String toString() {
		return hand.toString();
	}

//=============================TEST CODE==========================================	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Deck d = new Deck();
		Player p = new HumanPlayer("HUMAN");
		Hand h = new Hand(p);
		h.addCard(d.draw());
		h.addCard(d.draw());
		System.out.println(h);

	}

}
