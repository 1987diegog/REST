package uy.com.demente.ideas.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.com.demente.ideas.dao.SessionDAO;
import uy.com.demente.ideas.model.Session;

/**
 * @author 1987diegog
 */
@Stateless
@Local
public class SessionService {

	private SessionDAO sessionDAO;
	private static final long LIMIT_TIME_SESSION = 5;

	public String addSession(Session session) {
		sessionDAO = SessionDAO.getInstance();
		return sessionDAO.addSession(session);
	}

	public Session getSession(String sessionToken) {
		sessionDAO = SessionDAO.getInstance();
		return sessionDAO.getSession(sessionToken);
	}

	public Session modifySession(Session session) {
		sessionDAO = SessionDAO.getInstance();
		return sessionDAO.modifySession(session);
	}

	public void deleteSession(String sessionToken) {
		sessionDAO = SessionDAO.getInstance();
		sessionDAO.deleteSession(sessionToken);
	}

	public boolean validateSession(String sessionToken) {
		Session session = sessionDAO.getSession(sessionToken);

		LocalDateTime now = LocalDateTime.now();
		long minutes = ChronoUnit.MINUTES.between(session.getTimestamp(), now);

		if (minutes > LIMIT_TIME_SESSION) {
			return false;
		}

		return true;
	}
}
