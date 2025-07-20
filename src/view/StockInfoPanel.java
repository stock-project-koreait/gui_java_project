package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class StockInfoPanel extends JPanel {
	
	
	private InfoPanelMainGrid infoPanelMainGrid;
	
    // 데이터 표시 라벨들 (필드 선언)
	private JPanel leftMenuPanel;
	private JLabel stockName;
	private JButton investInfoBtn;
	
    public JPanel getLeftMenuPanel() {
		return leftMenuPanel;
	}
    
	public JButton getInvestInfoBtn() {
		return investInfoBtn;
	}
	
	public JLabel getStockName() {
		return stockName;
	}

	public InfoPanelMainGrid getInfoPanelMainGrid() {
		return infoPanelMainGrid;
	}
	
	public StockInfoPanel() {
		
		infoPanelMainGrid = new InfoPanelMainGrid();
        setLayout(new BorderLayout());

        // 1. 왼쪽 메뉴 (40개)
        leftMenuPanel = new JPanel(new GridLayout(800, 1, 2, 2));
        // 스크롤 패널에 메뉴 패널 넣기
        JScrollPane menuScrollPane = new JScrollPane(leftMenuPanel);
        menuScrollPane.setPreferredSize(new Dimension(200, 0));
        add(menuScrollPane, BorderLayout.WEST);
        // 스크롤 속도 조절
        menuScrollPane.getVerticalScrollBar().setUnitIncrement(24); 	
        
        // 2. 중앙 메인 패널 (상단: 주식이름/투자가치판단, 중단: 정보그리드)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 2-1. 상단 정보 (주식이름 + 투자가치판단)
        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(0, 120)); // 높이만 지정

        stockName = new JLabel("주식이름", SwingConstants.CENTER);
        stockName.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        stockName.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        stockName.setBounds(30, 20, 300, 50);
        topPanel.add(stockName);

        investInfoBtn = new JButton("주식 정보 불러오기");
        investInfoBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        investInfoBtn.setBounds(400, 30, 180, 40);
        topPanel.add(investInfoBtn);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // 따로만든 grid 패널 붙히기
        mainPanel.add(infoPanelMainGrid, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }
    
	// 정보 불러오키 click 메소드
	public void infoClick(ActionListener actionListener) {
		investInfoBtn.addActionListener(actionListener);
	}// StockInfoPanel()
	
} // class






