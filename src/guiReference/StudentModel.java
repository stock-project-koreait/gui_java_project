package pub2504.gui;

import javax.swing.table.DefaultTableModel;

public class StudentModel {
	
	private DefaultTableModel model;

	public StudentModel() {
		initModel();
	}
	
	public void initModel() {
		model = new DefaultTableModel(
				new String[] {"학번", "학생명", "국어", "영어", "수학", "총점", "평균"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column !=0 && column !=5 && column !=6;
			}
		};
	}
	
	public DefaultTableModel getModel() {
		return this.model;
	}
	
}
