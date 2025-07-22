package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StockRetainedPanel extends JPanel {
	
	JButton getDividendBtn;
	JTable table;
	private DefaultTableModel columnName;
	private JTextField inputStockNm;
	private JTextField inputNumberOfHoldings;
	private String[] tableHeader;
	
	public StockRetainedPanel() {
//		검색 패널 생성
		JPanel searchPane = new JPanel();

//		컴포넌트들을 수직으로 배치하기 위해 BoxLayout 사용,
//		Y_AXIS를 사용해 수직 배치
		searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.Y_AXIS));
		
		searchPane.add(Box.createVerticalStrut(60)); // 수직 간격
		
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
		columnName = new DefaultTableModel(new String[] {"회사명", "예상 배당금", getYear()+"년 배당 지급 현황"}, 1);
		table = new JTable(columnName);
		table.setBorder(null);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBorder(null);
		
		add(scrollPane);
		
		add(searchPane);
		
		setVisible(true);
		
	} // StockRetainedPanel
	
//	버튼에 이벤트 리스너 등록(컨트롤에서 처리하도록)
	public void showDividendInfoWhenClick(ActionListener actionListener) {
		getDividendBtn.addActionListener(actionListener);
	}

// 올해 년도를 구하는 메소드
	public String getYear() {
		return String.valueOf(LocalDate.now().getYear());
	}
	
	
//	getter & setter
	public String getCompanyName() {
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
