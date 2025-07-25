package model.eventUtil;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Main.MainModel;
import Main.MainView;
import model.apiUtil.StockDividendInfoAPI;
import model.apiUtil.StockInfoAPI;
import model.apiUtil.StockJson;
import model.vo.StockDividendInfoVO;
import model.vo.StockInfoVO;
import model.vo.StockJsonVO;
import view.StockInfoPanelMainGrid;

// 주식 정보에 들어가는 이벤트들을 담는 클래스
public class StockInfoEvent {
	
	public void getInfoList(MainModel mainModel, MainView mainView) {
		mainView.getStockInfoPanel().infoClick(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(mainModel.getStockJsonModel().getStockJsonList() != null) {
					mainModel.getStockJsonModel().getStockJsonList().removeAllElements();
				}
				
				StockJson.getJson();
				
				DefaultListModel<StockJsonVO> stockJsonList = mainModel.getStockJsonModel().getStockJsonList();
				
				int modelNum = stockJsonList.size();
				
		        for (int i = 0; i <= modelNum-1; i++) {
		            JButton menuBtn = new JButton(stockJsonList.get(i).getName());
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
				
				DefaultListModel<StockInfoVO> stockInfoList = mainModel.getStockInfoModel().getStockInfoList();
				
				if(stockInfoList != null) {
					stockInfoList.clear();
					//mainModel.getStockInfoModel().getStockInfoList().removeAllElements();
				} // 모델이 있으면 삭제 후 아래 코드 데이터 저장 
				
				// 주식 정보 모델에 데이터 저장하기 위해 메소드 호출 stockInfoList 에 데이터 저장
				StockInfoAPI.getStockInfo(stockText);
				// 배당률 모델에 데이터 저장하기 위해 메소드 호출 getStockDividendList 에 데이터 저장
				StockDividendInfoAPI.getApi(stockText);
				
				DefaultListModel<StockDividendInfoVO> stockDividendList =
							mainModel.getStockDividendInfoModel().getStockDividendList();
				
				int listSize = stockDividendList.size();
				
				// 중복 부분 변수에 담기
				StockInfoPanelMainGrid IPMG = mainView.getStockInfoPanel().getInfoPanelMainGrid();
				StockInfoVO SIVO_first = stockInfoList.get(0);
				StockDividendInfoVO SDIVO_last
				= stockDividendList.get(listSize-1);
				
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

}
