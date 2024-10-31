package craftmartTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccess {

	private String username ="root";
	private String password = "main987";
	private String databaseURL= "jdbc:mysql://localhost:3306/craftmart_employee";
	private Connection conn;
	private Statement stmt;
	private ArrayList<EmployeeBean> queryRes = new ArrayList<>();
	
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
		this.setup();
		System.out.println("DB setup complete");
	}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;	}

	public String getDatabaseURL() {return databaseURL;	}
	public void setDatabaseURL(String databaseURL) {this.databaseURL = databaseURL;	}
	
	
	public ArrayList<EmployeeBean> getQueryRes(){return this.queryRes;}
	public void setQueryRes(){	
		String requestStaff="SELECT * FROM craftmart_employee.staff";
		try {
			ResultSet rset = stmt.executeQuery(requestStaff);
			while (rset.next()) {
				
				EmployeeBean staff = new EmployeeBean();
				staff.setFirstName(rset.getString(1));//all customer data i'm pulling is currently string, so next should work?
				staff.setLastName(rset.getString(2));
				staff.setEmail(rset.getString(3));
				staff.setPhone(rset.getString(4));
				staff.setStreetAddress(rset.getString(5));
				staff.setCity(rset.getString(6));
				staff.setState(rset.getString(7));
				staff.setZip(rset.getString(8));
				staff.setHireDate(rset.getString(9));
				staff.setPosition(rset.getString(10));
				
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
	public void addBeans(EmployeeBean empBean) {
		//create sql query from empBean data
		//run makeUpdate with string
		
		String addBean= "INSERT INTO `craftmart_employee`.`staff`"
				+ "(firstName, lastName, email, phone, streetAddress, city, state, zip, hireDate, position)"
				+" VALUES('" + empBean.getFirstName() +"','" + empBean.getLastName() +"','"
				+ empBean.getEmail() +"','"+ empBean.getPhone() +"','"+ empBean.getStreetAddress() +"','"
				+ empBean.getCity() +"','" + empBean.getState() +"','" + empBean.getZip() +"','"
				+ empBean.getHireDate() + "','" +empBean.getPosition() + "')";
		
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
	public void setup() {
		String initSQL="DROP TABLE IF EXISTS `craftmart_employee`.`staff`;\r\n";
		String create= "CREATE TABLE `craftmart_employee`.`staff` "
				+ "  (`firstName` VARCHAR(20) NOT NULL,"
				+ "  `lastName` VARCHAR(20) NOT NULL,"
				+ "  `email` VARCHAR(45) NOT NULL,"
				+ "  `phone` VARCHAR(12) NOT NULL,"
				+ "  `streetAddress` VARCHAR(45) NOT NULL,"
				+ "  `city` VARCHAR(45) NOT NULL,"
				+ "  `state` VARCHAR(2) NOT NULL,"
				+ "  `zip` VARCHAR(5) NOT NULL,"
				+ "  `hireDate` DATE NOT NULL,"
				+ "  `position` VARCHAR(45) NOT NULL,"
				+ "  PRIMARY KEY (`email`),"
				+ "  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE)";
				
		//ChatGPT helped me populate sample data pre-formatted as sql (I gave it my above code). Nothing fancy, and nothing I couldn't do myself. 
		String popSQL = "INSERT INTO `craftmart_employee`.`staff` \r\n"
				+ "(`firstName`, `lastName`, `email`, `phone`, `streetAddress`, `city`, `state`, `zip`, `hireDate`, `position`) VALUES\r\n"
				+ "('John', 'Doe', 'john.doe@example.com', '123-567-8904', '123 Elm St', 'Springfield', 'IL', '62701', '2023-01-15', 'Manager'),\r\n"
				+ "('Jane', 'Smith', 'jane.smith@example.com', '123-456-7891', '456 Oak St', 'Springfield', 'IL', '62701', '2023-01-16', 'Developer'),\r\n"
				+ "('Alice', 'Johnson', 'alice.johnson@example.com', '123-456-7892', '789 Pine St', 'Lincoln', 'NE', '68508', '2023-01-17', 'Designer'),\r\n"
				+ "('Bob', 'Brown', 'bob.brown@example.com', '123-456-7893', '101 Maple St', 'Omaha', 'NE', '68102', '2023-01-18', 'Sales'),\r\n"
				+ "('Charlie', 'Davis', 'charlie.davis@example.com', '123-456-7894', '202 Birch St', 'Peoria', 'IL', '61602', '2023-01-19', 'Marketing'),\r\n"
				+ "('Dana', 'Martinez', 'dana.martinez@example.com', '123-456-7895', '303 Cedar St', 'Chicago', 'IL', '60601', '2023-01-20', 'HR'),\r\n"
				+ "('Eve', 'Wilson', 'eve.wilson@example.com', '123-456-7896', '404 Spruce St', 'Madison', 'WI', '53703', '2023-01-21', 'Analyst'),\r\n"
				+ "('Frank', 'Garcia', 'frank.garcia@example.com', '123-456-7897', '505 Walnut St', 'Detroit', 'MI', '48201', '2023-01-22', 'Developer'),\r\n"
				+ "('Grace', 'Lee', 'grace.lee@example.com', '123-456-7898', '606 Chestnut St', 'Milwaukee', 'WI', '53202', '2023-01-23', 'Support'),\r\n"
				+ "('Hank', 'Martinez', 'hank.martinez@example.com', '123-456-7899', '707 Ash St', 'St. Louis', 'MO', '63101', '2023-01-24', 'Sales'),\r\n"
				+ "('Ivy', 'Hernandez', 'ivy.hernandez@example.com', '123-456-7800', '808 Fir St', 'Indianapolis', 'IN', '46201', '2023-01-25', 'Finance'),\r\n"
				+ "('Jack', 'Robinson', 'jack.robinson@example.com', '123-456-7801', '909 Redwood St', 'Cincinnati', 'OH', '45201', '2023-01-26', 'Designer'),\r\n"
				+ "('Kelly', 'Clark', 'kelly.clark@example.com', '123-456-7802', '123 Palm St', 'Columbus', 'OH', '43201', '2023-01-27', 'HR'),\r\n"
				+ "('Liam', 'Lewis', 'liam.lewis@example.com', '123-456-7803', '234 Chestnut St', 'Nashville', 'TN', '37201', '2023-01-28', 'Manager'),\r\n"
				+ "('Mia', 'Walker', 'mia.walker@example.com', '123-456-7804', '345 Maple St', 'Charlotte', 'NC', '28201', '2023-01-29', 'Analyst'),\r\n"
				+ "('Noah', 'Hall', 'noah.hall@example.com', '123-456-7805', '456 Oak St', 'Baltimore', 'MD', '21201', '2023-01-30', 'Support'),\r\n"
				+ "('Olivia', 'Young', 'olivia.young@example.com', '123-456-7806', '567 Birch St', 'Atlanta', 'GA', '30301', '2023-01-31', 'Developer'),\r\n"
				+ "('Paul', 'King', 'paul.king@example.com', '123-456-7807', '678 Maple St', 'Seattle', 'WA', '98101', '2023-02-01', 'Sales'),\r\n"
				+ "('Quinn', 'Wright', 'quinn.wright@example.com', '123-456-7808', '789 Elm St', 'Phoenix', 'AZ', '85001', '2023-02-02', 'Marketing'),\r\n"
				+ "('Rachel', 'Scott', 'rachel.scott@example.com', '123-456-7809', '890 Pine St', 'Denver', 'CO', '80201', '2023-02-03', 'Finance')";
		
		this.makeUpdate(initSQL);
		this.makeUpdate(create);
		this.makeUpdate(popSQL);
	}

	//disconnect from db
	public void disconn() throws SQLException {
		if (this.conn!=null)
			this.conn.close();
	}
	
	
}
