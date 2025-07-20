package model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.DefaultListModel;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vo.StockDividendInfoVO;
import vo.StockJsonVO;

public class StockJsonModel {

	
	private static DefaultListModel<StockJsonVO> stockJsonList = new DefaultListModel<StockJsonVO>();
	
	// 통신 객체 (HTTP Client : HTTP 요청을 보내고 응답받는 객체)
	private static final OkHttpClient client = new OkHttpClient();
	// Gson 객체
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public StockJsonModel() {
	}
	
	public static DefaultListModel<StockJsonVO> getStockJsonList() {
		return stockJsonList;
	}

	public static DefaultListModel<StockJsonVO> getJson(){
		// request 요청 객체 만들기
		Request request = new Request.Builder().url("http://localhost:3000/stock").build();
		
		try {
			
			Response response = client.newCall(request).execute();
			String json = Objects.requireNonNull(response.body()).string();
			List<Map<String, Object>> data
			= gson.fromJson(json, new TypeToken<List<Map<String, Object>>>(){}.getType());
			
			for (Map<String, Object> item : data) {
				int id = Integer.parseInt(item.get("id").toString());
			    String name = (String) item.get("기업명");
			    String category = (String) item.get("업종");
			    String stockNum = (String) item.get("상장주식수(주)");
			    boolean isLike = Boolean.parseBoolean(item.get("isLike").toString());
			    int count = ((Number)data.get(0).get("count")).intValue();

			    StockJsonVO vo = new StockJsonVO(id, name, category, stockNum, isLike, count);
			    stockJsonList.addElement(vo);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return stockJsonList;
	}
	
	public static void getJsonTest() {
		// request 요청 객체 만들기
		Request request = new Request.Builder().url("http://localhost:3000/stock").build();
		try {
			Response response = client.newCall(request).execute();
			String json = Objects.requireNonNull(response.body()).string();
			List<Map<String, Object>> data
				= gson.fromJson(json, new TypeToken<List<Map<String, Object>>>(){}.getType());
			
			System.out.println(((Number)data.get(0).get("count")).intValue());
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}















