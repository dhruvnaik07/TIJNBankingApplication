
public class From {

	private final int RTID;
	private final String identifier;
	private final float percentage;
	
	public From(int RTID, String identifier, float percentage) {
		
		this.RTID=RTID;
		this.identifier=identifier;
		this.percentage=percentage;
	}
	
	public int getRTID() {
		
		return RTID;
	}
	
	public String getIdentifier() {
		
		return identifier;
	}

	public float getPercentage() {
	
	return percentage;
	}
}
