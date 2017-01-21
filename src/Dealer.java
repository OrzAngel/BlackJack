

/**
 * Acts as a dealer <br>
 * Strategy : never stand until score greater or equal 17. 
 * @author CJC
 *
 */
public class Dealer extends Player {
	
	private static int DEALER_MIN = 17;
	
	public Dealer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean decision(String message) {
		
		int index = message.indexOf("Your score is: ");
		
		if (index == 0) {
			index += "Your score is: ".length();
			int score = Integer.parseInt(message.substring(index).split("\\.")[0]);
			if (score >= DEALER_MIN) {
				return false;
			}
		}
		
		return true;
		
	}
	//=============================TEST CODE==========================================	
}
