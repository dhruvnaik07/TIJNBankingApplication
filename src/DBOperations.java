import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.PreparedStatement;

public class DBOperations {

	private final Connection connection;
	
	public DBOperations(Connection connection) {
		
		this.connection=connection;
	}
	
	public void createBankAccount() {
		
		String createStatement = "CREATE TABLE BANKACCOUNT (\n" + "BankID VARCHAR(5),\n" + "BankNumber VARCHAR(5),\n" + "PRIMARY KEY(BankID, BankNumber),\n" + "UNIQUE(BankID, BankNumber)\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("BANKACCOUNT table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void createUserAccount() {
		
		String createStatement = "CREATE TABLE USERACCOUNT (\n" + "SSN CHAR(9),\n" + "Name VARCHAR(15),\n" + "Balance FLOAT(2),\n" + "BankID VARCHAR(5),\n" + "BankNumber VARCHAR(5),\n" + "PBAVerified BOOLEAN,\n" +"PRIMARY KEY(SSN),\n" + "FOREIGN KEY (BankID, BankNumber) REFERENCES BANKACCOUNT(BankID, BankNumber) ON DELETE CASCADE\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("USERACCOUNT table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void createFrom() {
		
		String createStatement = "CREATE TABLE FROMTABLE (\n" + "RTID INT,\n" + "Identifier VARCHAR(30),\n" + "Percentage FLOAT(2),\n" + "PRIMARY KEY(RTID, Identifier),\n" + "FOREIGN KEY (RTID) REFERENCES REQUESTTRANSACTION(RTID),\n" + "FOREIGN KEY (IDENTIFIER) REFERENCES ELECTRONICADDRESS(IDENTIFIER)\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("FROMTABLE table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void createHasAdditional() {
		
		String createStatement = "CREATE TABLE HASADDITIONAL (\n" + "SSN CHAR(9),\n" + "BankID VARCHAR(5),\n" + "BankNumber VARCHAR(5),\n" + "Verified BOOLEAN,\n" +"PRIMARY KEY(SSN, BankID, BankNumber),\n" + "FOREIGN KEY (BankID, BankNumber) REFERENCES BANKACCOUNT(BankID, BankNumber),\n" + "FOREIGN KEY (SSN) REFERENCES USERACCOUNT(SSN)\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("HASADDITIONAL table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void createRequestTransaction() {
		
		String createStatement = "CREATE TABLE REQUESTTRANSACTION (\n" + "RTID INT,\n" + "Amount FLOAT(2),\n" + "DateTime DATE,\n" + "Memo VARCHAR(50),\n" + "SSN CHAR(9),\n" + "PRIMARY KEY(RTID),\n" + "FOREIGN KEY (SSN) REFERENCES USERACCOUNT(SSN)\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("REQUESTTRANSACTION table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void createSendTransaction() {
		
		String createStatement = "CREATE TABLE SENDTRANSACTION (\n" + "STID INT,\n" + "Amount FLOAT(2),\n" + "DateTime DATE,\n" + "Memo VARCHAR(50),\n" + "Cancelled BOOLEAN,\n" + "SSN CHAR(9),\n" + "Identifier VARCHAR(30),\n" + "PRIMARY KEY(STID),\n" + "FOREIGN KEY (SSN) REFERENCES USERACCOUNT(SSN),\n" + "FOREIGN KEY (IDENTIFIER) REFERENCES ELECTRONICADDRESS(IDENTIFIER)\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("SENDTRANSACTION table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
		
	public void createElectronicAddress() {
		
		String createStatement = "CREATE TABLE ELECTRONICADDRESS (\n" + "Identifier VARCHAR(30),\n" + "SSN CHAR(9),\n" + "Type CHAR(1),\n" + "Verified BOOLEAN,\n" + "PRIMARY KEY(Identifier),\n" + "FOREIGN KEY (SSN) REFERENCES USERACCOUNT(SSN)\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("ELECTRONICADDRESS table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void createSSNCode() {
		
		String createStatement = "CREATE TABLE SSNCODE (\n" + "SSN CHAR(9),\n" + "Code CHAR(6),\n" + "Identifier VARCHAR(30),\n" + "Type CHAR(1),\n" + "PRIMARY KEY(SSN, Type));";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("SSNCODE table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void createPendingTransaction() {
		
		String createStatement = "CREATE TABLE PENDINGTRANSACTION (\n" + "STID INT,\n" + "Amount FLOAT(2),\n" + "DateTime DATE,\n" + "Memo VARCHAR(50),\n" + "SSN CHAR(9),\n" + "Identifier VARCHAR(30),\n" + "PRIMARY KEY(STID),\n" + "FOREIGN KEY (SSN) REFERENCES USERACCOUNT(SSN)\n" + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.execute(createStatement);
			System.out.println("PENDINGTRANSACTION table created successfully");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoBankAccount(BankAccount data) {
		
		String bankID = data.getBankID();
		String bankNumber = data.getBankNumber();
		
		String addStatement = "INSERT INTO BANKACCOUNT VALUES ( '" + bankID + "', '" + bankNumber + "');";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(addStatement);
			System.out.println("Data is added into BANKACCOUNT table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoUserAccount(UserAccount data) {
		
		String SSN = data.getSSN();
		String name = data.getName();
		String bankID = data.getBankID();
		String bankNumber = data.getBankNumber();
		long balance = data.getBalance();
		boolean pbaVerified = data.getPbaVerified();
		
		String addStatement = "INSERT INTO USERACCOUNT VALUES ( '" + SSN + "', '" + name + "', " + balance + ", '" + bankID + "', '" + bankNumber + "', " + pbaVerified + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(addStatement);
			System.out.println("Data is added into USERACCOUNT table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoHasAdditional(HasAdditional data) {
		
		String SSN = data.getSSN();
		String bankID = data.getBankID();
		String bankNumber = data.getBankNumber();
		boolean verified = data.getVerified();
		
		String addStatement = "INSERT INTO HASADDITIONAL VALUES ( '" + SSN + "', '" + bankID + "', '" + bankNumber + "', " + verified + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(addStatement);
			System.out.println("Data is added into HASADDITIONAL table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoSendTransaction(SendTransaction data) {
		
		int STID = data.getSTID();
		long amount = data.getAmount();
		Date dateTime = data.getDateTime();
		String memo = data.getMemo();
		boolean cancelled = data.getCancelled();
		String SSN = data.getSSN();
		String identifier = data.getIdentifier();
		
		java.sql.Date sqlDate=new java.sql.Date(dateTime.getTime());
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("INSERT INTO SENDTRANSACTION VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, STID);
            ps.setLong(2, amount);
            ps.setDate(3, sqlDate);
            ps.setString(4, memo);
            ps.setBoolean(5, cancelled);
            ps.setString(6, SSN);
            ps.setString(7, identifier);
            ps.executeUpdate();
  
			System.out.println("Data is added into SENDTRANSACTION table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoRequestTransaction(RequestTransaction data) {
		
		int RTID = data.getRTID();
		long amount = data.getAmount();
		Date dateTime = data.getDateTime();
		String memo = data.getMemo();
		String SSN = data.getSSN();
		
		java.sql.Date sqlDate=new java.sql.Date(dateTime.getTime());
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("INSERT INTO REQUESTTRANSACTION VALUES (?,?,?,?,?)");
            ps.setInt(1, RTID);
            ps.setLong(2, amount);
            ps.setDate(3, sqlDate);
            ps.setString(4, memo);
            ps.setString(5, SSN);
            ps.executeUpdate();
			System.out.println("Data is added into REQUESTTRANSACTION table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoFrom(From data) {
		
		int RTID = data.getRTID();
		String identifier = data.getIdentifier();
		float percentage = data.getPercentage();
		
		String addStatement = "INSERT INTO FROMTABLE VALUES ( '" + RTID + "', '" + identifier + "', " + percentage + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(addStatement);
			System.out.println("Data is added into FROMTABLE table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoElectronicAddress(ElectronicAddress data) {
		
		String identifier = data.getIdentifier();
		String SSN = data.getSSN();
		String type = data.getType();
		boolean verified = data.getVerified();
		
		String addStatement = "INSERT INTO ELECTRONICADDRESS VALUES ( '" + identifier + "', '" + SSN + "', '" + type + "', " + verified + ");";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(addStatement);
			System.out.println("Data is added into ELECTRONICADDRESS table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoSSNCode(SSNCode data) {
		
		String SSN = data.getSSN();
		String Code = data.getCode();
		String EOP = data.getEOP();
		String type = data.getType();
		
		String addStatement = "INSERT INTO SSNCODE VALUES ( '" + SSN + "', '" + Code + "', '" + EOP + "', '" + type + "');";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(addStatement);
			System.out.println("Data is added into SSNCODE table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addIntoPendingTransaction(PendingTransaction data) {
		
		int STID = data.getSTID();
		long amount = data.getAmount();
		Date dateTime = data.getDateTime();
		String memo = data.getMemo();
		String SSN = data.getSSN();
		String identifier = data.getIdentifier();
		
		java.sql.Date sqlDate=new java.sql.Date(dateTime.getTime());
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("INSERT INTO PENDINGTRANSACTION VALUES (?,?,?,?,?,?)");
            ps.setInt(1, STID);
            ps.setLong(2, amount);
            ps.setDate(3, sqlDate);
            ps.setString(4, memo);
            ps.setString(5, SSN);
            ps.setString(6, identifier);
            ps.executeUpdate();
  
			System.out.println("Data is added into PENDINGTRANSACTION table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromBankAccount(String bankID, String bankNumber) {
		
		String deleteStatement = "DELETE FROM BANKACCOUNT WHERE BankID='" + bankID + "' AND BANKNUMBER='" + bankNumber + "'";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from BANKACCOUNT table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromUserAccount(String SSN, String bankID, String bankNumber) {
		
		String deleteStatement = "DELETE FROM USERACCOUNT WHERE SSN='" + SSN + "' AND BankID='" + "' AND BankNumber='" + bankNumber + "'";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from USERACCOUNT table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromHasAdditional(String SSN, String bankID, String bankNumber) {
		
		String deleteStatement = "DELETE FROM HASADDITIONAL WHERE SSN='" + SSN + "' AND BankID='" + "' AND BankNumber='" + bankNumber + "'";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from HASADDITIONAL table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromSendTransaction(int STID) {
		
		String deleteStatement = "DELETE FROM SENDTRANSACTION WHERE STID=" + STID;
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from SENDTRANSACTION table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromRequestTransaction(int RTID) {
		
		String deleteStatement = "DELETE FROM REQUESTTRANSACTION WHERE RTID=" + RTID;
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from REQUESTTRANSACTION table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromFromTable(int RTID, String identifier) {
		
		String deleteStatement = "DELETE FROM FROMTABLE WHERE RTID=" + RTID + " AND IDENTIFIER='" + identifier + "'";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from FROMTABLE table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromElectronicAddress(String identifier) {
		
		String deleteStatement = "DELETE FROM ELECTRONICADDRESS WHERE identifier='" + identifier + "'";
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from ELECTRONICADDRESS table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteFromPendingTransaction(int STID) {
		
		String deleteStatement = "DELETE FROM PENDINGTRANSACTION WHERE STID=" + STID;
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(deleteStatement);
			System.out.println("Data is deleted from PENDINGTRANSACTION table");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}