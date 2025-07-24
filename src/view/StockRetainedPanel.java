package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StockRetainedPanel extends JPanel {
	
	JButton getDividendBtn;
	JTable table;
	JPanel likeMenuPanel;
	
	private DefaultTableModel columnName;
	private static JTextField inputStockNm;
	private JTextField inputNumberOfHoldings;
	private String[] tableHeader;
	
	private JList<String> UIList = null;
	

	public StockRetainedPanel() {
		
//		즐겨찾기한 종목 목록 패널
		likeMenuPanel = new JPanel(new GridLayout(50, 1, 2, 2));
		setLayout(new BorderLayout());
		
//		메인(search)패널 생성
		JPanel searchPane = new JPanel();

//		컴포넌트들을 수직으로 배치하기 위해 BoxLayout 사용,
//		Y_AXIS를 사용해 수직 배치
		searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.Y_AXIS));
		
		add(Box.createVerticalStrut(60)); // 수직 간격
		
//		컴포넌트 생성
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel stockNm = new JLabel("주식 이름");
		inputStockNm = new JTextField(30);
		
		panel1.add(stockNm);
		panel1.add(inputStockNm);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel numberOfHoldings = new JLabel("보유 주식 수");
		inputNumberOfHoldings = new JTextField(30);
		
		panel2.add(numberOfHoldings);
		panel2.add(inputNumberOfHoldings);
		
//		검색 패널에 컴포넌트 추가
		searchPane.add(panel1);
		searchPane.add(Box.createVerticalStrut(10)); // 수직 간격
		searchPane.add(panel2);
		searchPane.add(Box.createVerticalStrut(10));
		
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		getDividendBtn = new JButton("예상 배당금 계산하기");
		btnPane.add(getDividendBtn);
		searchPane.add(btnPane);
		
		searchPane.add(Box.createVerticalStrut(30));
		
//		table의 컬럼명 설정
		columnName = new DefaultTableModel(new String[] {"회사명", "예상 배당금", getYear()+"년 배당 지급 현황"}, 0);
		table = new JTable(columnName);
		JScrollPane scrollPane = new JScrollPane(table);
		
		
		add(scrollPane, BorderLayout.CENTER);       // 테이블 중앙
		add(searchPane, BorderLayout.SOUTH);        // 검색창은 아래에

		setVisible(true);
		
	} // StockRetainedPanel
	
//	버튼에 이벤트 리스너 등록
	public void showDividendInfoWhenClick(ActionListener actionListener) {
		getDividendBtn.addActionListener(actionListener);
	}

// 올해 년도를 구하는 메소드
	public static String getYear() {
		return String.valueOf(LocalDate.now().getYear());
	}
	
	
//	getter & setter
	
	public static String getCompanyName() {
		return inputStockNm.getText();
	}
	
	public String getNumberOfHoldings() {
		return inputNumberOfHoldings.getText();
	}

	public String[] getTableHeader() {
		return tableHeader;
	}

	public void setTableHeader(String[] tableHeader) {
		this.tableHeader = tableHeader;
	}

	public DefaultTableModel getColumnName() {
		return columnName;
	}

	public void setColumnName(DefaultTableModel columnName) {
		this.columnName = columnName;
	}
	
	
	

} // class
