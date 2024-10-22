package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Managerment.DatabaseConnection;
import main.Rocket;
import main.Satellite;
import main.SpaceShip;
public class ConnectionManager {
    private DatabaseConnection dbConnection;
    private Connection conn;

    public ConnectionManager() {
        dbConnection = new DatabaseConnection();
        conn = dbConnection.connectToDataBase(); // Kết nối cơ sở dữ liệu
       
    }

    // Phương thức để lấy dữ liệu tàu từ cơ sở dữ liệu
    public ArrayList<SpaceShip> loadSpaceShips() {
        ArrayList<SpaceShip> ships = new ArrayList<>();
        try {
            String query = "SELECT * FROM space";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("Name");
                String type = rs.getString("type");
                String country = rs.getString("country");
                int yearBuilt = rs.getInt("yearBuilt");
                String currentMission = rs.getString("currentMission");

                if ("Rocket".equals(type)) {
                    int crewCapacity = rs.getInt("crewCapacity");
                    Rocket rocket = new Rocket(code, name, country, currentMission, yearBuilt, currentMission, crewCapacity);
                    ships.add(rocket);
                } else if ("Satellite".equals(type)) {
                    String rotate = rs.getString("rotate");
                    Satellite satellite = new Satellite(code, name, country, rotate, yearBuilt, currentMission, rotate);
                    ships.add(satellite);
                }
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
    }

    public void addRocketData(Rocket rocket) {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = dbConnection.connectToDataBase();
            if (conn != null) {
                String query = "INSERT INTO Space (code, Name, type, country, yearBuilt, currentMission, crewCapacity) VALUES (?, ?, ?, ?, ?, ?, ?)";
                pstm = conn.prepareStatement(query);
                pstm.setString(1, rocket.getCode());
                pstm.setString(2, rocket.getName());
                pstm.setString(3, rocket.getType());
                pstm.setString(4, rocket.getCountry());
                pstm.setInt(5, rocket.getYearBuilt());
                pstm.setString(6, rocket.getCurrentMission());
                pstm.setInt(7, rocket.getCrewCapacity());

                pstm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSatelliteData(Satellite satellite) {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = dbConnection.connectToDataBase();
            if (conn != null) {
                String query = "INSERT INTO Space (code, Name, type, country, yearBuilt, currentMission, rotate) VALUES (?, ?, ?, ?, ?, ?, ?)";
                pstm = conn.prepareStatement(query);
                pstm.setString(1, satellite.getCode());
                pstm.setString(2, satellite.getName());
                pstm.setString(3, satellite.getType());
                pstm.setString(4, satellite.getCountry());
                pstm.setInt(5, satellite.getYearBuilt());
                pstm.setString(6, satellite.getCurrentMission());
                pstm.setString(7, satellite.getRotate());

                pstm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm vệ tinh thành công");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
    public void deleteShip(String code)
    {
    	   	
    	try{
    		String query = "Delete from space where code = ?";
    		PreparedStatement pstm = conn.prepareStatement(query);
    		pstm.setString(1, code);
    		
    		int rowAffected = pstm.executeUpdate();
    		if(rowAffected > 0)
    		{
                JOptionPane.showMessageDialog(null, "Xóa thành công tàu không gian có mã: " + code);

    		}else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy tàu không gian có mã: " + code);
            }
    	}catch (SQLException e) {
            e.printStackTrace();
    	
    }
}
}
