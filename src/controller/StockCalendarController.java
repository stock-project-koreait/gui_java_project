package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;

import Main.MainModel;
import Main.MainView;
import model.CalendarModel;
import model.apiUtil.StockDividendInfoAPI;
import model.eventUtil.StockCalendarEvent;
import model.vo.StockDividendInfoVO;

public class StockCalendarController {
	// gui에서 캘린더에 들어갈 이벤트 담는 컨트롤러

	private MainModel mainModel;
	private MainView mainView;
	private StockCalendarEvent stockCalendarEvent;

	public StockCalendarController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		
		this.stockCalendarEvent = new StockCalendarEvent();
		
		stockCalendarEvent.getStockDeividendCalendar(mainModel, mainView);
	}
	
	// calendarModel에서 캘린더 데이터 가져옴
	public void loadCalendarData(int year, int month) {
		
		CalendarModel calendarModel = new CalendarModel();
		List<String> days = calendarModel.getCalendarDays(year, month);
	}

}
