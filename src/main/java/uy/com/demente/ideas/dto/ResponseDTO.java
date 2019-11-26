package uy.com.demente.ideas.dto;

import java.io.Serializable;

/**
 * @author 1987diegog
 */
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String menssage;

	public String getMenssage() {
		return menssage;
	}

	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}

}
