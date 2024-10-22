package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ConnectionManager;

public class ButtonHandler implements ActionListener {
    private JTextField txtCode, txtName, txtCountry, txtYearBuilt, txtMission, txtCrewCapacity, txtRotate;
    private JComboBox<String> cbShipSpaceType;
    private DefaultTableModel model;
    private ConnectionManager connManager;
    private JTable table;
    
    
    public ButtonHandler(JTextField txtCode, JTextField txtName, JComboBox<String> cbShipSpaceType,
                         JTextField txtCountry, JTextField txtYearBuilt, JTextField txtMission,
                         JTextField txtCrewCapacity, JTextField txtRotate, DefaultTableModel model, ConnectionManager connManager, JTable table) {
        this.txtCode = txtCode;
        this.txtName = txtName;
        
        this.txtCountry = txtCountry;
        this.txtYearBuilt = txtYearBuilt;
        this.txtMission = txtMission;
        this.txtCrewCapacity = txtCrewCapacity; // Thêm tham số cho Rocket
        this.txtRotate = txtRotate; // Thêm tham số cho Satellite
        this.cbShipSpaceType = cbShipSpaceType;
        this.model = model;  
        this.connManager = connManager;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	 String action = e.getActionCommand();
    	 if ("Thêm tàu".equals(action)) {
             add();
         } else if ("Xóa Tàu".equals(action)) {
             delete();
         }
    }
    
    public void add() {
        String code = txtCode.getText().trim();
        String name = txtName.getText().trim();
        String country = txtCountry.getText().trim();
        int yearBuilt;
        String currentMission = txtMission.getText().trim();
        String type = (String) cbShipSpaceType.getSelectedItem();
        
        // Kiểm tra nhập liệu
        if (code.isEmpty() || name.isEmpty() || country.isEmpty() || currentMission.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        try {
            yearBuilt = Integer.parseInt(txtYearBuilt.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Năm xây dựng phải là một số nguyên hợp lệ!");
            return;
        }

        try {
            if ("Rocket".equals(type)) {
                int crewCapacity = Integer.parseInt(txtCrewCapacity.getText().trim());
                Rocket rocket = new Rocket(code, name, type, country, yearBuilt, currentMission, crewCapacity);
                connManager.addRocketData(rocket);
                model.addRow(new Object[]{code, name, type, country, yearBuilt, currentMission, crewCapacity, ""});
                JOptionPane.showMessageDialog(null, "Thêm tàu tên lửa thành công!");
            } else if ("Satellite".equals(type)) {
                String rotate = txtRotate.getText().trim();
                Satellite satellite = new Satellite(code, name, type, country, yearBuilt, currentMission, rotate);
                connManager.addSatelliteData(satellite);
                model.addRow(new Object[]{code, name, type, country, yearBuilt, currentMission, "", rotate});
                JOptionPane.showMessageDialog(null, "Thêm vệ tinh thành công!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm dữ liệu: " + ex.getMessage());
        }
    }

    
    public  void delete()
    {
    	   int selectedRow = table.getSelectedRow(); 
    	
    	if(selectedRow >= 0)
    	{
    		
    	String code = (String) model.getValueAt(selectedRow, 0);
    	
    	connManager.deleteShip(code);
    	
    	model.removeRow(selectedRow);
    	JOptionPane.showMessageDialog(null, "Tàu có mã " + code + " đã được xóa thành công.");
    	
    	}else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
        }
    }

}
