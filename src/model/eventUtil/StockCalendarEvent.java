package model.eventUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import Main.MainModel;
import Main.MainView;
import model.apiUtil.StockDeividendInfoAPI;
import model.vo.StockDividendInfoVO;

// 배당락일 캘린더에 들어가는 이벤트들을 담는 클래스
public class StockCalendarEvent {
	
	public void getDate(MainModel mainModel, MainView mainView) {

		mainView.getStockCalendarPanel().addbtnClickToPrint(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("view 컨트롤러 테스트");
				StockDeividendInfoAPI.getApi("삼성전자");
				
				DefaultListModel<StockDividendInfoVO> stockDividendList = mainModel.getStockDividendInfoModel().getStockDividendList();
				
				int listSize = stockDividendList.size();
				// stockDividendList에서 모든 배당기준일만 출력
				for (int i = 0; i < listSize; i++) {
					System.out.println(
							stockDividendList.get(i).getDvdnBasDt());
				}
			}
		});

	} // getDate
	
	

}
