
public class HasAdditional {

	private final String SSN;
	private final String bankID;
	private final String bankNumber;
	private final boolean verified;
	
	public HasAdditional(String SSN, String bankID, String bankNumber, boolean verified) {
		
		this.SSN=SSN;
		this.bankID=bankID;
		this.bankNumber=bankNumber;
		this.verified=verified;
	}
	
	public String getSSN() {
		
		return SSN;
	}
	
public String getBankID() {
		
		return bankID;
	}

public String getBankNumber() {
	
	return bankNumber;
}

public boolean getVerified() {
	
	return verified;
}
}
