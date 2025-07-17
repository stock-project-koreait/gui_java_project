package controller;

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
	}

	public void getDate() {
		System.out.println(mainModel.getStockDividendInfoModel().getApi("삼성전자"));
//		List<String> cashDvdList = mainModel.getStockDividendInfoModel().getApi("삼성전자");
		
	}

}
