package vo;

// json 서버에서 가져온 주식 객체
public class StockJsonVO {

	private int id; // 아이디
	private String name; // 주식종목명
	private boolean isLike; // 즐겨찾기여부
	private int count; // 가지고 있는 주 수
	private String category; // 회사 업종

	public StockJsonVO() {
	}

	public StockJsonVO(int id, String name, boolean isLike, int count, String category) {
		this.id = id;
		this.name = name;
		this.isLike = isLike;
		this.count = count;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", isLike=" + isLike + ", count=" + count + "]";
	}
}
