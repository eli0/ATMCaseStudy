// Deposit.java
// Represents a deposit ATM transaction

public class Deposit extends Transaction {
	private double amount;
	private Keypad keypad;
	private DepositSlot depositSlot;
	private final static int CANCELED = 0;
	
	public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot) {
		super (userAccountNumber, atmScreen, atmBankDatabase);
		
		keypad = atmKeypad;
		depositSlot = atmDepositSlot;
	}
	
	//perform transaction
	@Override
	public void execute() {
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		amount = promptForDepositAmount();
		
		// check whether user entered deposit amount or canceled
		if (amount != CANCELED) {
			// request deposit envelope
			screen.displayMessage("\nPlease insert a deposit envelope containing ");
			screen.dispalyDollarAmount(amount);
			screen.displayMessageLine(".");
			
			// receive deposit envelope
			boolean envelopeReceived = depositSlot.isEnvelopeReceived();
			
			if (envelopeReceived) {
				screen.displayMessage("\nYour envelope has been" + "received.\nNOTE: The money just deposited will not " + "be available until we verify the amount of any " + "enclosed cash and your checks clear.");
				bankDatabase.credit(getAccountNumber(), amount);
			} else {
				screen.displayMessageLine("\nYou did not insert an " + "envelope, so the ATM has canceled your transaction.");
			}
		} else {
			screen.displayMessageLine("\nCanceling transaction...");
		}
	}
	
	private double promptForDepositAmount() {
		Screen screen = getScreen();
		
		screen.displayMessage("\nPlease enter a deposit amount in " + "CENTS (or 0 to cancel): ");
		int input = keypad.getInput();
		
		// check if canceled 
		if (input == CANCELED) 
			return CANCELED;
		else {
			return (double) input / 100;
		}
	}
}
