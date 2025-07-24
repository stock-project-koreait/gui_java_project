package view;

import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StockInfoPanelMainGrid extends JPanel{

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
	
	public JLabel getLblSysdate() {
		return lblSysdate;
	}

	public JLabel getLblDividendDate() {
		return lblDividendDate;
	}

	public JLabel getLblOpen() {
		return lblOpen;
	}

	public JLabel getLblClose() {
		return lblClose;
	}

	public JLabel getLblHigh() {
		return lblHigh;
	}

	public JLabel getLblLow() {
		return lblLow;
	}

	public JLabel getLblCashDate() {
		return lblCashDate;
	}

	public JLabel getLblDvdnRcd() {
		return lblDvdnRcd;
	}

	public JLabel getLblDvdnAmt() {
		return lblDvdnAmt;
	}
	public JLabel getLblCashDvdnRt() {
		return lblCashDvdnRt;
	}

	public StockInfoPanelMainGrid() {
		setLayout(new GridLayout(5, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        
        // 1행
        add(new JLabel("SYSDATE 시간 : ", SwingConstants.CENTER));
        lblSysdate = new JLabel("SYSDATE 시간", SwingConstants.CENTER);
        lblCSS(lblSysdate);
        add(lblSysdate);
        add(new JLabel("최근 배당 기준일 : ", SwingConstants.CENTER));
        lblDividendDate = new JLabel("최근 배당 기준일", SwingConstants.CENTER);
        lblCSS(lblDividendDate);
        add(lblDividendDate);

        // 2행
        add(new JLabel("시가 : ", SwingConstants.CENTER));
        lblOpen = new JLabel("시가", SwingConstants.CENTER);
        lblCSS(lblOpen);
        add(lblOpen);
        add(new JLabel("최근 현금지급일 : ", SwingConstants.CENTER));
        lblCashDate = new JLabel("최근 현금지급일", SwingConstants.CENTER);
        lblCSS(lblCashDate);
        add(lblCashDate);

        // 3행
        add(new JLabel("종가 : ", SwingConstants.CENTER));
        lblClose = new JLabel("종가", SwingConstants.CENTER);
        lblCSS(lblClose);
        add(lblClose);
        add(new JLabel("배당타입 : ", SwingConstants.CENTER));
        lblDvdnRcd = new JLabel("배당타입", SwingConstants.CENTER);
        lblCSS(lblDvdnRcd);
        add(lblDvdnRcd); // 필요시 추가 변수 선언

        // 4행
        add(new JLabel("고가 : ", SwingConstants.CENTER));
        lblHigh = new JLabel("고가", SwingConstants.CENTER);
        lblCSS(lblHigh);
        add(lblHigh);
        add(new JLabel("한 주당 배당금 : ", SwingConstants.CENTER));
        lblDvdnAmt = new JLabel("한 주당 배당금", SwingConstants.CENTER);
        lblCSS(lblDvdnAmt);
        add(lblDvdnAmt);

        // 5행
        add(new JLabel("저가 : ", SwingConstants.CENTER));
        lblLow = new JLabel("저가", SwingConstants.CENTER);
        lblCSS(lblLow);
        add(lblLow);
        add(new JLabel("현금배당률 : ", SwingConstants.CENTER));
        lblCashDvdnRt = new JLabel("현금배당률", SwingConstants.CENTER);
        lblCSS(lblCashDvdnRt);	
        add(lblCashDvdnRt);
	}
	
	// 라벨 CSS 적용
	private void lblCSS (JLabel lbl) {
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		
	}
	
}







