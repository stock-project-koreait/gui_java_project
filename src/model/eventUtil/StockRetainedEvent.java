package model.eventUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Main.MainModel;
import Main.MainView;
import model.StockDividendInfoModel;
import model.apiUtil.StockDividendInfoAPI;
import model.vo.StockDividendInfoVO;

// 보유하고 있는 주식에 들어가는 이벤트들을 담는 클래스

public class StockRetainedEvent {

	public void buttonClickEvent(MainModel mainModel, MainView mainView) {
		mainView.getStockRetainedPanel().showDividendInfoWhenClick(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getUIList(mainModel, mainView);
			}
		});
	} // buttonClickEvent

	/*
	 * 리스트에 회사명, 예상 배당금, 2025년 배당금 지급 현황을 리스트에 담아서 view에 전달해야함
	 * 
	 * [기능 별 메소드] 
	 * 1. 가장 최근 데이터 중에 
	 * 2. api 리스트 안에서 가장 최근 배당 데이터 리턴 
	 * 3. 최근 지급한 1주당 배당금 리턴 
	 * 4. 보유 주식 수 * 1주당 배당금 곱해서 다음 배당 지급일에 받을 배당금 리턴
	 */
	
	// 사용자가 입력한 회사의
	// 제일 최근 배당 정보 리턴하는 메소드
//	   int numberOfretainedStock
	
	List<String> UIList = new ArrayList();

//	사용자 입력 값을 검증하고 UI에 표시할 리스트를 리턴
	public List<String> getUIList(MainModel mainModel, MainView mainView) {
		
		UIList.clear(); // List 데이터 초기화
		
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
			UIList = makeList(mainModel, mainView);
		}
		return UIList;
	} // getUIList
	
	public List<String> makeList(MainModel mainModel, MainView mainView) {
		String companyNm = mainView.getStockRetainedPanel().getCompanyName();
		String retainedStock = mainView.getStockRetainedPanel().getNumberOfHoldings();
		int numberOfretainedStock = Integer.parseInt(retainedStock);
		
		DefaultListModel<StockDividendInfoVO> list = StockDividendInfoAPI.getApi(companyNm);
		
		int size = list.getSize();
		for(int i=size-1; i>0; i--) {
			if(list.get(i).getCashDvdnPayDt() != null) {
				mainView.getStockRetainedPanel()
				.getColumnName()
				.addRow(list.get(i).getIsinCdNm(), getExpectedDividend(list.get(i), numberOfretainedStock), dividendPaymentsStatusForThisYear());
			}
		}
		
	} // makeList
	
//	예상 배당금 계산 후 String으로 바꾸고 리턴해주는 메소드
	public String getExpectedDividend(StockDividendInfoVO obj, int numberOfretainedStock) {
		return Integer.toString(Integer.parseInt(obj.getStckGenrDvdnAmt()) * numberOfretainedStock) + " 원";
	} // getExpectedDividend
	
//	올해 기준 배당 지급 현황 (
	
	

	
} // StockRetainedEvent
