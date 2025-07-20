package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.MainModel;
import model.StockInfoModel;
import view.MainView;

public class StockInfoController {
	// gui에서 주식 정보에 들어갈 이벤트를 담는 컨트롤러

	MainModel mainModel;
	MainView mainView;
	
	public StockInfoController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		
		getInfo();
		getTestStockInfo();
		getDividends();
	}

	public void getInfo() {
		mainView.getStockInfoPanel().infoClick(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
		        for (int i = 1; i <= 40; i++) {
		            JButton menuBtn = new JButton("메뉴 아이템 " + i);
		            menuBtn.setBackground(Color.LIGHT_GRAY);
		            menuBtn.setHorizontalAlignment(SwingConstants.LEFT);
		            mainView.getStockInfoPanel().getLeftMenuPanel().add(menuBtn);
		        }
			}
		});
	}
	
	public void getTestStockInfo() {
		mainView.getStockInfoPanel().infoClick(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("getStockInfo test 클릭 메세지 입니다.");
				
//				System.out.println(
//						"삼성전자" + 
//						"\n시가총액 : "+mainModel.getStockInfoModel().getStockInfo("삼성전자").get(0).getMrktTotAmt() +
//						"\n시가 : " +mainModel.getStockInfoModel().getStockInfo("삼성전자").get(0).getMkp() +
//						"\n종가 : " +mainModel.getStockInfoModel().getStockInfo("삼성전자").get(0).getClpr() +
//						"\n고가 : " +mainModel.getStockInfoModel().getStockInfo("삼성전자").get(0).getHipr() +
//						"\n저가 : " +mainModel.getStockInfoModel().getStockInfo("삼성전자").get(0).getLopr()
//						);
			}
		});
	} // getStockInfo()
	
	public void getDividends() {
		
		mainView.getStockInfoPanel().diviClick(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int lastNum = mainModel.getStockDividendInfoModel().getApi("삼성전자").size();
				
				System.out.println("getDividends test 클릭 메세지 입니다.");
				System.out.println(
						"삼성전자" +
						"\n최근 배당기준일 : " + mainModel.getStockDividendInfoModel().getApi("삼성전자").get(lastNum-2).getDvdnBasDt()+
						"\n최근 현급지급일 : " + mainModel.getStockDividendInfoModel().getApi("삼성전자").get(lastNum-2).getCashDvdnPayDt()+
						"\n배당 타입 : " + mainModel.getStockDividendInfoModel().getApi("삼성전자").get(lastNum-2).getStckDvdnRcdNm()+
						"\n한 주당 배당금 : " + mainModel.getStockDividendInfoModel().getApi("삼성전자").get(lastNum-2).getStckGenrDvdnAmt()+
						"\n현금배당률 : " + mainModel.getStockDividendInfoModel().getApi("삼성전자").get(lastNum-2).getStckGenrCashDvdnRt()
						);
			}
		});
	}// getDividends()
	
} //class








