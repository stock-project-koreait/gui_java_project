package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.MainModel;
import view.MainView;

public class StockCalendarController {
	// gui에서 캘린더에 들어갈 이벤트 담는 컨트롤러

	MainModel mainModel;
	MainView mainView;
	

	public StockCalendarController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		
		getDate();
	}

	public void getDate() {
		
		mainView.getStockCalendarPanel().addbtnClickToPrint(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(mainModel.getStockDividendInfoModel().getApi("삼성전자"));
				System.out.println("view 컨트롤러 테스트");
			}
		});
		
	} // getDate
	
}
