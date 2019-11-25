package uy.com.demente.ideas.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 1987diegog
 */
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String session;
	private String userName;
	private LocalDateTime timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
