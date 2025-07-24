package model.eventUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Main.MainModel;
import Main.MainView;
import model.apiUtil.StockDividendInfoAPI;
import model.vo.StockDividendInfoVO;
import view.StockRetainedPanel;

// 보유하고 있는 주식에 들어가는 이벤트들을 담는 클래스

public class StockRetainedEvent {

	public void buttonClickEvent(MainModel mainModel, MainView mainView) {
		mainView.getStockRetainedPanel().showDividendInfoWhenClick(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				사용자가 입력 후 버튼을 누르면 검증 후 데이터 보내줌
				verifiyingInputValue(mainModel, mainView); 
			}
		});
	} // buttonClickEvent

	/*
	 * [기능 별 메소드] 
	 * 1. 가장 최근 데이터 중에 
	 * 2. api 리스트 안에서 가장 최근 배당 데이터 리턴 
	 * 3. 최근 지급한 1주당 배당금 리턴 
	 * 4. 보유 주식 수 * 1주당 배당금 곱해서 다음 배당 지급일에 받을 배당금 리턴
	 */
	
	
//	사용자 입력 값을 검증하는 메소드
	public void verifiyingInputValue(MainModel mainModel, MainView mainView) {
		
		String companyNm = mainView.getStockRetainedPanel().getCompanyName();
		String retainedStock = mainView.getStockRetainedPanel().getNumberOfHoldings();
		
		if (companyNm.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "주식명을 입력해 주세요.", "information", 
					JOptionPane.INFORMATION_MESSAGE);
		} else if (retainedStock.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "보유 주식 수를 입력해 주세요.", "information",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (companyNm.isEmpty() && retainedStock.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "주식명과 보유 주식 수를 입력해 주세요.", "information",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			makeList(mainModel, mainView); 
		}
	} // getUIList
	
	
	public void makeList(MainModel mainModel, MainView mainView) {
		String companyNm = mainView.getStockRetainedPanel().getCompanyName(); // 사용자가 입력한 회사명
		String retainedStock = mainView.getStockRetainedPanel().getNumberOfHoldings(); // 사용자가 입력한 보유 주식 수
		int numberOfretainedStock = Integer.parseInt(retainedStock);
		
		mainView.getStockRetainedPanel()
		.getColumnName()
		.addRow(new Object[] {
				StockRetainedPanel.getCompanyName(), // 회사 이름
//				getExpectedDividend(객체,numberOfretainedStock), // 예상 배당금 계산 결과
//				getDividendPaymentsatus(mainView, mainModel) // 올해 배당금 지급 현황
			}); 
		
	} // makeList

	
//	예상 배당금 계산 후 String으로 바꾸고 리턴해주는 메소드
	public String getExpectedDividend(StockDividendInfoVO obj, int numberOfretainedStock) {
		return Integer.toString(Integer.parseInt(obj.getStckGenrDvdnAmt()) * numberOfretainedStock) + " 원";
	} // getExpectedDividend
	
	
//	배당금 지급 현황을 String으로 리턴하는 메소드
	public static String getDividendPaymentsatus(MainView mainView, MainModel mainModel) {
		
//		올해 현금배당지급일(String) 리스트를 Date타입 리스트로 저장
		List<Date> dateList = getStringToDate(getSelectDateForThisYearList(mainView, mainModel));
		
//		Collections.sort(dateList); // 가장 오래된 날짜부터 정렬(오름차순)
		System.out.println(dateList);

		SimpleDateFormat monthFormat = new SimpleDateFormat("M"); // 월만 추출(1~12)
		List<String> monthList = new ArrayList<String>();
		
		for(Date date : dateList) {
//			현금배당지급 날짜에서 월만 뺀 후 문자열로 저장
			String month = monthFormat.format(date);
			monthList.add(month);
		}
		
		String dividendPaymentsatus = "";
		StringBuilder sb = new StringBuilder();
		
		int size = monthList.size();
		for(int i=0; i<size; i++) {
			String m = monthList.get(i);
			sb.append(m);
			if(i != size-1) { // 마지막 인덱스가 아닐 경우
				sb.append("월, ");
			} else {
				sb.append(" (총 "+size+"번)");
			}
		}
		
		return dividendPaymentsatus;
			
	} // getDividendPaymentsatus
	
	
	
//	api 데이터에서 올해의 cashDvdnPayDt만 가져와서 리스트에 저장하는 메소드
	public static List<String> getSelectDateForThisYearList(MainView mainView, MainModel mainModel) {
		
		String companyNm = mainView.getStockRetainedPanel().getCompanyName();
		String currentYear = StockRetainedPanel.getYear();
		
		
//		리턴할 리스트 초기화
		List<String> selectDateList = new ArrayList<String>();
		
		StockDividendInfoAPI.getApi(companyNm);
		
//		api에서 가져온 회사의 배당 정보
		DefaultListModel<StockDividendInfoVO> list = mainModel.getStockDividendInfoModel().getStockDividendList();
		
		int size = list.getSize();
		
		for(int i=0; i<size-1; i++) {
			String date = list.get(i).getCashDvdnPayDt().toString(); // api 데이터에 있는 날짜
			String year = date.substring(0, 5);
			System.out.println(year.length());
			System.out.println("date 길이"+date.length());
			if(!date.isEmpty()&&year.equals(currentYear)) { // 현재 년도와 같을 경우에만 가져옴
				selectDateList.add(date);
			}
			System.out.println(selectDateList);

		}
		
		return selectDateList; 
	}
	
	
//	제일 최근 배당 정보 객체를 리턴하는 메소드
//	public static DefaultListModel<StockDividendInfoVO> getRecentDividendInfo(MainView mainView, MainModel mainModel) {
//		
//		String companyNm = mainView.getStockRetainedPanel().getCompanyName();
//		
//		DefaultListModel<StockDividendInfoVO> list = StockDividendInfoAPI.getApi(companyNm);
//		
////		데이트 포맷
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		int size = list.getSize();
//		
//		try {
//			
//			Date curruntDate = sdf.parse(String.valueOf(LocalDate.now()));
//			Date apiDate = sdf.parse();
//			
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} // 현재 년월일
//
//		
//		
//	}
	
	
	// String인 날짜 List 요소들을 Date 타입으로 변환 후 List에 담음
		public static List<Date> getStringToDate(List<String> strList) {

			// 연도월일로 날짜 format
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			
				// date 담을 ArrayList 생성
				List<Date> dateList = new ArrayList<Date>();
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
	
	
} // StockRetainedEvent
