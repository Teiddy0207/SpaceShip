package BookPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BookProcess {
	private String url = "jdbc:sqlserver://TEDDY\\QUANGANH:1433;databaseName=BookDB;encrypt=true;trustServerCertificate=true";
	private String userName = "sa";
	private String password = "123456";


	public Connection getCon() 
	{
		Connection conn = null;
		try {
			 conn = DriverManager.getConnection( url, userName, password);
			 JOptionPane.showMessageDialog(null, "Ket noi thanhh cong");
			
			
		}catch (SQLException e)
		{
			e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "Ket noi thai bai" + e.getMessage());
		}
		return conn;
	}
	
	public void getBookByID(String id) throws SQLException 
	{
		Connection conn = getCon();
		String query = "Select * from tbBook where id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		 if (rs.next()) {
		        // In ra thông tin cuốn sách
		        System.out.println("ID: " + rs.getString("ID"));
		        System.out.println("Name: " + rs.getString("Name"));
		        System.out.println("Year: " + rs.getInt("Year"));
		        System.out.println("Author: " + rs.getString("Author"));
		        System.out.println("Price: " + rs.getDouble("Price"));
		    } else {
		        System.out.println("Không tìm thấy sách với ID: " + id);
		    }
		
	}
	
	public List<Book> getAllBooks() throws SQLException 
	{
		List<Book> bl = new ArrayList<>();
		Connection conn = getCon();
		String query = "SELECT * FROM tbBook";
		PreparedStatement ps = conn.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    
	    while(rs.next())
	    {
	    	Book book = new Book(rs.getString("ID"), rs.getString("Name"), rs.getInt("Year"), 
                    rs.getString("Author"), rs.getDouble("Price"));
	    	bl.add(book);
	    }
	    return bl;
	}
	
	public void insertBook(Book book) throws SQLException 
	{
		Connection conn = getCon();
		String query = "INSERT INTO tbBook (ID, Name, Year, Author, Price) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query);
		   ps.setString(1, book.getId());
		    ps.setString(2, book.getName());
		    ps.setInt(3, book.getYear());
		    ps.setString(4, book.getAuthor());
		    ps.setDouble(5, book.getPrice());
		    
		    
		    int rowsAffected = ps.executeUpdate();
		    if (rowsAffected > 0) {
		        System.out.println("Cập nhật sách thành công!");
		    } else {
		        System.out.println("Cập nhật sách thất bại.");
		    }
		
	}
	
	public void updateBook(String ID, String Name, int Year, String Author, double Price) throws SQLException {
	    Connection con = getCon();
	    String query = "UPDATE tbBook SET Name = ?, Year = ?, Author = ?, Price = ? WHERE ID = ?";
	    PreparedStatement ps = con.prepareStatement(query);
	    ps.setString(1, Name);
	    ps.setInt(2, Year);
	    ps.setString(3, Author);
	    ps.setDouble(4, Price);
	    ps.setString(5, ID);
	    
	    
	    int rowsAffected = ps.executeUpdate();
	    if (rowsAffected > 0) {
	        System.out.println("Cập nhật sách thành công!");
	    } else {
	        System.out.println("Cập nhật sách thất bại.");
	    }
	}
	
	
	
}


