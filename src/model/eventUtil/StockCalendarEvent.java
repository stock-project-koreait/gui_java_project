package model.eventUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Main.MainModel;
import Main.MainView;
import model.apiUtil.StockDeividendInfoAPI;
import model.vo.StockDividendInfoVO;
import view.StockCalendarPanel;

// 배당락일 캘린더에 들어가는 이벤트들을 담는 클래스
public class StockCalendarEvent {
	
	public void getStockDeividendCalendar(MainModel mainModel, MainView mainView) {

		mainView.getStockCalendarPanel().addBtnClickToShowStockDeividendCalendar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				StockCalendarPanel calendarPanel = mainView.getStockCalendarPanel();
				
				// 회사 미 입력 시 경고메세지 출력
				if(calendarPanel.getTextField().isEmpty()) {
					showMessage(mainView, "회사를 입력하세요!");
				} else {
					StockDeividendInfoAPI.getApi(calendarPanel.getTextField());
				}
				
				DefaultListModel<StockDividendInfoVO> stockDividendList = mainModel.getStockDividendInfoModel().getStockDividendList();
				
				// stockDividendList 길이
				int listSize = stockDividendList.size();
				
				// stockDividendList에서 모든 배당기준일만 출력
				for (int i = 0; i < listSize; i++) {
					System.out.println(
							stockDividendList.get(i).getDvdnBasDt()
					);
				}
			}
		});

	} // getStockDeividendCalendar
	
	// 입력한 메세지 경고창 보여주는 메소드 
	public void showMessage(MainView mainView, String message) {
		JOptionPane.showMessageDialog(mainView, message);
	} // getMessage
	
	

}
