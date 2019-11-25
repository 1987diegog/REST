package uy.com.demente.ideas.rest;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import uy.com.demente.ideas.dto.SessionDTO;
import uy.com.demente.ideas.factory.BOFactory;
import uy.com.demente.ideas.factory.DTOFactory;
import uy.com.demente.ideas.model.Session;
import uy.com.demente.ideas.services.SessionService;

/**
 * @author 1987diegog
 */
@Path("sessions")
public class SessionResource {

	@EJB
	private SessionService sessionServices;

	/**
	 */
	@POST
	@Produces("application/json")
	public Response addSession(SessionDTO sessionDTO) {

		Response.ResponseBuilder builder = null;

		Session session = BOFactory.getSession(sessionDTO);
		String sessionToken = sessionServices.addSession(session);

		System.out.println("Added session");
		Session sessionAdded = sessionServices.getSession(sessionToken);

		Gson gson = new Gson();
		SessionDTO sessionAddedDTO = DTOFactory.getSessionDTO(sessionAdded);
		String json = gson.toJson(sessionAddedDTO);

		// Creo una respuesta con codigo "OK".
		builder = Response.status(Response.Status.OK).entity(json);
		return builder.build();
	}

	/**
	 * @return the session with equal id param
	 */
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getSession(@PathParam("id") String sessionToken) {

		System.out.println("ID session: " + sessionToken);
		Session session = sessionServices.getSession(sessionToken);

		Gson gson = new Gson();
		SessionDTO sessionDTO = DTOFactory.getSessionDTO(session);
		String json = gson.toJson(sessionDTO);

		return json;
	}

	/**
	 */
	@PUT
	@Produces("application/json")
	public String modifySessionPUT(SessionDTO sessionDTO) {

		Session session = BOFactory.getSession(sessionDTO);
		sessionServices.modifySession(session);
		System.out.println("Session modify");
		return "OK";
	}

	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public String deleteSession(@PathParam("sessionToken") String sessionToken) {

		System.out.println("ID session: " + sessionToken);
		sessionServices.deleteSession(sessionToken);

		return "OK";
	}
}
