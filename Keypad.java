// Keypad.java
// Represents ATM's keypad

import java.util.Scanner;

public class Keypad {
	private Scanner input; // reads data from the command line
	
	// non-argument constructor
	public Keypad() {
		input = new Scanner(System.in);
	}
	
	public int getInput() {
		return input.nextInt();
	}
}