import java.util.Scanner;

/**
 * Acts as a human player<br>
 * displays everything onto the screen and makes decision base on input from keyboard. 
 * @author CJC
 *
 */
public class HumanPlayer extends Player {
	
	private Scanner in;
	
	public HumanPlayer(String name) {
		super(name);
		in = new Scanner(System.in);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display(String message) {
		System.out.println(message);
	}

	@Override
	public boolean decision(String message) {
		
		display(message);
		return in.nextLine().equals("y");
		
	}

	//=============================TEST CODE==========================================	

}
