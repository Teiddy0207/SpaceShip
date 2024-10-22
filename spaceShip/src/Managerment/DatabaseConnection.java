package Managerment;
import java.sql.*;

import javax.swing.JOptionPane;


public class DatabaseConnection {
	  private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=spaceShip;encrypt=true;trustServerCertificate=true";
	  private String userName = "sa";
	  private String password = "123456";
	  
	  public Connection connectToDataBase()
	  {
		  Connection conn = null;
		  try {
			  conn = DriverManager.getConnection( url, userName, password);
			 
			  
		  }catch(SQLException e) {
			  e.printStackTrace();
			
	
		  }
		  
		  
		  return conn;
	  }
}
