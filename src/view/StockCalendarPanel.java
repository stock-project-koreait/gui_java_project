package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	
	private JButton selectCompanyBtn;
	private JMonthChooser month;
	private JTextField textField;
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private JTextPane day;
	
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
		
		selectCompanyBtn = new JButton("회사 검색");
		selectCompanyBtn.setBounds(600, 25, 109, 55);
		add(selectCompanyBtn);
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(69, 112, 531, 32);
		add(yearChooser);
		
		monthChooser = new JMonthChooser();
		monthChooser.setBounds(602, 112, 107, 32);
		add(monthChooser);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(69, 151, 85, 79);
		add(textPane);
		
		JPanel dayList = new JPanel(new GridLayout(6, 7));
		List<JTextPane> dayPanes = new ArrayList<>();
		
		// 6*7 달력 textPane 객체 생성
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				day = new JTextPane();
				day.setBorder(new LineBorder(Color.BLACK));
				dayList.add(day);
				dayPanes.add(day);
			}
		}
		
		for (int i = 1; i < 42; i++) {
		    dayPanes.get(i).setText(i + "");
		}
		
		dayList.setBounds(69, 151, 595, 474);
		add(dayList);
		
		JTextPane showDayAndCompany = new JTextPane();
		showDayAndCompany.setBounds(69, 650, 600, 55);
		add(showDayAndCompany);
	}
	
	// 연도와 월 선택과 회사 입력 후 회사 검색 버튼 클릭 시 달력에 배당락일 보여주는 메서드
	public void addBtnClickToShowStockDeividendCalendar(ActionListener actionListener) {
		selectCompanyBtn.addActionListener(actionListener);
	} // addBtnClickToShowStockDeividendCalendar
	
	public String getTextField() {
		return textField.getText();
	} // getTextField
	
	public int getMonthChooser() {
		return monthChooser.getMonth() + 1;
	} // getMonthChooser
	
	public int getYearChooser() {
		return yearChooser.getYear();
	}
	
}
