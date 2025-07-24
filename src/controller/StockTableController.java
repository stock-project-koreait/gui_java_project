package controller;

import Main.MainModel;
import Main.MainView;
import model.eventUtil.StockTableEvent;

public class StockTableController {
	// gui에서 주식테이블 이벤트가 들어갈 컨트롤러

	private MainModel mainModel;
	private MainView mainView;
	private StockTableEvent stockTableEvent;

	public StockTableController() {
	}

	public StockTableController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		
		this.stockTableEvent = new StockTableEvent(mainModel, mainView);
		
	}
}
