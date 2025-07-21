package model.eventUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import Main.MainModel;
import Main.MainView;

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
	 * 1.모델리스트 중에 최근 날짜 데이터 반환 2.
	 */

	/*
	 * 리스트에 회사명, 예상 배당금, 2025년 배당금 지급 현황을 리스트에 담아서 view에 전달해야함
	 * 
	 * [기능 별 메소드] 1. 가장 최근 배당금 지급 날짜 리턴 2. api 리스트 안에서 가장 최근 배당 데이터 리턴 3. 최근 지급한 1주당
	 * 배당금 리턴 4. 보유 주식 수 * 1주당 배당금 곱해서 다음 배당 지급일에 받을 배당금 리턴
	 */

	// 사용자가 입력한 회사의
	// 제일 최근 배당 정보 리턴하는 메소드
//	, int numberOfretainedStock

//	사용자 입력 값을 검증하고 리스트를 리턴
	public List<String> getUIList(MainModel mainModel, MainView mainView) {

		String companyNm = mainView.getStockRetainedPanel().getCompanyName();
		String retainedStock = mainView.getStockRetainedPanel().getNumberOfHoldings();

		if (companyNm.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "주식명을 입력해 주세요.", "information", JOptionPane.INFORMATION_MESSAGE);
		} else if (retainedStock.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "보유 주식 수를 입력해 주세요.", "information",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (companyNm.isEmpty() && retainedStock.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "주식명과 보유 주식 수를 입력해 주세요.", "information",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

		}
		return null;
	} // getUIList

}
