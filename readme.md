# BlackJack

Winner winner chicken dinner!
________
Synopsis

	This project contains six major independant classes
	1. Card : represent a card with rank and suit;
	2. Deck : create 52 cards and deal them one by one randomly;
	3. Hand : represent a hand of cards, and store the player who plays this hand;
	4. Player : make decisions (y/n) based on messages from Blackjack game
	5. BlackJack : run one game, interact with players
	6. BlackJackTester : test the BlackJack program
____________
Installation

	Go to BlackJackTester.java. Run the main method. Then follow the instruction on the screen.
	Almost every class has their own main method for testing. Some of them may not make sense.
___
API

	If a user need to write his BlackJackTester of his own based on my classes, below is what he would need. 

	API for the Player

		Constructor

			Player(String name)
			create a player with a name, must be unique.

	API for the BlackJack

		Constructor

			BlackJack()
			prepare the game.

		Method	

			1. void addPlayer(Player player)
			add a new player to the game.	

			2. void removePlayer(Player player)
			remove a player from the game.

			3. ArrayList<Integer> oneGame()
			run one game, returns every player's "score" in an arraylist, the first element stands for the first player added. The "score" now represents whether the player is win or lose in this game: 1 stands for player's win, 0 stands for a tie and -1 stands for a lose. 

______
Design

I managed to implement a BlackJack game with multiple players. And I want to achieve the chanllenge part, especially the split in the future. From this reason I have the design desicions below

1) The Player class stores the player's name only, has no access to their Hand and score. This class will receive message when the BlackJack class calls display(String message) and feedback to the game by answering true or false when the BlackJack class calls decision(String message). Although it makes it really annoying when I want to implement an AIplayer, it make the prototype of Player class highly cohesive and just like a real player in the casino.

2) The BlackJack class runs one game hand after hand asking its hold whether they want a hit or a stand and ends up with the last hand holded by the dealer. The reason why not player after player is that if we introduce split one player may hold multiple hands. And I decide to make Hand aware of its holder, raising the degree of coupling in exchange for easier implementation elsewhere. The Hand class is the major part in my design, almost everything collaborates with Hand class.

3) The BlackJack class is highly coupled with the Dealer class for a easy implemetation. When we create a BlackJack class a Dealer with its name "The dealer" is automatically created. But the dealer is still a player at the same time. It interacts with the game the same way as other players, making decisions based on the message. Thus I could use one single oneHand(Hand hand) method to ask for decision both for the players and the dealer, in order to avoid repeating myself.

4) At first I want to design two seperate classes to achieve the blackJack. one called BlackJackCode, which has no instance variables and knows all the rules about BlackJack. The other one called Casino, holding all the Deck, Hands and Players as instance varibles. I want to make the BlackJackCode sth like the Math class, creating no object of its own but having the method taking the objects in the Casino class as arguments. And the Casino class is able to run the game among its Players with its Deck under the guildline of the BlackJackCode. But I failed to make it. I ended up with a BlackJackScore class responsible for calculating the score of a hand of cards only. And a BlackJack class do all the rest. 

