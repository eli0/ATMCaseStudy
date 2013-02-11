// Account.java
// Represents a bank account

public class Account {
	private int accountNumber; // acc number
	private int pin; // pin number
	private double availableBalance; // balance which is currently available
	private double totalBalance; // total funding + pending deposits
	
	//Account constructor initializes attributes
	public Account(int theAccountNumber, int thePin, double theAvailableBalance, double theTotalBalance) {
		accountNumber = theAccountNumber;
		pin = thePin;
		availableBalance = theAvailableBalance;
		totalBalance = theTotalBalance;
	} // end of the constructor
	
	// validate users pin
	public boolean validatePIN (int userPIN) {
		if (pin == userPIN)
			return true;
		else
			return false;
	}
	
	public double getAvailableBalance(){
		return availableBalance;
	}
	
	public double getTotalBalance() {
		return totalBalance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void credit(double amount) {
		totalBalance += amount;
	}
	
	public void depit(double amount) {
		availableBalance -= amount;
		totalBalance -= amount;
	}
}
