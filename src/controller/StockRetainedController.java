package controller;

import model.MainModel;
import view.MainView;

public class StockRetainedController {
	// gui에서 보유하고 있는 주식 이벤트에 들어갈 컨트롤러

	MainModel mainModel;
	MainView mainView;

	public StockRetainedController() {
	}

	public StockRetainedController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
	}

}
