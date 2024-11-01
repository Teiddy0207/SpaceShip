package GGV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XLGV {
	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=BookDB;encrypt=true;trustServerCertificate=true";
	private String userName = "sa";
	private String password = "123456";
	
	public Connection getCon()
	{
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<Giangvien> getGV(String Donvi, int Soct) throws SQLException
	{
		List<Giangvien> dsFV = new ArrayList<>();
		
		try(Connection conn = getCon())
		{
			String query = "Select * from tbGiangvien where Donvi = ? and Soct >= ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, Donvi);
			ps.setInt(2, Soct);
			ResultSet  rs = ps.executeQuery();
			
		}
		
		return dsFV;
	}
}
