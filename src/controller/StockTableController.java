package controller;

import model.MainModel;
import view.MainView;

public class StockTableController {
	// gui에서 주식테이블 이벤트가 들어갈 컨트롤러

	MainModel mainModel;
	MainView mainView;

	public StockTableController() {
	}

	public StockTableController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
	}

}
