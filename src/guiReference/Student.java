package pub2504.gui;

import java.io.Serializable;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 328947934923423L;
	
	private String id;
	private String name;
	private Score score;
	
	public Student() {
	}

	public Student(String id, String name, Score score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
}
