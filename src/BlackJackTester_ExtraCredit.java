
public class BlackJackTester_ExtraCredit {
	
	
	public static void main(String[] args) {
		
		BlackJack bj = new BlackJack();
		
		int[] win = new int[10];
		int[] tie = new int[10];
		int[] lose = new int[10];
		Player[] players = new Player[10];
		String s = "AI_";
		
		int i;
		
		for (i = 1; i <= 8 ; i++) {
			players[i] = new AIPlayerBasic(s + i);
			bj.addPlayer(players[i]);
			win[i] = 0;
			tie[i] = 0;
			lose[i] =0;
		}
		
		players[i] = new AIPlayer(s + i);
		bj.addPlayer(players[i]);
		win[i] = 0;
		tie[i] = 0;
		lose[i] =0;
		
		for (int k = 0; k < 100; k++) {
			i = 0 ;
			for (int result : bj.oneGame()) {
				i++;
				switch (result) {
				case 0:
					tie[i]++;
					break;
				case 1:
					win[i]++;
					break;
				case -1:
					lose[i]++;
					break;
				}
			}
		}
		
		for (i = 1 ; i <= 9; i++) {
			System.out.printf("%s: win %d, lose %d, tie %d%n",players[i].getName(),win[i],lose[i],tie[i]);
		}
	}
	
}
