package pub2504.gui;

import java.io.Serializable;

public class Score implements Serializable {

	private static final long serialVersionUID = 93284793247932L;

	private int kor;
	private int math;
	private int eng;

	public Score() {
	}

	public Score(int kor, int math, int eng) {
		this.kor = kor;
		this.math = math;
		this.eng = eng;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	@Override
	public String toString() {
		return "Score [kor=" + kor + ", math=" + math + ", eng=" + eng + "]";
	}

}
