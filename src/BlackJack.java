import java.util.ArrayList;

/**
 * this class runs a BlackJack game with a dealer, a deck and some outside players<br>
 * @author CJC
 *
 */
public class BlackJack {

	private static final int  PLAYER_BUSTS = -2;
	private static final int  DEALER_BUSTS = -1;
	private static final int  BLACKJACK = 21;

	private Deck deck;
	
	private Dealer dealer;
	private Card faceDown;
	private Hand dealerHand;
	private int dealerScore;

	private ArrayList<Player> players;
	private ArrayList<Hand> hands;
	private ArrayList<Integer> handScores;	
	/**
	 * prepares the game, create a card deck and a dealer.
	 */
	public BlackJack() {

		deck = new Deck();
		dealer = new Dealer("The dealer");

		players = new ArrayList<Player>();
		hands = new ArrayList<Hand>();
		handScores = new ArrayList<Integer>();
		dealerScore = 0;

	}
	/**
	 * add a new player to the game
	 * @param player
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	/**
	 * remove a player from the game
	 * @param player
	 */
	public void removePlayer(Player player) {
		players.remove(player);
	}
	/**
	 * perform one game, returns win or lose for each player in an arraylist.
	 */
	public ArrayList<Integer> oneGame() {
				
		broadcast("A new Game starts");
		
		init();
		
		playerTurn();
		dealerTurn();
		
		return check();
	
	}
	
	/*
	 * game initialize 
	 */
	private void init() {
		
		hands.clear();
		handScores.clear();
		deck.shuffle();

		dealerInit();
		
		for (Player player:players) {
			playerInit(player);
		}
	}
	
	/*
	 * init the dealer
	 */
	private void dealerInit() {

		faceDown = deck.draw();
		dealerHand = new Hand(dealer);
		Card card = deck.draw();
		dealerHand.addCard(card);
		broadcast(String.format("%s gets a %s.",dealer.getName(),card));
		broadcast(String.format("%s has %s in hand, and a card face down.",dealer.getName(),dealerHand));
		//insurance check here in the future
	}
	
	/*
	 * init the player
	 */
	private void playerInit(Player player) {

		//bet here in the future

		Hand hand = new Hand(player);
		Card card = deck.draw();
		hand.addCard(card);
		broadcast(String.format("%s gets a %s.",player.getName(),card));
		card = deck.draw();
		hand.addCard(card);
		broadcast(String.format("%s gets a %s.",player.getName(),card));
		broadcast(String.format("%s has %s in hand.",player.getName(),hand));
		hands.add(hand);

		//split check here in the future

	}
	
	/*
	 * players' turn
	 */
	private void playerTurn() {
		int score;
		for (Hand hand : hands) {
			score = oneHand(hand);
			if (score > BLACKJACK) {
				score = PLAYER_BUSTS;
			}
			handScores.add(score);
		}
	}
	
	/*
	 * dealer's turn
	 */
	private void dealerTurn() {
		dealerHand.addCard(faceDown);
		broadcast(String.format("%s turns his face down card over and gets a %s. Now he has %s in hand.", 
				dealer.getName(), faceDown, dealerHand));
		
		dealerScore = oneHand(dealerHand);
		if (dealerScore > BLACKJACK) {
			dealerScore = DEALER_BUSTS;
		}
		
	}
	
	/*
	 * this method prompts the player & the dealer to make decision on one hand.
	 */
	private int oneHand(Hand hand) {

		Player player = hand.holder();
		int handScore = getScore(hand);
		
		broadcast(String.format("Now it is %s's turn, playing the hand %s",player.getName(),hand));

		while (handScore < BLACKJACK) {
			
			if (player.decision(String.format("Your score is: %d. Do you want a hit? (y/n)", handScore))) {

				Card card = deck.draw();
				hand.addCard(card);
				handScore = getScore(hand);
				broadcast(String.format("%s wants a new card and gets a %s. Having %s in hand now.",player.getName(),card,hand));

			} else {
				broadcast(String.format("%s chooses to stand.",player.getName()));
				break;
			}
		}

		if (handScore == BLACKJACK) {
			broadcast(String.format("%s ends up with a BlackJack! COOOL",player.getName()));
			return handScore;
		}

		broadcast(String.format("%s's final score is: %d",player.getName(),handScore));

		if (handScore > BLACKJACK) {
			broadcast(String.format("%s busts! POOOR",player.getName()));
		}

		return handScore;
	}
	
	/*
	 * check whether each hand is win or lose against the dealer. 
	 */
	private ArrayList<Integer> check() {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		Hand hand;
		Player player = null;
		int diff;
		int handScore;
		int playerScore = 0;
		
		for (int i = 0; i < hands.size(); i++) {
			hand = hands.get(i);
			if (!hand.holder().equals(player)) {
				result.add(playerScore);
				player = hand.holder();
				playerScore = 0;
			}
			
			handScore = handScores.get(i);
			diff = winner(handScore);
			
			switch(diff) {
			case 0:
				player.display("Tie");
				break;
			case 1:
				playerScore ++;
				player.display("You win");
				break;
			case -1:
				playerScore --;
				player.display("You lose");
			}
		}
		
		result.add(playerScore);
		result.remove(0);
		return result;
	}
	
	/**
	 * 
	 * @return 0 - tie; 1 - player win; -1 - dealer win;
	 */
	private int winner(int score) {

		int diff = score - dealerScore;

		if (diff > 0) {
			return 1;
		}
		if (diff < 0) {
			return -1;
		}

		return 0;
	}
	
	private void broadcast(String message) {

		for (Player player : players) {
			player.display(message);
		}
	}

	private static int getScore(Hand hand) {
		return BlackJackScore.getScore(hand);
	}

	//=============================TEST CODE==========================================	
	public static void main(String[] arg) {

		BlackJack bj = new BlackJack();
		Player hm = new HumanPlayer("The one");
		Player ai = new AIPlayer("The AI");
		bj.addPlayer(hm);
//		bj.oneGame();
		bj.addPlayer(ai);
		bj.oneGame();
	}
}
