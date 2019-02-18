import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

class CustomTask extends TimerTask  {

		DBOperations operations;
		Connection connection;
		Statement stmt = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		
		public CustomTask(DBOperations operations, Connection connection) throws SQLException {

			this.operations = operations;
			this.connection = connection;
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			stmt3 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
		}

	   public void run() {
		   
	       try {

	    	   String selectStatement = "SELECT * FROM PENDINGTRANSACTION";
		       ResultSet rs = stmt.executeQuery(selectStatement);
		       
		       if (!rs.first()) {
		    	   
		    	   return;
		       }
		       
		       else {
		    	   
		    	   rs.beforeFirst();
		    	   String identifier = null;
		    	   
		    	   ArrayList<Integer> STIDArray = new ArrayList<>();
		    	   
		    	   while (rs.next()) {
		    		   
		    		   identifier = rs.getString("identifier");
		    		   int STID = rs.getInt("STID");
	    			   String SSN = rs.getString("SSN");
	    			   long amount = rs.getLong("amount");
	    			   Date date = rs.getDate("Datetime");
	    			   String memo = rs.getString("memo");
		    		   
		    		   String selectStatement2 = "SELECT * FROM ELECTRONICADDRESS WHERE Identifier='" + identifier + "'";
		    		   ResultSet rs2 = stmt2.executeQuery(selectStatement2);
		    		   
		    		   if (!rs2.first())
		    			   continue;
		    		   
		    		   else {
		    			   
		    			   STIDArray.add(STID);
		    			   
		    			   rs2.beforeFirst();
		    			   String rSSN = null;

		    			   while (rs2.next()) {
		    				   
		    				   rSSN = rs2.getString("SSN");
		    			   }
		    			   
		    			   String selectStatement3 = "SELECT STID FROM SENDTRANSACTION ORDER BY STID DESC LIMIT 1";
		    			   ResultSet rs3 = stmt3.executeQuery(selectStatement3);
		    			   STID = 0;
							
		    			   if (!rs3.first()) {
								
		    				   STID = 1;
		    			   }
							
		    			   else {
		    				   
		    				   rs3.beforeFirst();
							
			    			   while (rs3.next()) {
									
			    				   STID = rs3.getInt("STID");
			    				   STID++;
			    			   }
		    			   }
		    			   
		    			   SendTransaction sendData = new SendTransaction(STID, amount, date, memo, false, SSN, identifier);
		    			   operations.addIntoSendTransaction(sendData);
		    			   
		    			   selectStatement3 = "SELECT COUNT(*) FROM USERACCOUNT WHERE SSN='" + rSSN + "'";
		    			   rs3 = stmt3.executeQuery(selectStatement3);
		    			   int count = 0;
		    			   
		    			   while (rs3.next()) {
		    				   
		    				   count = rs3.getInt("COUNT");
		    			   }
		    			   
		    			   if (count == 1) {
		    				   
		    				   selectStatement3 = "SELECT Balance FROM USERACCOUNT WHERE SSN='" + rSSN + "'";
		    				   rs3 = stmt3.executeQuery(selectStatement3);
		    				   long balance = 0;
		    				   
		    				   while (rs3.next()) {
		    					   
		    					   balance = rs3.getLong("Balance");
		    				   }
		    				   
		    				   balance = balance + amount;
		    				   
		    				   String updateStatement = "UPDATE USERACCOUNT SET Balance=" + balance + " WHERE SSN='" + rSSN + "'";
		    				   stmt3.executeUpdate(updateStatement);
		    			   }
		    			   
		    			   else {
		    				   
		    				   selectStatement3 = "SELECT Balance FROM USERACCOUNT WHERE SSN='" + rSSN + "' AND PbaVerified=true";
		    				   rs3 = stmt3.executeQuery(selectStatement3);
		    				   long balance = 0;
		    				   
		    				   while (rs3.next()) {
		    					   
		    					   balance = rs3.getLong("Balance");
		    				   }
		    				   
		    				   balance = balance + amount;
		    				   
		    				   String updateStatement = "UPDATE USERACCOUNT SET Balance=" + balance + " WHERE SSN='" + rSSN + "' AND PbaVerified=true";
		    				   stmt3.executeUpdate(updateStatement);
		    			   }
		    		   }
		    	   }
		    	   
    			   for (int i: STIDArray)
    				   operations.deleteFromPendingTransaction(i);
		       }
	    	   
		       selectStatement = "SELECT * FROM PENDINGTRANSACTION";
		       rs = stmt.executeQuery(selectStatement);
	         
		       if (!rs.first()) {
	         
		    	   return;
		       }
	         
		       else {
	        	 
		    	   rs.beforeFirst();
		    	   
		    	   ArrayList<Integer> STIDArray = new ArrayList<>();
	         
		         while (rs.next()) {
		        	 
		        	 Date date = rs.getDate("Datetime");
		        	 Date currentDate = new Date();
		        	 
		        	 Calendar c = Calendar.getInstance(); 
		        	 c.setTime(date); 
		        	 c.add(Calendar.DATE, 15);
		        	 date = c.getTime();
		        	 
		        	 System.out.println(date.toString());
		        	 
		        	 if (date.compareTo(currentDate) < 0) {
		        		 
		        		 int STID = rs.getInt("STID");
			        	 long amount = rs.getLong("Amount");
			        	 String memo = rs.getString("Memo");
			        	 String SSN = rs.getString("SSN");
			        	 String identifier = rs.getString("Identifier");
			        	 
			        	 STIDArray.add(STID);
				        		
			        	 String selectStatement2 = "SELECT STID FROM SENDTRANSACTION ORDER BY STID DESC LIMIT 1";
			        	 ResultSet rs2 = stmt2.executeQuery(selectStatement2);
									
			        	 if (!rs2.first()) {
										
			        		 STID = 1;
			        	 }
									
			        	 else {
			        		 
			        		 rs2.beforeFirst();
									
				        	 while (rs2.next()) {
											
				        		 STID = rs2.getInt("STID");
				        		 STID++;
				        	 }
			        	 }
									
			        	 SendTransaction sendData = new SendTransaction(STID, amount, date, memo, true, SSN, identifier);
			        	 operations.addIntoSendTransaction(sendData);
								
			        	 selectStatement2 = "SELECT COUNT(*) FROM USERACCOUNT WHERE SSN='" + SSN + "'";
			        	 rs2 = stmt2.executeQuery(selectStatement2);
			        	 int count = 0;
								
			        	 while (rs2.next()) {
									
			        		 count = rs2.getInt("COUNT");
			        	 }
								
			        	 if (count == 1) {
								
			        		 selectStatement2 = "SELECT Balance FROM USERACCOUNT WHERE SSN='" + SSN + "'";
			        		 rs2 = stmt2.executeQuery(selectStatement2);
			        		 long balance = 0;
									
			        		 while (rs2.next()) {
										
			        			 balance = rs2.getLong("Balance");
			        		 }
									
			        		 balance = balance + amount;
									
			        		 String updateStatement = "UPDATE USERACCOUNT SET Balance=" + balance + " WHERE SSN='" + SSN + "'";
			        		 stmt2.executeUpdate(updateStatement);
			        	 }
								
			        	 else {
									
			        		 selectStatement2 = "SELECT Balance FROM USERACCOUNT WHERE SSN='" + SSN + "' AND PbaVerified=true";
			        		 rs2 = stmt2.executeQuery(selectStatement2);
			        		 long balance = 0;
									
			        		 while (rs2.next()) {
										
			        			 balance = rs2.getLong("Balance");
			        		 }
									
			        		 balance = balance + amount;
									
			        		 String updateStatement = "UPDATE USERACCOUNT SET Balance=" + balance + " WHERE SSN='" + SSN + "' AND PbaVerified=true";
			        		 stmt2.executeUpdate(updateStatement);
			        	 }
			       	 }
		       	 }
		         
		         for (int i: STIDArray)
		        	 operations.deleteFromPendingTransaction(i);
	         }
	     }
	       
	     catch (Exception e) {
	           
	    	  e.printStackTrace();
	     }
	 }
}

public class TIJNServer {

	public static void main(String[] args) {

		try {
			
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
			   "jdbc:postgresql://localhost:5432/TIJN","postgres", "password123");
			
			DBOperations operations = new DBOperations(connection);
			CustomTask task = new CustomTask(operations, connection);
			
			Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	        
	        System.out.println(calendar.getTime());
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	
	        Timer time = new Timer();
	        time.schedule(task, calendar.getTime(), TimeUnit.SECONDS.toMillis(120));
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
}