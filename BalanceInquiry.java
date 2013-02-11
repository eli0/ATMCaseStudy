// BalanceInquiry.java
//Represents a balance inquiry atm transaction

public class BalanceInquiry extends Transaction {
	// balance inquiry constructor
	public BalanceInquiry (int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
		super(userAccountNumber, atmScreen, atmBankDatabase);
	}// end constructor
	
	//performs the transaction
	@Override
	public void execute() {
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		//get available balance
		double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
		
		// get total balance for the account involved
		double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
		
		// Display all the information to the screen
		screen.displayMessageLine("\nBalance Information:");
		screen.displayMessage(" - Available balance: ");
		screen.dispalyDollarAmount(availableBalance);
		screen.displayMessage("\n - Total balance:");
		screen.dispalyDollarAmount(totalBalance);
		screen.displayMessageLine("");
	}// end execute
} // end class
