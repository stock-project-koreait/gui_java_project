package Main;

import java.util.Map;

import controller.MainController;
import io.github.cdimascio.dotenv.Dotenv;
import model.MainModel;
import model.StockDividendInfoModel;
import model.StockInfoModel;
import view.MainView;

public class Main {
	public static void main(String[] args) {
		MainView mainView = new MainView();
		mainView.initView();
		
		MainModel mainModel = new MainModel();
		MainController mainController = new MainController(mainView, mainModel);
		
		
		//System.out.println(StockInfoModel.getStockInfo("삼성전자"));
		
	} // main
} // class 











