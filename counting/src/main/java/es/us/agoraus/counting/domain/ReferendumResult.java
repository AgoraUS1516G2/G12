package es.us.agoraus.counting.domain;

public class ReferendumResult implements Result {
	String pregunta;
	Integer numeroSi;
	Integer numeroNo;
	public ReferendumResult(String pregunta, Integer numeroSi, Integer numeroNo) {
		super();
		this.pregunta = pregunta;
		this.numeroSi = numeroSi;
		this.numeroNo = numeroNo;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Integer getNumeroSi() {
		return numeroSi;
	}
	public void setNumeroSi(Integer numeroSi) {
		this.numeroSi = numeroSi;
	}
	public Integer getNumeroNo() {
		return numeroNo;
	}
	public void setNumeroNo(Integer numeroNo) {
		this.numeroNo = numeroNo;
	}
	
	
}
