
public class SSNCode {

	private final String SSN;
	private final String code;
	private final String EOP;
	private final String type;
	
	public SSNCode(String SSN, String code, String EOP, String type) {
		
		this.SSN=SSN;
		this.code=code;
		this.EOP = EOP;
		this.type = type;
	}
	
	public String getSSN() {
		
		return SSN;
	}
	
	public String getCode() {
		
		return code;
	}
	
	public String getEOP() {
		
		return EOP;
	}

	public String getType() {

		return type;
	}
}
