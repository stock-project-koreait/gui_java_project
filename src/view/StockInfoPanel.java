package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.StockInfoController;
import model.StockInfoModel;


public class StockInfoPanel extends JPanel {
	
	StockInfoController SIC = new StockInfoController();
	
	public StockInfoPanel() {
		
		JButton btnNewButton = new JButton("종목 정보");
		add(btnNewButton);
		
		btnNewButton.addActionListener(e->{
		SIC.getTest();
		
		}); // test 정보
		
	} // StockInfoPanel()
} // class









