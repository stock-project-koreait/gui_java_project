package model.apiUtil.constant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;

public class ApiConstant {

	private static Dotenv dotenv = Dotenv.load();
	private static String apiKey = dotenv.get("STOCK_KEY");
	
	// 통신 객체 (HTTP Client : HTTP 요청을 보내고 응답받는 객체)
	public static final OkHttpClient client = new OkHttpClient();
	// Gson 객체
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static final String STOCK_API = "https://apis.data.go.kr/1160100/service/GetStocDiviInfoService/getDiviInfo?serviceKey="
			+ apiKey + "&pageNo=1&numOfRows=100&resultType=json&isinCd=";
	
	public static final String STOCKINFO_API = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey="
			+ apiKey + "&numOfRows=1&pageNo=1&resultType=json&itmsNm=";

	
}







