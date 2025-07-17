package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainView extends JFrame {
	
	public void initView() {
		
		setTitle("주식 프로젝트");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// 템페인 생성
		JTabbedPane tabbedPane = new JTabbedPane();
		
		// 종목리스트 페널
		StockTablePanel stockTablePanel = new StockTablePanel();
		tabbedPane.add("종목 리스트", stockTablePanel);
		
		// 종목 정보 페널
		StockInfoPanel stockInfoPanel = new StockInfoPanel();
		tabbedPane.add("종목 정보", stockInfoPanel);
		
		// 배당락일 캘린더 패널
		StockCalendarPanel stockCalendarPanel = new StockCalendarPanel();
		tabbedPane.add("배당락일 캘린더", stockCalendarPanel);
		
		// 보유하고 있는 주식 패널
		StockRetainedPanel stockRetainedPanel = new StockRetainedPanel();
		tabbedPane.add("보유하고 있는 주식" , stockRetainedPanel);
	
		
		add(tabbedPane);
		setVisible(true);
	}
}
