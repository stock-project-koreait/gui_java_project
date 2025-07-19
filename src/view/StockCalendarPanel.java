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
	
	JButton btn;
	
	public StockCalendarPanel() {
		initCalendar();
	}
	
	public void initCalendar() {
		JTextPane tp = new JTextPane();
		tp.setText("테스트 텍스트 패인");
//		tp.setBackground(Color.BLACK);
//		tp.setForeground(Color.WHITE);
		add(tp);
		
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBackground(attr, Color.BLUE);
		tp.setCharacterAttributes(attr, true);
		
		btn = new JButton("콘솔 테스트");
		add(btn);
		
		
	}
	
	// 버튼 클릭 시 콘솔에 출력하는 메서드
	public void addbtnClickToPrint(ActionListener actionListener) {
		btn.addActionListener(actionListener);
	}
}
