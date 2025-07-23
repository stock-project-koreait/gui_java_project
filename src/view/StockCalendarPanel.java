package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class StockCalendarPanel extends JPanel {
	
	private JButton selectCompanyBtn;
	private JTextField textField;
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private JLabel showDayAndCompany;
	private List<JTextPane> days;
	
	public StockCalendarPanel() {
		initCalendar();
	}
	
	public void initCalendar() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(Box.createVerticalStrut(20));
		
		// 회사 입력, 회사 검색 버튼
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField(30);
		selectCompanyBtn = new JButton("회사 검색");
		textField.setPreferredSize(new Dimension(400, 40));
		selectCompanyBtn.setPreferredSize(new Dimension(100, 40));
		searchPanel.add(textField);
		searchPanel.add(selectCompanyBtn);
		add(searchPanel);
		
		add(Box.createVerticalStrut(20)); // 수직 공백
		
		// 년도, 월 선택
		JPanel chooserPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		chooserPanel.setLayout(new BoxLayout(chooserPanel, BoxLayout.X_AXIS));
		chooserPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		yearChooser = new JYearChooser();
		monthChooser = new JMonthChooser();
		
		// 크기 조정
		yearChooser.setPreferredSize(new Dimension(330, 40));
		yearChooser.setMaximumSize(new Dimension(330, 40));
		monthChooser.setPreferredSize(new Dimension(250, 40));
		monthChooser.setMaximumSize(new Dimension(250, 40));
		
		chooserPanel.add(Box.createHorizontalGlue()); // 왼쪽 공백
		chooserPanel.add(yearChooser);
		chooserPanel.add(Box.createHorizontalStrut(20)); // 가로 공백
		chooserPanel.add(monthChooser);
		
		add(chooserPanel);
		
		add(Box.createVerticalStrut(30));
		
		// 캘린더 day들의 JTextPane를 담는 리스트 객체 생성
		days = new ArrayList<JTextPane>();
		
		// 날짜 캘린더 (6*7)
		JPanel dayList = new JPanel(new GridLayout(6, 7));
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				JTextPane day = new JTextPane();
				day.setBorder(new LineBorder(Color.GRAY));
				day.setPreferredSize(new Dimension(70, 70));
				dayList.add(day);
				days.add(day);
			}
		}
		add(dayList);

		add(Box.createVerticalStrut(80)); // 수직 공백
		
		// 결과 출력
		showDayAndCompany = new JLabel("배당락일", SwingConstants.CENTER);
		showDayAndCompany.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		showDayAndCompany.setPreferredSize(new Dimension(300, 60));
		
		// 최대 크기 부모 패널 가로 길이와 같게 지정
		showDayAndCompany.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		showDayAndCompany.setMinimumSize(new Dimension(300, 60));

		// BoxLayout에서 X축 가운데 정렬
		showDayAndCompany.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(showDayAndCompany);
		
		add(Box.createVerticalStrut(100));
	} // initCalendar
	
	// 년도, 월, 회사 이름 입력 후 회사 검색 클릭 시 해당 회사의 배당락일 캘린더에 표시
	public void addbtnClickToShowDividendCalendar(ActionListener actionListener) {
		selectCompanyBtn.addActionListener(actionListener);
	} // addbtnClickToShowDividendCalendar	
	
	// 선택한 월
	public int getMonthChooser() {
		return monthChooser.getMonth();
	} // getMonthChooser
	
	// 선택한 년도
	public int getYearChooser() {
		return yearChooser.getYear();
	} // getYearChooser
	
	// 입력한 회사 이름
	public String getTextField() {
		return textField.getText();
	} // getTextField
	
	// 캘린더 날짜들을 담는 JTextPane 리스트 반환
	public List<JTextPane> getDaysList(){
		return days;
	} // getDaysList
	
	public JLabel getShowDayAndCompany() {
		return showDayAndCompany;
	} // getShowDayAndCompany
	
}