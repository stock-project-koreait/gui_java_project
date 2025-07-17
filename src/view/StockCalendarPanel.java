package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.StockCalendarController;

public class StockCalendarPanel extends JPanel {
	
	StockCalendarController scc;
	
	public StockCalendarPanel() {
		JButton newBtn = new JButton("캘린더");
		add(newBtn);
		
		newBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				scc.getDate();
			}
		});
	}

}
