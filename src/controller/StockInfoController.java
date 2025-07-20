package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.MainModel;
import model.StockDividendInfoModel;
import model.StockInfoModel;
import view.InfoPanelMainGrid;
import view.MainView;
import vo.StockDividendInfoVO;
import vo.StockInfoVO;

public class StockInfoController {
	// gui에서 주식 정보에 들어갈 이벤트를 담는 컨트롤러

	MainModel mainModel;
	MainView mainView;
	
	public StockInfoController(MainModel mainModel, MainView mainView) {
		this.mainModel = mainModel;
		this.mainView = mainView;
		
		getInfoList();
	}

	public void getInfoList() {
		mainView.getStockInfoPanel().infoClick(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModel.getStockJsonModel().getJson();
				
				int modelNum = mainModel.getStockJsonModel().getStockJsonList().size();
				
		        for (int i = 0; i <= modelNum-1; i++) {
		            JButton menuBtn = new JButton(mainModel.getStockJsonModel().getStockJsonList().get(i).getName());
		            menuBtn.setBackground(Color.LIGHT_GRAY);
		            menuBtn.setHorizontalAlignment(SwingConstants.LEFT);
		            mainView.getStockInfoPanel().getLeftMenuPanel().add(menuBtn);
		            
		            // 아래에서 따로 정의한 메소드 붙히기
		            menuBtn.addActionListener(btnAction(mainModel, mainView, menuBtn));
		        }
		        // 정보 버튼 1번 클릭하면 비활성화 시키기
		        mainView.getStockInfoPanel().getInvestInfoBtn().setEnabled(false);
			}
		});
	} // getInfoList()
	
	// 리스트 버튼 메소드 파라미터에 메인모델, 메인뷰를 넣은 이유는 메인모델, 뷰를 static으로 만들지 않으려고
	private static ActionListener btnAction(MainModel mainModel, MainView mainView, JButton menuBtn) {
		ActionListener btnAction =new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 시간 가져오기
				LocalDateTime now = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedNow = now.format(formatter);
		        // 클릭한 텍스트
				String stockText = menuBtn.getText();
				
				if(mainModel.getStockInfoModel().getStockInfoList() != null) {
					mainModel.getStockInfoModel().getStockInfoList().clear();
					//mainModel.getStockInfoModel().getStockInfoList().removeAllElements();
				} // 모델이 있으면 삭제 후 아래 코드 데이터 저장 
				
				mainModel.getStockInfoModel().getStockInfo(stockText);// 주식 정보 모델에 데이터 저장
				mainModel.getStockDividendInfoModel().getApi(stockText); // 배당률 모델에 데이터 저장
				
				int listSize = mainModel.getStockDividendInfoModel().getStockDividendList().size();
				
				// 중복 부분 변수에 담기
				InfoPanelMainGrid IPMG = mainView.getStockInfoPanel().getInfoPanelMainGrid();
				StockInfoVO SIVO_first = mainModel.getStockInfoModel().getStockInfoList().get(0);
				StockDividendInfoVO SDIVO_last
				= mainModel.getStockDividendInfoModel().getStockDividendList().get(listSize-1);
				
				
				mainView.getStockInfoPanel().getStockName().setText(stockText);
				IPMG.getLblSysdate().setText(formattedNow);
				IPMG.getLblOpen().setText(SIVO_first.getMkp());
				IPMG.getLblClose().setText(SIVO_first.getClpr());
				IPMG.getLblHigh().setText(SIVO_first.getHipr());
				IPMG.getLblLow().setText(SIVO_first.getLopr());
				IPMG.getLblDividendDate().setText(SDIVO_last.getDvdnBasDt());
				IPMG.getLblCashDate().setText(SDIVO_last.getCashDvdnPayDt());
				IPMG.getLblDvdnRcd().setText(SDIVO_last.getStckDvdnRcdNm());
				IPMG.getLblDvdnAmt().setText(SDIVO_last.getStckGenrDvdnAmt());
				IPMG.getLblCashDvdnRt().setText(SDIVO_last.getStckGenrCashDvdnRt());
			}
		};
		return btnAction;
	}
	
} //class








