package view;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class StockTablePanel extends JPanel {

	private JTable stockTable;
	private DefaultTableModel tableModel;
	private JButton loadDataButton;

	public StockTablePanel() {

		// 생성자 안에 메소드 실행
		initializeComponents();
		setupLayout();
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	public JTable getStockTable() {
		return stockTable;
	}
	
	private void initializeComponents() {
		// 테이블 컬럼 정의
		String[] columnNames = { "ID.NO", "기업명", "업종", "즐겨찾기" };

		// 테이블 모델 생성 (편집 불가능하게 설정)
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// 모든 컬럼 편집 불가능
				return false;
			}
		};

		// JTable 생성
		stockTable = new JTable(tableModel);
		stockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stockTable.setRowHeight(30);

		// 컬럼 너비 설정
		TableColumnModel columnModel = stockTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40); // ID.NO
		columnModel.getColumn(1).setPreferredWidth(180); // 기업명
		columnModel.getColumn(2).setPreferredWidth(210); // 업종
		columnModel.getColumn(3).setPreferredWidth(80); // 즐겨찾기

        // 컬럼들 중앙 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<4; i++) {
        	columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }

		// 데이터 로드 버튼
		loadDataButton = new JButton("주식 불러오기");
		loadDataButton.setPreferredSize(new Dimension(120, 30));
	}
	
	// 레이아웃 설정
	private void setupLayout() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("리스트"));

		// 테이블을 스크롤 패널에 추가
		JScrollPane scrollPane = new JScrollPane(stockTable);
		scrollPane.setPreferredSize(new Dimension(500, 300));

		// 버튼 패널
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(loadDataButton);

		// 레이아웃 배치
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	// 정보 불러오키 click 메소드
	public void tableInfoClick(ActionListener actionListener) {
		loadDataButton.addActionListener(actionListener);
	}

	// 테이블 즐겨찾기 정보 변경 메소드
	public void tableIsLikeChange(MouseAdapter mouseAdapter) {
		stockTable.addMouseListener(mouseAdapter);
	}
	
}








