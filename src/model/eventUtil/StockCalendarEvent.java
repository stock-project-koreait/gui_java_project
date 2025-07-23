package model.eventUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import Main.MainModel;
import Main.MainView;
import model.CalendarModel;
import model.StockDividendInfoModel;
import model.apiUtil.StockDividendInfoAPI;
import model.vo.StockDividendInfoVO;
import view.StockCalendarPanel;

// 배당락일 캘린더에 들어가는 이벤트들을 담는 클래스
public class StockCalendarEvent {
	
	public void getStockDeividendCalendar(MainModel mainModel, MainView mainView) {

		mainView.getStockCalendarPanel().addbtnClickToShowDividendCalendar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String companyName = mainView.getStockCalendarPanel().getTextField();
				
				// 회사 미입력 시 경고메세지 출력
				if(companyName.isEmpty()) {
					showMessage(mainView, "회사를 입력하세요!");
				} else {
					StockDividendInfoAPI.getApi(companyName);
				}

				StockDividendInfoAPI.getApi(companyName);
				
				DefaultListModel<StockDividendInfoVO> stockDividendList = mainModel.getStockDividendInfoModel().getStockDividendList();
//				
//				int listSize = stockDividendList.size();
//				// stockDividendList에서 모든 배당기준일만 출력
//				for (int i = 0; i < listSize; i++) {
//					System.out.println(
//							stockDividendList.get(i).getDvdnBasDt());
//				}
				

				CalendarModel calendarModel = new CalendarModel();
				List<JTextPane> days = mainView.getStockCalendarPanel().getDaysList();
				int daysSize = days.size();
				
				List<String> dayStrings = calendarModel.getCalendarDays(
						mainView.getStockCalendarPanel().getYearChooser(),
						mainView.getStockCalendarPanel().getMonthChooser()
				);

				for (int i = 0; i < daysSize; i++) {
					days.get(i).setText(dayStrings.get(i));
				}
				
			}
		});

	} // getStockDeividendCalendar
	
	// 입력한 메세지 경고창 보여주는 메소드 
	public void showMessage(MainView mainView, String message) {
		JOptionPane.showMessageDialog(mainView, message);
	} // showMessage
	
}
