
public class BankAccount {

	private final String bankID;
	private final String bankNumber;
	
	public BankAccount(String bankID, String bankNumber) {
		
		this.bankID=bankID;
		this.bankNumber=bankNumber;
	}
	
	public String getBankID() {
		
		return bankID;
	}
	
	public String getBankNumber() {
		
		return bankNumber;
	}
}
