package model.apiUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.DefaultListModel;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.StockJsonModel;
import model.vo.StockJsonVO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 주식 제목, 시가, 종가 등을 호출하는 json
public class StockJson {

	// 통신 객체 (HTTP Client : HTTP 요청을 보내고 응답받는 객체)
	private static final OkHttpClient client = new OkHttpClient();
	// Gson 객체
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static DefaultListModel<StockJsonVO> getJson() {
		// request 요청 객체 만들기
		Request request = new Request.Builder().url("http://localhost:3000/stock").build();

		try {

			Response response = client.newCall(request).execute();
			String json = Objects.requireNonNull(response.body()).string();
			List<Map<String, Object>> data = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
			}.getType());

			for (Map<String, Object> item : data) {
				int id = Integer.parseInt(item.get("id").toString());
				String name = (String) item.get("기업명");
				String category = (String) item.get("업종");
				String stockNum = (String) item.get("상장주식수(주)");
				boolean isLike = Boolean.parseBoolean(item.get("isLike").toString());
				int count = ((Number) data.get(0).get("count")).intValue();

				StockJsonVO vo = new StockJsonVO(id, name, category, stockNum, isLike, count);
				StockJsonModel.getStockJsonList().addElement(vo);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return StockJsonModel.getStockJsonList();
	} // getJson

	public static void getJsonTest() {
		// request 요청 객체 만들기
		Request request = new Request.Builder().url("http://localhost:3000/stock").build();
		try {
			Response response = client.newCall(request).execute();
			String json = Objects.requireNonNull(response.body()).string();
			List<Map<String, Object>> data = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
			}.getType());

			System.out.println(((Number) data.get(0).get("count")).intValue());

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	} // getJsonTest

}
