package mbTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccess {

	private String username ="group1_dev";
	private String password = "pass";
	private String databaseURL= "jdbc:mysql://localhost:3306/mbLodge";
	private Connection conn;
	private Statement stmt;
	//private ArrayList<CustomerBean> queryRes = new ArrayList<>();
	
	/*
	 *Method to create dao operator. Object creation handles driver,
	 * connection, and statement 
	 * */
	public DataAccess() throws SQLException, ClassNotFoundException{
		//load driver
		try{Class.forName("com.mysql.cj.jdbc.Driver");}
		catch(ClassNotFoundException e){e.getMessage();}
		//get connection
		if(conn==null) {
			try {this.conn=DriverManager.getConnection(databaseURL, username, password);	}
			catch (SQLException eq) {eq.printStackTrace();}
		}
		if(conn!=null) {
			System.out.println("Connected to db");
			this.stmt=conn.createStatement();
		}
		
		System.out.println("DB setup complete");
	}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;	}

	public String getDatabaseURL() {return databaseURL;	}
	public void setDatabaseURL(String databaseURL) {this.databaseURL = databaseURL;	}
	
	


	/*
	 * This method takes a bean (made from form data in the servlet) 
	 * and adds it to the database for the duration of the local machine database only.
	 *  Table is not yet updated to retain all data. 
	 * */
	public void addBeans(CustomerBean newCust) {
		//create sql query from empBean data
		//run makeUpdate with string
		
		String addBean= "INSERT INTO `mbLodge`.`customer_data`"
				+ "(firstName, lastName, email, age, phone, hashedPass)"
				+" VALUES('" + newCust.getFirstName() +"','" + newCust.getLastName() +"','"
				+ newCust.getEmail() + "','" + newCust.getAge() + "','"+ newCust.getPhone() + "','" + newCust.getHashedPassword() + "')";
		
		makeUpdate(addBean);
		System.out.println("Bean added");
	}
	
	/*This method is used in validating login attempts
	 * @param String sqlQuery the select statement created from user
	 * input email */
	public String queryEmailpw(String sqlQuery) {
		System.out.println("running makeQuery from DAO");
		ResultSet sqlOutput;
		String returnedHash;
		try {
			sqlOutput = stmt.executeQuery(sqlQuery);
			if (sqlOutput!= null) {
				//System.out.println(status);
				sqlOutput.next();
				returnedHash = sqlOutput.getString(1);
				System.out.println("Return value from db: " + returnedHash);
				return returnedHash;				
			}	
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return "Something went wrong";
		}
		return "error";
	}
	
	/*
	 * This function makes update commands to the db
	 * given @param String sqlQuery from servlet or validator class */
	public String makeUpdate(String sqlQuery) {
		Integer status;
		try {
			status = stmt.executeUpdate(sqlQuery);
			if (status>0) {
				return "Update Successful";}		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return "Something went wrong";
		}
		return status.toString();
	}

	/*This function counts the number of reservations 
	 * beginning on the check in date for the 
	 * users requested bed size to prevent overbooking */
	public int makeQueryCount(String sqlQuery) {
		ResultSet countResult;
		int count = -1;
		try{
			countResult= stmt.executeQuery(sqlQuery);
			if (countResult != null) {
				countResult.next();
				count = countResult.getInt(1);
				return count;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
	}

	/*This function inserts a new reservation to the  database 
	 * given an sql query from servlet or validator */
	public boolean makeReservationUpdate(String sqlQuery) {
		
		Integer status;
		try {
			status = stmt.executeUpdate(sqlQuery);
			System.out.println("The results of insert update are: " + status);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/*This function loads a customer into the session after a successful login */
	public CustomerBean loadCustomer(CustomerBean loggedInUser) {
		//get other data from sql query
		String loadCustomerQuery = "SELECT * FROM mblodge.customer_data WHERE email = '" + loggedInUser.getEmail() + "'";
		try {
			ResultSet userData = stmt.executeQuery(loadCustomerQuery);
			if(userData != null) {
				userData.next();
				loggedInUser.setFirstName(userData.getString(1));
				loggedInUser.setLastName(userData.getString(2));
				loggedInUser.setEmail(userData.getString(3));
				loggedInUser.setAge(Integer.valueOf(userData.getInt(4)).toString());
				loggedInUser.setPhone(userData.getString(5));
				
			}
			return loggedInUser;
		}
		catch (Exception e) {
			e.printStackTrace();
			return loggedInUser;
		}
	}
	
	/*Function to search by reservation number */
	public ReservationBean searchReservationNumber(int resNum, CustomerBean loggedInUser) {
		
		ReservationBean searchedStay = new ReservationBean();
		if (resNum < 0) {
			return searchedStay;//this is an empty Bean? I can't return null
		}
		String queryResNum = "SELECT * FROM mblodge.reservations WHERE reservation_number = " + resNum
				+ " AND email = '" + loggedInUser.getEmail() + "'";
		
		try {
			ResultSet queryResults = stmt.executeQuery(queryResNum);
			if (queryResults != null) {
				queryResults.next();
				searchedStay.setResNumber(queryResults.getInt(1));
				System.out.println("PUlled res number");
				searchedStay.setResOwnerEmail(queryResults.getString(2));
				System.out.println("setResOwnerEmail run");
				searchedStay.setCheckInDate(queryResults.getString(3));
				searchedStay.setCheckOutDate(queryResults.getString(4));
				searchedStay.setRoomType(queryResults.getString(5));
				System.out.println("results of roomtype" + searchedStay.getRoomType());
				searchedStay.setNumGuests(queryResults.getInt(6));
				return searchedStay;
			}
			else return searchedStay;// empty attributes
		}
		catch (Exception e) {
			e.printStackTrace();
			return searchedStay; //this would likely also be empty if stmt threw an error
		}
	}
	
	
	/*Function to search by customer email */
	//perhaps return an array of reservations for email search
	public ArrayList<ReservationBean> searchUserEmail(CustomerBean loggedInUser) {
		String rezByEmail = "SELECT * FROM mblodge.reservations WHERE email = '" 
				+ loggedInUser.getEmail() + "'";
		System.out.println("Logged in email as: " + loggedInUser.getEmail());
		ArrayList<ReservationBean> allUserRez = new ArrayList<ReservationBean>();
		ReservationBean tempBean = new ReservationBean();
		try {
			ResultSet dbPull = stmt.executeQuery(rezByEmail);
			if (dbPull != null) {
				while(dbPull.next()) {
					tempBean.setResNumber(dbPull.getInt(1));
					tempBean.setResOwnerEmail(dbPull.getString(2));
					tempBean.setCheckInDate(dbPull.getString(3));
					tempBean.setCheckOutDate(dbPull.getString(4));
					tempBean.setRoomType(dbPull.getString(5));
					tempBean.setNumGuests(dbPull.getInt(6));
					allUserRez.add(tempBean);
					System.out.println("added a bean to search diplay");
					
				}//end while
				return allUserRez;
			}//end if
			else return null;
		}//end try
		catch(Exception e) {
			e.printStackTrace(); 
			return null;}
	}
	
	
	//disconnect from db
	public void disconn() throws SQLException {
		if (this.conn!=null)
			this.conn.close();
	}
	
	
}
