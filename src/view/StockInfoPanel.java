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
	
    // 데이터 표시 라벨들 (필드 선언)
	private JPanel leftMenuPanel;
	private JLabel stockName;
    private JLabel lblSysdate;
    private JLabel lblDividendDate;
    private JLabel lblOpen;
    private JLabel lblClose;
    private JLabel lblHigh;
    private JLabel lblLow;
    private JLabel lblCashDate;
	private JLabel lblDvdnRcd;
	private JLabel lblDvdnAmt;
	private JLabel lblCashDvdnRt;
	private JButton investInfoBtn;
	
    public JPanel getLeftMenuPanel() {
		return leftMenuPanel;
	}

	public StockInfoPanel() {
        setLayout(new BorderLayout());

        // 1. 왼쪽 메뉴 (40개)
        leftMenuPanel = new JPanel(new GridLayout(40, 1, 2, 2));
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

        // 정보 그리드 (5행 4열)
        JPanel infoGridPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        infoGridPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        
        // 1행
        infoGridPanel.add(new JLabel("SYSDATE 시간 : ", SwingConstants.CENTER));
        lblSysdate = new JLabel("SYSDATE 시간", SwingConstants.CENTER);
        lblSysdate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblSysdate);
        infoGridPanel.add(new JLabel("최근 배당 기준일 : ", SwingConstants.CENTER));
        lblDividendDate = new JLabel("최근 배당 기준일", SwingConstants.CENTER);
        lblDividendDate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblDividendDate);

        // 2행
        infoGridPanel.add(new JLabel("시가 : ", SwingConstants.CENTER));
        lblOpen = new JLabel("시가", SwingConstants.CENTER);
        lblOpen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblOpen);
        infoGridPanel.add(new JLabel("최근 현금지급일 : ", SwingConstants.CENTER));
        lblCashDate = new JLabel("최근 현금지급일", SwingConstants.CENTER);
        lblCashDate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblCashDate);

        // 3행
        infoGridPanel.add(new JLabel("종가 : ", SwingConstants.CENTER));
        lblClose = new JLabel("종가", SwingConstants.CENTER);
        lblClose.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblClose);
        infoGridPanel.add(new JLabel("배당타입 : ", SwingConstants.CENTER));
        lblDvdnRcd = new JLabel("배당타입", SwingConstants.CENTER);
        lblDvdnRcd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblDvdnRcd); // 필요시 추가 변수 선언

        // 4행
        infoGridPanel.add(new JLabel("고가 : ", SwingConstants.CENTER));
        lblHigh = new JLabel("고가", SwingConstants.CENTER);
        lblHigh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblHigh);
        infoGridPanel.add(new JLabel("한 주당 배당금 : ", SwingConstants.CENTER));
        lblDvdnAmt = new JLabel("한 주당 배당금", SwingConstants.CENTER);
        lblDvdnAmt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblDvdnAmt);

        // 5행
        infoGridPanel.add(new JLabel("저가 : ", SwingConstants.CENTER));
        lblLow = new JLabel("저가", SwingConstants.CENTER);
        lblLow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblLow);
        infoGridPanel.add(new JLabel("현금배당률 : ", SwingConstants.CENTER));
        lblCashDvdnRt = new JLabel("현금배당률", SwingConstants.CENTER);
        lblCashDvdnRt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        infoGridPanel.add(lblCashDvdnRt);

        mainPanel.add(infoGridPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }
    
	public void infoClick(ActionListener actionListener) {
		investInfoBtn.addActionListener(actionListener);
	}// StockInfoPanel()


	public void diviClick(ActionListener actionListener) {
		//dividendButton.addActionListener(actionListener);
	} // StockInfoPanel
	
	
} // class

//infoButton = new JButton("종목 정보");
//dividendButton = new JButton("배당 정보");
//add(infoButton);
//add(dividendButton);







