
import java.util.Date;

public class SendTransaction {

	private final int STID;
	private final long amount;
	private final Date dateTime;
	private final String memo;
	private final boolean cancelled;
	private final String SSN;
	private final String identifier;
	
	public SendTransaction(int STID, long amount, Date date, String memo, boolean cancelled, String SSN, String identifier) {
		
		this.STID=STID;
		this.amount=amount;
		this.dateTime=date;
		this.memo=memo;
		this.cancelled=cancelled;
		this.SSN=SSN;
		this.identifier=identifier;
	}
	
	public int getSTID() {
		
		return STID;
	}
	
	public long getAmount() {
		
		return amount;
	}

	public Date getDateTime() {
	
		return dateTime;
	}

	public String getMemo() {
	
		return memo;
	}

	public boolean getCancelled() {
	
		return cancelled;
	}

	public String getSSN() {
	
		return SSN;
	}
	
	public String getIdentifier() {
		
		return identifier;
	}
}
