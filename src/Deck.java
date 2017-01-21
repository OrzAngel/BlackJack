import java.util.ArrayList;
import java.util.Random;

/**
 * this class creates and maintains a deck of cards 
 * @author CJC
 *
 */
public class Deck {
	
	private ArrayList<Card> deck;
	private ArrayList<Card> used;
	private Random rand;
	
	/**
	 * creates a new deck of 52 cards
	 */
	public Deck() {
		
		rand = new Random();
		deck = new ArrayList<Card>();
		
		for (int suit = 0; suit <= 3; suit++)
		for (int rank = 0; rank <= 12; rank++) {
			deck.add(new Card(suit,rank));
		}
		
		used = new ArrayList<Card>();
	
	}
	
	/**
	 * draw a card randomly from the deck.
	 * @return the card randomly drawn
	 */
	public Card draw() {
		int  i = rand.nextInt(deck.size());
		Card c = deck.remove(i); 
		used.add(c);
		return c;
	}
	
	/**
	 * put back all the used cards.
	 */
	public void shuffle() {
		
		deck.addAll(used);
		used.clear();;
		
	}
	
	public String toString() {
		return deck.toString();
	}
	
	
//=============================TEST CODE==========================================	
	
	public static void main(String[] arg) {
		
		Deck d = new Deck();
		System.out.println(d);
		System.out.println(d.draw());
		System.out.println(d.draw());
		System.out.println(d.draw());
		System.out.println(d.draw());
		
		System.out.println(d.used);
		d.shuffle();
		System.out.println(d);
		System.out.println(d.draw());
		System.out.println(d.draw());
		System.out.println(d.draw());
		System.out.println(d.draw());
		d.shuffle();
		System.out.println(d);
		for (int i = 1; i<= 51; i++) {
			d.draw();
		}
		System.out.println(d);

	}
}
