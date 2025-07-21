package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Main.MainModel;
import Main.MainView;
import model.StockDividendInfoModel;
import model.eventUtil.StockRetainedEvent;
import model.vo.StockDividendInfoVO;

public class StockRetainedController {
	// gui에서 보유하고 있는 주식 이벤트에 들어갈 컨트롤러

	private MainModel mainModel;
	private MainView mainView;
	private StockRetainedEvent stockRetainedEvent;

	public StockRetainedController() {
	}

	public StockRetainedController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;

		this.stockRetainedEvent = new StockRetainedEvent();

		stockRetainedEvent.buttonClickEvent(mainModel, mainView);
	}

}
