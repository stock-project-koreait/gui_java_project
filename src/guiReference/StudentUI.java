package pub2504.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentUI extends JFrame {

	private DefaultTableModel model;

	private JTable table;
	private JButton getBtn;
	private JButton postBtn;
	private JButton putBtn;
	private JButton delBtn;

	public StudentUI(DefaultTableModel model) {

		this.model = model;

		setTitle(":: Student Score ::");
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		table = new JTable(model);

		add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel panel = new JPanel();
		getBtn = new JButton("목록");
		postBtn = new JButton("등록");
		putBtn = new JButton("수정");
		delBtn = new JButton("삭제");
		panel.add(getBtn);
		panel.add(postBtn);
		panel.add(putBtn);
		panel.add(delBtn);

		add(panel, BorderLayout.SOUTH);

		setVisible(true);

	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getGetBtn() {
		return getBtn;
	}

	public void setGetBtn(JButton getBtn) {
		this.getBtn = getBtn;
	}

	public JButton getPostBtn() {
		return postBtn;
	}

	public void setPostBtn(JButton postBtn) {
		this.postBtn = postBtn;
	}

	public JButton getPutBtn() {
		return putBtn;
	}

	public void setPutBtn(JButton putBtn) {
		this.putBtn = putBtn;
	}

	public JButton getDelBtn() {
		return delBtn;
	}

	public void setDelBtn(JButton delBtn) {
		this.delBtn = delBtn;
	}

}
