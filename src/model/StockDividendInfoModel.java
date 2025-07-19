package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vo.StockDividendInfoVO;

// 주식 배당금 정보 api를 담는 모델
public class StockDividendInfoModel {
	
	static Dotenv dotenv = Dotenv.load();
	static String apiKey = dotenv.get("STOCK_KEY");

	private static final String STOCK_API = "https://apis.data.go.kr/1160100/service/GetStocDiviInfoService/getDiviInfo?serviceKey=" + apiKey + "&pageNo=1&numOfRows=100&resultType=json&isinCd=";

	private static DefaultListModel<StockDividendInfoVO> stockDividendList = new DefaultListModel<StockDividendInfoVO>();
	
	// 통신 객체 (HTTP Client : HTTP 요청을 보내고 응답받는 객체)
	private static final OkHttpClient client = new OkHttpClient();
	// Gson 객체
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public StockDividendInfoModel() {
	}
	
	// 주식 배당금 api에서 회사이름, 배당기준일, 현급지급일, 배당타입, 한 주당 배당금, 현금 배당률를 listModel를 반환하는 메서드
	public static DefaultListModel<StockDividendInfoVO> getApi(String itmsNm) {

		String isinCd = StockInfoModel.getIsinCd(itmsNm);

		if (isinCd == null || isinCd.isEmpty()) {
			System.out.println("유효하지 않은 종목명입니다. " + itmsNm);
			return null;
		}

		// request 요청 객체 만들기
		Request request = new Request.Builder().url(STOCK_API + isinCd).build();

		try {
			// client response
			Response response = client.newCall(request).execute();
			// 응답 요청 json에 저장
			String json = Objects.requireNonNull(response.body()).string();
//			System.out.println(request.url());
			// 아래에서 getAsJson으로 item 추출하기 위해 json 객체로 변환
			JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
			// item 키를 jsonarray로 변경
			JsonArray itemArray = jsonObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonObject("items")
					.getAsJsonArray("item");

			for (JsonElement ele : itemArray) {
				JsonObject obj = ele.getAsJsonObject();
				
				StockDividendInfoVO stockDividendInfoVO = new StockDividendInfoVO(
						obj.getAsJsonObject().get("isinCdNm").getAsString(), // 회사이름
						obj.getAsJsonObject().get("dvdnBasDt").getAsString(), // 배당기준일
						obj.getAsJsonObject().get("cashDvdnPayDt").getAsString(), // 현급지급일
						obj.getAsJsonObject().get("stckDvdnRcdNm").getAsString(), // 배당 타입
						obj.getAsJsonObject().get("stckGenrDvdnAmt").getAsString(), // 한 주당 배당금
						obj.getAsJsonObject().get("stckGenrCashDvdnRt").getAsString() // 현금배당률
				);
				
				stockDividendList.addElement(stockDividendInfoVO);
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return stockDividendList;
	} // getApi
	

}
