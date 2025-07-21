package Main;

import model.StockDividendInfoModel;
import model.StockInfoModel;
import model.StockJsonModel;

public class MainModel {

	private StockInfoModel stockInfoModel; // 주식 시세 정보를 담고 있는 모델
	private StockDividendInfoModel stockDividendInfoModel; // 주식 배당금 정보를 담고 있는 모델
	private StockJsonModel stockJsonModel; // json서버를 통해 주식회사에 대한 정보를 담고 있는 모델

	public StockInfoModel getStockInfoModel() {
		return stockInfoModel;
	}

	public StockDividendInfoModel getStockDividendInfoModel() {
		return stockDividendInfoModel;
	}

	public StockJsonModel getStockJsonModel() {
		return stockJsonModel;
	}

	public MainModel(StockInfoModel stockInfoModel, StockDividendInfoModel stockDividendInfoModel, StockJsonModel stockJsonModel) {
		this.stockInfoModel = stockInfoModel;
		this.stockDividendInfoModel = stockDividendInfoModel;
		this.stockJsonModel = stockJsonModel;
	}
}
