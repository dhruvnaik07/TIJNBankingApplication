
public class UserAccount {

	private final String SSN;
	private final String name;
	private final long balance;
	private final String bankID;
	private final String bankNumber;
	private final boolean pbaVerified;
	
	public UserAccount(String SSN, String name, long balance, String bankID, String bankNumber, boolean pbaVerified) {
		
		this.SSN=SSN;
		this.name=name;
		this.balance=balance;
		this.bankID=bankID;
		this.bankNumber=bankNumber;
		this.pbaVerified=pbaVerified;
	}
	
	public String getSSN() {
		
		return SSN;
	}
	
	public String getName() {
		
		return name;
	}
	
	public long getBalance() {
		
		return balance;
	}
	
	public String getBankID() {
		
		return bankID;
	}
	
	public String getBankNumber() {
		
		return bankNumber;
	}
	
	public boolean getPbaVerified() {
		
		return pbaVerified;
	}
	
}
