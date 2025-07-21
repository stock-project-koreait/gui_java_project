package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.MainModel;
import model.StockDividendInfoModel;
import view.MainView;
import vo.StockDividendInfoVO;

public class StockRetainedController {
	// gui에서 보유하고 있는 주식 이벤트에 들어갈 컨트롤러

	MainModel mainModel;
	MainView mainView;

	public StockRetainedController() {
	}

	public StockRetainedController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
	
		buttonClickEvent();
	}
	
	public void buttonClickEvent() {
		mainView.getStockRetainedPanel().showDividendInfoWhenClick(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getUIList();
			}
		});
	} // buttonClickEvent
	
	/*
	 1.모델리스트 중에 최근 날짜 데이터 반환
	 2.
	 */
	
//	사용자 입력 값을 검증하고 리스트를 리턴
	public List<String> getUIList() {
		
		String companyNm = mainView.getStockRetainedPanel().getCompanyName();
		String retainedStock = mainView.getStockRetainedPanel().getNumberOfHoldings();
		
		if (companyNm.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "주식명을 입력해 주세요.", "information", JOptionPane.INFORMATION_MESSAGE);
		} else if (retainedStock.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "보유 주식 수를 입력해 주세요.", "information", JOptionPane.INFORMATION_MESSAGE);
		} else if (companyNm.isEmpty() && retainedStock.isEmpty()) {
			JOptionPane.showMessageDialog(mainView, "주식명과 보유 주식 수를 입력해 주세요.", "information", JOptionPane.INFORMATION_MESSAGE);
		}else {
			
		}
		return null;
	} // getUIList
	 
	
	
	
	
	
	
}
