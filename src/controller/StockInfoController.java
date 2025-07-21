package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import Main.MainModel;
import Main.MainView;
import model.StockDividendInfoModel;
import model.StockInfoModel;
import model.apiUtil.StockDeividendInfoAPI;
import model.apiUtil.StockInfoAPI;
import model.apiUtil.StockJson;
import model.eventUtil.StockInfoEvent;
import model.vo.StockDividendInfoVO;
import model.vo.StockInfoVO;
import view.StockInfoPanelMainGrid;

public class StockInfoController {
	// gui에서 주식 정보에 들어갈 이벤트를 담는 컨트롤러

	private MainModel mainModel;
	private MainView mainView;
	private StockInfoEvent stockInfoEvent;
	
	public StockInfoController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		
		this.stockInfoEvent = new StockInfoEvent();
		
		stockInfoEvent.getInfoList(mainModel, mainView);
		
	}
	
} //class








