package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import controller.StockCalendarController;

public class StockCalendarPanel extends JPanel {
	
	StockCalendarController scc;
	
	public StockCalendarPanel() {
		Calendar calendar = Calendar.getInstance();
		
		JTextPane tp = new JTextPane();
		tp.setText("테스트 텍스트 패인");
//		tp.setBackground(Color.BLACK);
//		tp.setForeground(Color.WHITE);
		add(tp);
		
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBackground(attr, Color.BLUE);
		tp.setCharacterAttributes(attr, true);
		
	}

}
