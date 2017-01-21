/**
 * This class represent a card with its rank and suit. <br>
 * once created it could never change
 * @author CJC
 *
 */
public class Card {
	
	private static String[] RANK = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private static String[] SUIT = {"\u2660","\u2764","\u2663","\u2666"};
	
	private int rank; // 0:A 1:2 etc,
	private int suit; // 0: spades, 1:hearts, 2:clubs, 3: diamonds
	
	/**create a new card with its rank and suit
	 * @param rank
	 * @param suit
	 */
	public Card(int suit, int rank) {
		this.rank = rank;
		this.suit = suit;
	}
	/**
	 * accessor for the rank
	 * @return an int represent the the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * accessor for the suit
	 * @return an int represent the suit
	 */
	public int getSuit() {
		return suit;
	}
	
	public String toString() {
		return SUIT[suit]+RANK[rank];
	}

	
	
//=============================TEST CODE==========================================	

	public static void main(String[] args) {
		
		System.out.println(new Card(0,6));
		System.out.println(new Card(1,11));
		System.out.println(new Card(3,12));
		System.out.println(new Card(2,9));
	}
	
}
