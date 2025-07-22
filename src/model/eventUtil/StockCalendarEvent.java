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

// 배당락일 캘린더에 들어가는 이벤트들을 담는 클래스
public class StockCalendarEvent {
	
	public void getDate(MainModel mainModel, MainView mainView) {

		mainView.getStockCalendarPanel().addbtnClickToShowDividendCalendar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String companyName = mainView.getStockCalendarPanel().getTextField();
				
				if(companyName.isEmpty()) {
					showMessage(mainView, "회사명을 입력하세요!");
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

	} // getDate
	
	// 메세지 경고창 출력하는 메서드
	public void showMessage(MainView mainView, String message) {
		JOptionPane.showMessageDialog(mainView, message);
	} // showMessage

}
