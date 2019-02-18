
public class ElectronicAddress {
	
	private final String identifier;
	private final String SSN;
	private final String type;
	private final boolean verified;
	
	public ElectronicAddress(String identifier, String SSN, String type, boolean verified) {
		
		this.identifier=identifier;
		this.SSN=SSN;
		this.type=type;
		this.verified=verified;
	}
	
	public String getIdentifier() {
		
		return identifier;
	}
	
	public String getSSN() {
		
		return SSN;
	}

	public String getType() {
	
		return type;
	}
	
	public boolean getVerified() {
		
		return verified;
	}
}
