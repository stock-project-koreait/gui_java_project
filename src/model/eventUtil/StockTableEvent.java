package model.eventUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Main.MainModel;
import Main.MainView;

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
				loadSampleData(mainView.getStockTablePanel().getTableModel());
			}
		});
	}
	
	// 샘플 데이터 로드 메서드 (실제로는 데이터베이스나 API에서 가져올 예정)
	private static void loadSampleData(DefaultTableModel tableModel) {
		// 기존 데이터 클리어
		tableModel.setRowCount(0);
		// 샘플 데이터 추가
		Object[][] sampleData = { { "001", "삼성전자", "전자/IT", false }, { "002", "SK하이닉스", "반도체", true },
				{ "003", "LG화학", "화학", false }, { "004", "현대자동차", "자동차", true }, { "005", "네이버", "인터넷/게임", false },
				{ "006", "카카오", "인터넷/게임", true }, { "007", "포스코홀딩스", "철강", false }, { "008", "KB금융", "금융", false },
				{ "009", "신한지주", "금융", true }, { "010", "LG전자", "전자/IT", false } };

	       for (Object[] row : sampleData) {
	            // Boolean 값을 O/X 문자열로 변환하여 테이블에 추가
	            Object[] displayRow = {row[0], row[1], row[2], (Boolean) row[3] ? "즐겨찾기중~" : "X"};
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
	            
	            // 4번째 열(인덱스 3) 클릭 시만 동작
	            if (col == 3) { 
	                int result = JOptionPane.showConfirmDialog(
	                    stockTable,
	                    "즐겨찾기를 변경하시겠습니까?",
	                    "확인",
	                    JOptionPane.OK_CANCEL_OPTION,
	                    JOptionPane.QUESTION_MESSAGE
	                );
	                if (result == JOptionPane.OK_OPTION) {
	                    // 여기서 실제 즐겨찾기 변경 처리(예시: 값 반전)
	                    System.out.println("확인 눌림, 실제 처리 실행");
	                    // 예시: 체크박스 값 변경 등 원하는 로직 작성
	                } else {
	                    // 취소 시 아무 동작 X
	                    System.out.println("취소함");
	                }
	            }
	        }
		});
	}
} // class

