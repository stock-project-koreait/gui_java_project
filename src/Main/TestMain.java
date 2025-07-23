package Main;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.DefaultListModel;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.StockDividendInfoModel;
import model.StockInfoModel;
import model.StockJsonModel;
import model.apiUtil.StockDividendInfoAPI;
import model.vo.StockDividendInfoVO;
import model.vo.StockJsonVO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestMain {

	public static void main(String[] args) {
		//MainView mainView = new MainView();
		MainModel mainModel = new MainModel(new StockInfoModel(), new StockDividendInfoModel(), new StockJsonModel());
		//MainController mainController = new MainController(mainView, mainModel);
	
		StockDividendInfoAPI.getApi("BNK금융지주");
		DefaultListModel<StockDividendInfoVO> stockDividendList = StockDividendInfoModel.getStockDividendList();
		int listSize = stockDividendList.size();
		int count = 0;
		
		for(int i=0; i<listSize; i++) {
			if(stockDividendList.get(i).getStckDvdnRcdNm().equals("무배당")) {
				count++;
			}
		}
		System.out.println(count);
		//System.out.println(stockDividendList.get(0).getStckDvdnRcdNm().equals("현금배당"));
		
	} // main
}// class













