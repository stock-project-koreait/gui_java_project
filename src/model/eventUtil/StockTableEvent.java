package model.eventUtil;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Main.MainModel;
import Main.MainView;
import model.apiUtil.StockJson;
import model.vo.StockJsonVO;

// 주식 리스트 테이블에 들어가는 이벤트담는 클래스
public class StockTableEvent {
	
	
	public StockTableEvent(MainModel mainModel, MainView mainView) {
		getTableInfoListBtn(mainModel, mainView);
		tableIsLikeChangeClick(mainModel, mainView);
	}
	
	// InfoList 버튼 클릭 이벤트 처리
	public void getTableInfoListBtn(MainModel mainModel, MainView mainView) {
		
		mainView.getStockTablePanel().tableInfoClick(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadSampleData(mainView.getStockTablePanel().getTableModel(),mainModel,mainView);
			}
		});
	}
	
	// 샘플 데이터 로드 메서드 (실제로는 데이터베이스나 API에서 가져올 예정)
	private static void loadSampleData(DefaultTableModel tableModel, MainModel mainModel, MainView mainView) {
		// 기존 데이터 클리어
		tableModel.setRowCount(0);
		StockJson.getJson();
		DefaultListModel<StockJsonVO> getJsonList =mainModel.getStockJsonModel().getStockJsonList();
		
		// 컬럼 한 행씩 추가하기
		for(int i=0; i<getJsonList.size(); i++) {
			Object[] displayRow= {
					getJsonList.get(i).getId(),
					getJsonList.get(i).getName(),
					getJsonList.get(i).getCategory(),
					getJsonList.get(i).isLike() ? "즐찾중입니다.~" : "X"
			};
			tableModel.addRow(displayRow);
		}
	}
	
	// InfoList 버튼 클릭 이벤트 처리
	public void tableIsLikeChangeClick(MainModel mainModel, MainView mainView) {
		// 반복 되는 부분 변수에 담기
		JTable stockTable = mainView.getStockTablePanel().getStockTable();
		
		mainView.getStockTablePanel().tableIsLikeChange(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int row = stockTable.rowAtPoint(e.getPoint());
	            int col = stockTable.columnAtPoint(e.getPoint());
	            // 클릭한 행의 0번째 컬럼 (주식ID) 가져오기
	            Object idValue = stockTable.getValueAt(row, 0);
	            // 불리언으로 바꿔야 잘 작동한다.
	            Object isLikeValue = stockTable.getValueAt(row, 3).equals("즐찾중입니다.~") ? true : false;
	            
	            // 4번째 열(인덱스 3) 클릭 시만 동작
	            if (col == 3) {
	            	if(isLikeValue == "X") {
		                int result = JOptionPane.showConfirmDialog(
			                    mainView.getStockTablePanel(),
			                    "즐겨찾기를 추가하시겠습니까?",
			                    "확인",
			                    JOptionPane.OK_CANCEL_OPTION,
			                    JOptionPane.QUESTION_MESSAGE
			                );
			                if (result == JOptionPane.OK_OPTION) {
			                    // 여기서 실제 즐겨찾기 변경 처리
			                    StockJson.patchJson(idValue.toString(), (Boolean)isLikeValue);
			                    loadSampleData(mainView.getStockTablePanel().getTableModel(),mainModel,mainView);
			                } else {
			                    // 취소 시 아무 동작 X
			                    System.out.println("취소함");
			                }
	            	}else {
		                int result = JOptionPane.showConfirmDialog(
			                    mainView.getStockTablePanel(),
			                    "즐겨찾기를 해제하시겠습니까?",
			                    "확인",
			                    JOptionPane.OK_CANCEL_OPTION,
			                    JOptionPane.QUESTION_MESSAGE
			                );
			                if (result == JOptionPane.OK_OPTION) {
			                    // 여기서 실제 즐겨찾기 변경 처리
			                    StockJson.patchJson(idValue.toString(), (Boolean)isLikeValue);
			                    loadSampleData(mainView.getStockTablePanel().getTableModel(),mainModel,mainView);
			                } else {
			                    // 취소 시 아무 동작 X
			                    System.out.println("취소함");
			                }
	            	}

	            }
	        } // void mouseClicked()
		}); // mainView.getStockTablePanel().tableIsLikeChange()
	} // tableIsLikeChangeClick()
	
	
	
} // class

