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
	private ArrayList<CustomerBean> queryRes = new ArrayList<>();
	
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
	
	
//	public ArrayList<CustomerBean> getQueryRes(){return this.queryRes;}
//	public void setQueryRes(){	
//		
//		try {
//			ResultSet rset = stmt.executeQuery();
//			while (rset.next()) {
//				
//				CustomerBean cust = new CustomerBean();
//				cust.setFirstName(rset.getString(1));//all customer data i'm pulling is currently string, so next should work?
//				cust.setLastName(rset.getString(2));
//				cust.setEmail(rset.getString(3));
//				cust.setPhone(rset.getString(4));
//				
//				
//				this.queryRes.add(cust);
//		
//			}//end while
//			
//		System.out.println("Query array complete");
//		this.disconn();
//		}//end try
//		catch (SQLException e) {
//			e.getMessage();
//		}
//		
//	}

	/*
	 * This method takes a bean (made from form data in the servlet) 
	 * and adds it to the database for the duration of the session only.
	 *  Table is wiped each time due to requirements of jsp creating 
	 *  the table and populating it.
	 * */
	public void addBeans(CustomerBean newCust) {
		//create sql query from empBean data
		//run makeUpdate with string
		
		String addBean= "INSERT INTO `mbLodge`.`customer_data`"
				+ "(firstName, lastName, email, age, phone, hashedPass)"
				+" VALUES('" + newCust.getFirstName() +"','" + newCust.getLastName() +"','"
				+ newCust.getEmail() + "','" + newCust.getAge() + "','"+ newCust.getPhone() + "','" + newCust.getHashedPassword() + "')";
		
		System.out.println(addBean);
		makeUpdate(addBean);
		System.out.println("Bean added");
	}
	
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

	/*
	 * The prompt asked us to create a table and populate it 'in the jsp'. 
	 * This occurs each time the servlet is called to add a member.
	 * */
	

	//disconnect from db
	public void disconn() throws SQLException {
		if (this.conn!=null)
			this.conn.close();
	}
	
	
}
