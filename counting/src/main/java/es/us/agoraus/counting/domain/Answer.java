package es.us.agoraus.counting.domain;

import com.google.gson.annotations.SerializedName;

//Comentario prueba jenkins II
public class Answer {

	private String question;
	@SerializedName("answer_question")
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnwser() {
		return answer;
	}

	public void setAnswer(String anwser) {
		this.answer = anwser;
	}

	@Override
	public String toString() {
		return "Answer [question=" + question + ", answer_question="
				+ answer + "]";
	}

}

