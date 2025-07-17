package view;

import javax.swing.JPanel;
import javax.swing.JButton;

public class StockTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public StockTablePanel() {

		btnNewButton = new JButton("테이블 종목 리스트");
		add(btnNewButton);

	}

}
