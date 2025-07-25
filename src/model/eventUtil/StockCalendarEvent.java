package model.eventUtil;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import Main.MainModel;
import Main.MainView;
import model.CalendarModel;
import model.apiUtil.StockDividendInfoAPI;
import model.vo.StockDividendInfoVO;
import view.StockCalendarPanel;

// 배당락일 캘린더에 들어가는 이벤트들을 담는 클래스
public class StockCalendarEvent {
	
	private List<String> dayStrings; // 연도, 월에 맞는 날짜 데이터들을 담는 리스트
	private List<JTextPane> days;
	
	public void getStockDeividendCalendar(MainModel mainModel, MainView mainView) {

		mainView.getStockCalendarPanel().addbtnClickToShowDividendCalendar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				StockCalendarPanel calendarPanel = mainView.getStockCalendarPanel();
				String companyName = calendarPanel.getTextField();
				int month = calendarPanel.getMonthChooser();
				int year = calendarPanel.getYearChooser();
				
				mainModel.getStockDividendInfoModel().getStockDividendList().clear();
				
				// 회사 미입력 시 경고창 출력
				companyIsEmptyToShowMessage(mainView, companyName);

				getCalendarDaysToView(mainView, month, year);
				
				// 배당금정보 모델에서 가져온 리스트를 stockDividendList 변수에 담음
				DefaultListModel<StockDividendInfoVO> stockDividendList = mainModel.getStockDividendInfoModel().getStockDividendList();
				// String list 객체 생성
				List<String> strDvdnLit = new ArrayList<String>();
				
				int listSize = stockDividendList.size();
				// 배당기준일만 String list에 넣음
				for (int i = 0; i < listSize; i++) {
					strDvdnLit.add(stockDividendList.get(i).getDvdnBasDt().toString());
				}
				
				List<Date> dateList = getStringToDate(strDvdnLit);

				int apiYear = 0;
				int apiMonth = 0;
				int apiDay = 0;
				int dayOfWeek = 0;
				
				for(Date date : dateList) {
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					
					calendar.add(Calendar.DATE, -2);
					
					dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
					// 주말일경우 금요일로 배당락일이 됨
					if(dayOfWeek == Calendar.SATURDAY) { // 토요일이면
						calendar.add(Calendar.DATE, -1);
					} else if(dayOfWeek == Calendar.SUNDAY) { // 일요일이면
						calendar.add(Calendar.DATE, -2);
					}
					
					apiYear = calendar.get(Calendar.YEAR);
					apiMonth = calendar.get(Calendar.MONTH)+1;
					apiDay = calendar.get(Calendar.DATE);
					dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
					
					if(apiYear == year && apiMonth == (month+1)) {
						
						int dayStringsSize = dayStrings.size();
						for(int i=0; i<dayStringsSize; i++) {
							String strday = dayStrings.get(i);
							
							try {
								int dayValue = Integer.parseInt(strday);
								if(dayValue == apiDay) {
									days.get(i).setBackground(Color.CYAN);
									
									calendarPanel.getShowDayAndCompany()
										.setText(
											getDayOfWeek(dayOfWeek) + " "	
											+ String.valueOf(apiYear) + "년 "
											+ String.valueOf(apiMonth) + "월 "
											+ String.valueOf(apiDay) + "일 " 
											+ companyName + " 배당락일"
										);
								} 
							} catch (NumberFormatException nfe) {
								continue;
							}
						}
					}
				}
			}
		});

	} // getStockDeividendCalendar
	
	public String getDayOfWeek(int dayOfWeek) {
		switch (dayOfWeek) {
		case Calendar.SUNDAY: return "일요일";
		case Calendar.MONDAY : return "월요일";
		case Calendar.TUESDAY : return "화요일";
		case Calendar.WEDNESDAY : return "수요일";
		case Calendar.THURSDAY : return "목요일";
		case Calendar.FRIDAY : return "금요일";
		case Calendar.SATURDAY : return "토요일";
		default : return "요일이 없음";
		}
	}
	
	
	// view에서 선택한 연도, 월에 맞는 날짜 데이터들을 보여주는 메서드
	public void getCalendarDaysToView(MainView mainView, int month, int year) {
		
		// calendarPanel에 각 날짜들을 담는 textPane리스트를 days라는 list 변수에 담음
		days = mainView.getStockCalendarPanel().getDaysList();
		// calendarModel 객체 생성
		CalendarModel calendarModel = new CalendarModel();
		
		// 연도와 월에 맞는 캘린더 날짜들을 담고 있는 getCalendarDays메서드 호출
		// view에서 선택한 연도, 월에 맞는 날짜들을 dayStrings list에 담음
		// ex) [, , 1, 2, 3, ....., 31, , ,]
		dayStrings = calendarModel.getCalendarDays(year, month);

		int daysSize = days.size();
		// days textPane에 dayStrings를 days 길이에 맞게 넣음
		for (int i = 0; i < daysSize; i++) {
			days.get(i).setText(dayStrings.get(i));
			
			days.get(i).setBackground(Color.WHITE);
		}
		
	} // getCalendarDaysToView
	
	// 배당락일 주말, 공휴일 제외
	public void weekendAndHolyDay(Calendar calendar, MainView mainView) {
	
		
		
	}
	
	public void companyIsEmptyToShowMessage(MainView mainView, String companyName) {
		
		// 회사 미입력 시 경고메세지 출력
		if(companyName.isEmpty()) {
			showMessage(mainView, "회사를 입력하세요!");
		} else {
			StockDividendInfoAPI.getApi(companyName);
		}
		
	} // companyIsEmptyToShowMessage
	
	// 입력한 메세지 경고창 보여주는 메소드 
	public void showMessage(MainView mainView, String message) {
		JOptionPane.showMessageDialog(mainView, message);
	} // showMessage
	
	// String인 날짜를 Date로 변환 후 List에 담음
	public static List<Date> getStringToDate(List<String> strList) {

		// 연도월일로 날짜 format
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			// date 담을 ArrayList 생성
			List<Date> dateList = new ArrayList<Date>();
			// 배당 기준일을 문자열로 된 날짜를 가져와서
			dateList = strList.stream().filter(day -> !day.isEmpty()) // null 제외
					.map(day -> {
						try {
							return sdf.parse(day.toString()); // format으로 date로 변경
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}).toList();

			return dateList; // dateList로 반환
		} // getStringToDate
	
}
