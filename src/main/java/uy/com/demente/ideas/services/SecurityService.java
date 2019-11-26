package uy.com.demente.ideas.services;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uy.com.demente.ideas.exception.InvalidSessionException;
import uy.com.demente.ideas.rest.PersonResource;

/**
 * @author 1987diegog
 */
@Stateless
@Local
public class SecurityService {

	private static Logger log = LoggerFactory.getLogger(PersonResource.class);
	private static String TAG = "[SECURITY_SERVICE] - ";

	/**
	 * @param sessionToken
	 * @throws InvalidSessionException
	 */
	public void validateSessionToken(String sessionToken) throws InvalidSessionException {

		log.info(TAG + "Validando SessionToken: " + sessionToken);

		if (sessionToken != null && sessionToken.equals("INVALID_SESSION_TOKEN")) {
			throw new InvalidSessionException("Invalid SessionToken: " + sessionToken);
		}

		log.info(TAG + "SessionToken valido: " + sessionToken);
	}
}
