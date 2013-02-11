//CashDispenser.java
//Represents Cash Dispenser of the ATM

public class CashDispenser {
	// the default initial number of bills in the cash dispenser
	public final static int INITIAL_COUNT = 500;
	private int count; // number of $20 bills remaining
	
	//no-argument constructor initializes count to default
	public CashDispenser() {
		count = INITIAL_COUNT;
	}
	
	// simulates dispensing of specified amount of cash
	public void dispenseCash(int amount) {
		int billsRequired = amount / 20; // amount of bills required
		count -= billsRequired; // update the count of bills
	} // end method dispense cash
	
	// indicates whether cash dispenser can dispense desired amount of money
	public boolean isSufficientCashAvailable(int amount) {
		int billsRequired = amount / 20;
		
		if (count >= billsRequired)
			return true;
		else
			return false;
	} //end method
} //end class