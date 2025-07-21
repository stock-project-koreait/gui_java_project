package Main;

import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;
import model.StockDividendInfoModel;
import model.StockInfoModel;
import model.StockJsonModel;

public class Main {
	public static void main(String[] args) {
		MainView mainView = new MainView();
		MainModel mainModel = new MainModel(new StockInfoModel(), new StockDividendInfoModel(), new StockJsonModel());
		MainController mainController = new MainController(mainView, mainModel);
		
	} // main
} // class 











