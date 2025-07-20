package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import controller.StockCalendarController;

public class StockCalendarPanel extends JPanel {
	
	JButton btn;
	JMonthChooser month;
	JTextField textField;
	
	public StockCalendarPanel() {
		initCalendar();
	}
	
	public void initCalendar() {
		
		setBounds(100, 100, 800, 800);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(55, 25, 545, 55);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(600, 25, 109, 55);
		add(btnNewButton);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(69, 112, 531, 32);
		add(yearChooser);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(602, 112, 107, 32);
		add(monthChooser);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(69, 151, 85, 79);
		add(textPane);
		
		JPanel dayList = new JPanel(new GridLayout(6, 7));
		
		for(int i=1; i<7; i++) {
			for(int j=1; j<8; j++) {
				JTextPane day = new JTextPane();
				day.setBorder(new LineBorder(Color.BLACK));
				dayList.add(day);
			}
		}
		
		dayList.setBounds(69, 151, 595, 474);
		add(dayList);
		
		JTextPane showDayAndCompany = new JTextPane();
		showDayAndCompany.setBounds(69, 650, 600, 55);
		add(showDayAndCompany);
	}
	
	
	public void addbtnClickToPrint(ActionListener actionListener) {
		btn.addActionListener(actionListener);
	}
}
