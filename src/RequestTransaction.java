
import java.util.Date;

public class RequestTransaction {

	private final int RTID;
	private final long amount;
	private final Date dateTime;
	private final String memo;
	private final String SSN;
	
	public RequestTransaction(int RTID, long amount, Date dateTime, String memo, String SSN) {
		
		this.RTID=RTID;
		this.amount=amount;
		this.dateTime=dateTime;
		this.memo=memo;
		this.SSN=SSN;
	}
	
	public int getRTID() {
		
		return RTID;
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
	
	public String getSSN() {
		
		return SSN;
	}
}
