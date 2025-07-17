package controller;

import model.MainModel;
import model.StockInfoModel;
import view.MainView;

public class StockInfoController {
	// gui에서 주식 정보에 들어갈 이벤트를 담는 컨트롤러

	MainModel mainModel;
	MainView mainView;

	public StockInfoController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
	}

	public void getTable() {

		mainModel.getStockInfoModel().getStockInfoList();
	}

}
