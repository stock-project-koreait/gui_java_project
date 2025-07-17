package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MainController;

public class StockInfoPanel extends JPanel {
	
	MainController mainController;
	
	public StockInfoPanel() {
		
		JButton btnNewButton = new JButton("종목 정보");
		add(btnNewButton);
		
		
		
	}
	
	
	
}
