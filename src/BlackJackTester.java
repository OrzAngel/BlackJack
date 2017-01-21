
public class BlackJackTester {

	
	
	public static void main(String[] args) {
		
		BlackJack bj = new BlackJack();
		
		Player player = new HumanPlayer("Terran");
		
		bj.addPlayer(player);
		
		System.out.println("Your name is Terran, Now you will play the game vs. the dealer");
		do {
			
			bj.oneGame();
			
		} while (player.decision("Do you want a new game?(y/n)"));
	}
		
		
}
