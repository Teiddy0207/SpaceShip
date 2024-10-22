package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.ConnectionManager;

public class SpaceShipFrame extends JFrame {
    private ArrayList<SpaceShip> spaceShips;
    private JTextField txtCode, txtName, txtCounTry, txtYearBuilt, txtCurrentMission, txtCrewCapacity, txtRotate;
    private JComboBox<String> cbShipSpaceType;
    private DefaultTableModel model;
    private JTable table;
    private ConnectionManager connManager;

    public SpaceShipFrame() {
        setTitle("Quản lý tàu không gian");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spaceShips = new ArrayList<>();
        setLocationRelativeTo(null);
        connManager = new ConnectionManager();
        addUI();
        pack();
        loadData(); // Load dữ liệu từ cơ sở dữ liệu khi khởi động
    }

    private void addUI() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(new TitledBorder("Nhập thông tin tàu"));
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblCode = new JLabel("Mã tàu không gian: ");
        JLabel lblName = new JLabel("Tên tàu không gian: ");
        JLabel lblCountry = new JLabel("Đất nước sản xuất: ");
        JLabel lblYearBuilt = new JLabel("Năm sản xuất: ");
        JLabel lblCurrentMission = new JLabel("Nhiệm vụ hiện tại: ");
        JLabel lblShipType = new JLabel("Loại tàu: ");
        JLabel lblCrewCapacity = new JLabel("Số lượng phi hành đoàn: ");
        JLabel lblRotate = new JLabel("Quỹ đạo quay ở hành tinh nào: ");

        txtCode = new JTextField(15);
        txtName = new JTextField(15);
        cbShipSpaceType = new JComboBox<>(new String[]{"Rocket", "Satellite"});
        txtCounTry = new JTextField(15);
        txtYearBuilt = new JTextField(15);
        txtCurrentMission = new JTextField(15);
        txtCrewCapacity = new JTextField(15);
        txtRotate = new JTextField(15);

        inputPanel.add(lblCode); inputPanel.add(txtCode);
        inputPanel.add(lblName); inputPanel.add(txtName);
        inputPanel.add(lblShipType); inputPanel.add(cbShipSpaceType);
        inputPanel.add(lblCountry); inputPanel.add(txtCounTry);
        inputPanel.add(lblYearBuilt); inputPanel.add(txtYearBuilt);
        inputPanel.add(lblCurrentMission); inputPanel.add(txtCurrentMission);
        inputPanel.add(lblCrewCapacity); inputPanel.add(txtCrewCapacity);
        inputPanel.add(lblRotate); inputPanel.add(txtRotate);
        add(inputPanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        btnPanel.setBorder(new TitledBorder("Chức năng"));
        JButton btnAdd = new JButton("Thêm tàu");
        JButton btnDelete = new JButton("Xóa tàu");
        JButton btnSearch = new JButton("Tìm tàu");
        JButton btnOpenFile = new JButton("Mở File");
        JButton btnSaveFile = new JButton("Lưu File");
       // JButton btnLoad = new JButton("Load từ CSDL"); // Thêm nút Load

        btnPanel.add(btnAdd);
        btnPanel.add(btnDelete);
        btnPanel.add(btnSearch);
        btnPanel.add(btnOpenFile);
        btnPanel.add(btnSaveFile);
       // btnPanel.add(btnLoad); // Thêm nút Load vào giao diện
        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(btnPanel, BorderLayout.SOUTH);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã tàu", "Tên tàu", "Loại tàu", "Quốc gia", "Năm SX", "Nhiệm Vụ", "Phi hành đoàn", "Hành tinh"});
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(750, 150));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        toggleFields();
        cbShipSpaceType.addActionListener(e -> toggleFields());
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
                ButtonHandler buttonHandler = new ButtonHandler(txtCode, txtName, cbShipSpaceType, txtCounTry,
                        txtYearBuilt, txtCurrentMission, txtCrewCapacity, txtRotate, model, connManager, table);
                buttonHandler.delete();
            }
        });
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                ButtonHandler buttonHandler = new ButtonHandler(txtCode, txtName, cbShipSpaceType, txtCounTry,
                        txtYearBuilt, txtCurrentMission, txtCrewCapacity, txtRotate, model, connManager, table);
                buttonHandler.add(); 
            }
        });


     
    }

    private void toggleFields() {
        String type = (String) cbShipSpaceType.getSelectedItem();
        txtCrewCapacity.setEnabled("Rocket".equals(type));
        txtRotate.setEnabled(!"Rocket".equals(type));
    }
    
 
    


    private void loadData() {
        // Tải dữ liệu từ CSDL bằng ConnectionManager
        ArrayList<SpaceShip> ships = connManager.loadSpaceShips();
        
        // Xóa dữ liệu hiện tại trong bảng (nếu có)
        model.setRowCount(0);

        // Thêm các dòng dữ liệu vào bảng
        for (SpaceShip ship : ships) {
            if (ship instanceof Rocket) {
                Rocket rocket = (Rocket) ship;
                model.addRow(new Object[]{
                    rocket.getCode(),
                    rocket.getName(),
                    rocket.getType(),
                    rocket.getCountry(),
                    rocket.getYearBuilt(),
                    rocket.getCurrentMission(),
                    rocket.getCrewCapacity(),
                    ""
                });
            } else if (ship instanceof Satellite) {
                Satellite satellite = (Satellite) ship;
                model.addRow(new Object[]{
                    satellite.getCode(),
                    satellite.getName(),
                    satellite.getType(),
                    satellite.getCountry(),
                    satellite.getYearBuilt(),
                    satellite.getCurrentMission(),
                    "",
                    satellite.getRotate()
                });
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpaceShipFrame frame = new SpaceShipFrame();
            frame.setVisible(true);
        });
    }
}
