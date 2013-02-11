	// ATM.java
	// Represents an automated teller machine
	
public class ATM {
	private boolean userAuthenticated; // whether user is authenticated
	private int currentAccountNumber; // curent user's account number
	private Screen screen; // ATM's screen
	private Keypad keypad; // ATM's keypad
	private CashDispenser cashDispenser; // ATM's cash dispenser
	private DepositSlot depositSlot; // ATM's deposit slot
	private BankDatabase bankDatabase; // account information database
	
	// constants corresponding menu options
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	// non-argument constructor initializes variables
	public ATM() {
		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();
		keypad = new Keypad();
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		bankDatabase = new BankDatabase();
	}
	
	// start ATM
	public void run() {
		// welcome and authenticate user; perform transaction
		while (true) {
			while (!userAuthenticated) {
				screen.displayMessageLine("\nWelcome!");
				authenticateUser();
			}// end while
			
			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;
			screen.displayMessageLine("\nThank You! Gooodbye!");
		}// end while
	} // end method run
	
	public void authenticateUser() {
		screen.displayMessage("\nPlease enter your bank account number: ");
		int accountNumber = keypad.getInput();
		screen.displayMessage("\nPlease enter your PIN: ");
		int pin = keypad.getInput();
		
		// set userAuthenticated to boolean value set by database
		userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
		
		// check whether authentication succeed 
		if (userAuthenticated) {
			currentAccountNumber = accountNumber; 
		} else {
			screen.displayMessage("\nIvalid account number or PIN code. Please try again.");
		}
	}
		
		private void performTransactions() {
			// local variable to store transaction currently being processed
			Transaction currentTransaction = null;
			
			boolean userExited = false; // user has not chosen exit
			
			// loop while user has not chosen exit
			
			while (!userExited) {
				// show menu and get user selection
				int mainMenuSelection = displayMainMenu();
				
			// decide how to proceed based on users selection
				switch (mainMenuSelection) {
					// user choose to perform on of three transaction types
				case BALANCE_INQUIRY:
				case WITHDRAWAL:
				case DEPOSIT:
					currentTransaction = createTransaction(mainMenuSelection);
					currentTransaction.execute();
					break;
				case EXIT:
					screen.displayMessage("\nExiting system...");
					userExited = true;
					break;
				default: 
					screen.displayMessage("\n You have not entered a valid selection. Try again.");
					break;
				}
			}
		}
		
		private int displayMainMenu() {
			screen.displayMessageLine("\nMain Menu:");
			screen.displayMessageLine("1 - View my balance");
			screen.displayMessageLine("2 - Withdraw cash");
			screen.displayMessageLine("3 - Depsosit funds ");
			screen.displayMessageLine("4 - Exit\n");
			return keypad.getInput();
		}
		
		private Transaction createTransaction(int type) {
			Transaction tmp = null;
			
			// determine which type of transaction create
			switch (type) {
			case BALANCE_INQUIRY:
				tmp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
				break;
			case WITHDRAWAL:
				tmp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
				break;
			case DEPOSIT:
				tmp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
				break;
			}
			return tmp;
		}
} // end ATM