package uy.com.demente.ideas.dao;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import uy.com.demente.ideas.model.Session;

/**
 * @author 1987diegog
 */
public class SessionDAO extends CacheHelper<String, Session> {

	private static SessionDAO sessionDAO;

	public static final int CONFIG_HEAP = 100;
	public static final String NAME_CACHE_SESSION = "dementeCacheSessions";
	public static final String KEY_SESSION = "kEy1987_DG";

	private SessionDAO() {

		super(String.class, Session.class, NAME_CACHE_SESSION);
		initCacheHelper(CONFIG_HEAP);
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static SessionDAO getInstance() {

		if (sessionDAO == null) {
			sessionDAO = new SessionDAO();
		}

		return sessionDAO;
	}

	private static String getKey(String userName) {

		String userWithoutSpecialCharacters = userName.replaceAll("[^a-zA-Z0-9]+", "");
		return userWithoutSpecialCharacters + RandomStringUtils.randomAlphanumeric(12);
	}

	public String addSession(Session session) {

		String sessionToken = getKey(session.getUserName());
		this.add(sessionToken, session);
		return sessionToken;
	}

	public Session getSession(String sessionToken) {
		return get(sessionToken);
	}

	public List<Session> getSessions() {
		return getAll();
	}

	public Session modifySession(Session session) {
		return replace(session.getSession(), session);
	}

	public void deleteSession(String sessionToken) {
		delete(sessionToken);
	}
}
