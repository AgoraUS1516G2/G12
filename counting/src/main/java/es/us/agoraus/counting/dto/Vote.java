package es.us.agoraus.counting.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Vote {

	private String age;
	private List<Answer> answers;
	@SerializedName("autonomous_community")
	private String autonomousCommunity;
	private String genre;
	private String id;
	@SerializedName("id_poll")
	private String pollId;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getAutonomousCommunity() {
		return autonomousCommunity;
	}

	public void setAutonomousCommunity(String autonomousCommunity) {
		this.autonomousCommunity = autonomousCommunity;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	@Override
	public String toString() {
		return "Voto [age=" + age + ", answers=" + answers + ", autonomous_community=" + autonomousCommunity
				+ ", genre=" + genre + ", id=" + id + ", id_poll=" + pollId + "]";
	}

}