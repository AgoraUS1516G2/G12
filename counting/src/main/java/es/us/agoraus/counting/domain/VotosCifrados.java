package es.us.agoraus.counting.domain;

import java.util.List;

public class VotosCifrados {

	private Integer msg;
	private List<byte[]> votes;

	public Integer getMsg() {
		return this.msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	public List<byte[]> getVotes() {
		return votes;
	}

	public void setVotes(List<byte[]> votes) {
		this.votes = votes;
	}

}
