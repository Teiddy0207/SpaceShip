package BookPackage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class GUIBook extends JFrame {
	 private JTextField txtID, txtName, txtAuthor, txtPrice;
	 private JComboBox<Integer> cmbYear;
	 private JButton btnAdd, btnUpdate;
	 private JTable bookTable;
	 private DefaultTableModel tableModel;
	 private BookProcess bookProcess;
	 
	 public GUIBook()
	 {
		 setTitle("Book Managerment");
		 setLayout(new BorderLayout());
		 txtID = new JTextField();
	     txtName = new JTextField();
	     txtAuthor = new JTextField();
	     txtPrice = new JTextField();
	     cmbYear = new JComboBox<>(new Integer[] {2018, 2019, 2020});
	    
	     
	     JPanel inputPanel = new JPanel(new GridLayout(6, 2));
	        inputPanel.add(new JLabel("ID:"));
	        inputPanel.add(txtID);
	        inputPanel.add(new JLabel("Name:"));
	        inputPanel.add(txtName);
	        inputPanel.add(new JLabel("Year:"));
	        inputPanel.add(cmbYear);
	        inputPanel.add(new JLabel("Author:"));
	        inputPanel.add(txtAuthor);
	        inputPanel.add(new JLabel("Price:"));
	        inputPanel.add(txtPrice);
	        
	        
	      
	     
	
	        String[] columnNames = {"ID", "Name", "Year", "Author", "Price"};
	        tableModel = new DefaultTableModel(columnNames, 0);
	        bookTable = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(bookTable);
	        
	        
	     
	        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
	        btnAdd = new JButton("Add Book");
	        btnUpdate = new JButton("Update Book");
	        buttonPanel.add(btnAdd);
	        buttonPanel.add(btnUpdate);
	        
	        
	        add(inputPanel, BorderLayout.NORTH);
	        add(scrollPane, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.SOUTH);
	        
	        setSize(700, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);  // Đặt cửa sổ ở giữa màn hình

	        setVisible(true);
	        
	        bookProcess = new BookProcess();
	        btnAdd.addActionListener(e -> addBook());
	        btnUpdate.addActionListener(e -> {
				try {
					updateBook();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			});
	        loadData();
	        
	       
	        bookTable.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
	            
	            public void valueChanged(ListSelectionEvent event) {
	                if (!event.getValueIsAdjusting()) {
	                    loadSelectedRowData();
	                }
	            }
	        });
	        
	 }
	 
	private void addBook()
	{
		try {
        String id = txtID.getText();
        String name = txtName.getText();
        int year = (int) cmbYear.getSelectedItem();
        String author = txtAuthor.getText();
        double price = Double.parseDouble(txtPrice.getText());
        
        bookProcess.insertBook(new Book(id, name, year, author, price));
        
        tableModel.addRow(new Object[] {id, name, year, author, price});
        
		}catch(SQLException e)
		{
			e.printStackTrace();
			   JOptionPane.showMessageDialog(this, "Error adding book: " + e.getMessage());
			
		}catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Price must be a number.");
	    }
        clearFields();
	}
	private void updateBook() throws SQLException
	{
		int selectedRow = bookTable.getSelectedRow();
		
		if(selectedRow == -1)
		{
			JOptionPane.showMessageDialog(this, "Cho biet dong co sach de cap nhat");
			return;
		}
		
		try {
	    String id = txtID.getText();
        String name = txtName.getText();
        int year = (int) cmbYear.getSelectedItem();
        String author = txtAuthor.getText();
        double price = Double.parseDouble(txtPrice.getText());
        
        bookProcess.updateBook(id, name, year, author, price);
        
        tableModel.setValueAt(name, selectedRow, 1);
        tableModel.setValueAt(year, selectedRow, 2);
        tableModel.setValueAt(author, selectedRow, 3);
        tableModel.setValueAt(price, selectedRow, 4);
        JOptionPane.showMessageDialog(this, "Book updated successfully.");
		}catch(SQLException e)
		{
			e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating book: " + e.getMessage());

		}catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price must be a number.");
        }
	}
	
	
 	private void loadData()
	{
		try {
			List<Book> books = bookProcess.getAllBooks();
			for(Book book: books) {
				tableModel.addRow(new Object[] {
						book.getId(), book.getName(), book.getYear(), book.getAuthor(), book.getPrice()		
				});
			}
		}catch(SQLException e)
		{
			 e.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage());
		}
	}
	   private void clearFields() {
	        txtID.setText("");
	        txtName.setText("");
	        txtAuthor.setText("");
	        txtPrice.setText("");
	        cmbYear.setSelectedIndex(0); // Reset về năm đầu tiên
	    }

	   
	   
	   private void loadSelectedRowData() {
	        int selectedRow = bookTable.getSelectedRow();
	        if (selectedRow != -1) {
	            // Lấy dữ liệu từ bảng
	            String id = (String) tableModel.getValueAt(selectedRow, 0);
	            String name = (String) tableModel.getValueAt(selectedRow, 1);
	            int year = (int) tableModel.getValueAt(selectedRow, 2);
	            String author = (String) tableModel.getValueAt(selectedRow, 3);
	            double price = (double) tableModel.getValueAt(selectedRow, 4);
	            
	            // Set dữ liệu lên các JTextField và JComboBox
	            txtID.setText(id);
	            txtName.setText(name);
	            cmbYear.setSelectedItem(year);
	            txtAuthor.setText(author);
	            txtPrice.setText(String.valueOf(price));
	        }
	    }
	public static void main(String[] args) {
	
		 new GUIBook();
	}

}
