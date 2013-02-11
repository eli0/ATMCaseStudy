// BankDatabase.java
// Represents the bank account information database


public class BankDatabase {
	private Account[] accounts; // array of accounts
	
	// no-argument constructor
	public BankDatabase() {
		accounts = new Account[2]; // test accounts
		accounts[0] = new Account(12345, 54321, 1000.0, 1200.0);
		accounts[1] = new Account(98765, 56789, 200.0, 200.0);
	}
	
	// retrieve account object which has specific account number
	private Account getAccount(int accountNumber) {
		for (Account currentAccount : accounts) {
				if (currentAccount.getAccountNumber() == accountNumber)
					return currentAccount;
				}
			return null; // if no matching account was found return null
		}
	
	// Determine whenever user specified pin matches account's pin
	public boolean authenticateUser(int userAccountNumber, int userPin) {
		Account userAccount = getAccount(userAccountNumber);
		
		//if acc exists validate pin
		if (userAccount != null) {
			return userAccount.validatePIN(userPin);
		} else {
			return false;
		}
	} // end method
	
	// return available balance from account with specified account number
	public double getAvailableBalance (int userAccountNumber) {
		return getAccount(userAccountNumber).getAvailableBalance();
	}
	
	// return total balance from the account with specified account number
	public double getTotalBalance(int userAccountNumber) {
		return getAccount(userAccountNumber).getAvailableBalance();
	}
	
	// credit an amount from the account with specified accout number
	public void credit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).credit(amount);
	}
	
	// debit an amount from account with specified account number
	public void debit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).depit(amount);
	}
}
