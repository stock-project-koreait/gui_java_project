package Main;

import controller.MainController;
import model.MainModel;
import model.StockDividendInfoModel;
import view.MainView;

public class TestMain {
	public static void main(String[] args) {
		MainView mainView = new MainView();
		MainModel mainModel = new MainModel();
		MainController mainController = new MainController(mainView, mainModel);
		
		//System.out.println(mainModel.getStockJsonModel().getJson());
		
		//System.out.println(mainModel.getStockDividendInfoModel().getApi("삼성전자"));
		
		
	} // main
}
