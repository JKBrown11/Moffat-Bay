package mbTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccess {

	private String username ="root";
	private String password = "PASSWORD";
	private String databaseURL= "jdbc:mysql://localhost:3306/MoffatBayLodge";
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
	
	
	public ArrayList<CustomerBean> getQueryRes(){return this.queryRes;}
	public void setQueryRes(){	
		String requestStaff="SELECT * FROM craftmart_employee.staff";
		try {
			ResultSet rset = stmt.executeQuery(requestStaff);
			while (rset.next()) {
				
				CustomerBean staff = new CustomerBean();
				staff.setFirstName(rset.getString(1));//all customer data i'm pulling is currently string, so next should work?
				staff.setLastName(rset.getString(2));
				staff.setEmail(rset.getString(3));
				staff.setPhone(rset.getString(4));
				
				
				this.queryRes.add(staff);
		
			}//end while
			
		System.out.println("Query array complete");
		this.disconn();
		}//end try
		catch (SQLException e) {
			e.getMessage();
		}
		
	}

	/*
	 * This method takes a bean (made from form data in the servlet) 
	 * and adds it to the database for the duration of the session only.
	 *  Table is wiped each time due to requirements of jsp creating 
	 *  the table and populating it.
	 * */
	public void addBeans(CustomerBean empBean) {
		//create sql query from empBean data
		//run makeUpdate with string
		
		String addBean= "INSERT INTO `db_name`.`db_table`"
				+ "(firstName, lastName, email, phone)"
				+" VALUES('" + empBean.getFirstName() +"','" + empBean.getLastName() +"','"
				+ empBean.getEmail() +"','"+ empBean.getPhone() + "')";
		
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
