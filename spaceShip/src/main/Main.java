package main;

import javax.swing.SwingUtilities;

public class Main {
    
	
	public static void main(String[] args) {
     SwingUtilities.invokeLater(() -> {
 
    	 SpaceShipFrame frame = new SpaceShipFrame();
    	 frame.setVisible(true);
     });  
    }
}

