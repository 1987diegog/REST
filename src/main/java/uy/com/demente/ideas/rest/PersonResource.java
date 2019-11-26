package uy.com.demente.ideas.rest;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.dto.ResponseDTO;
import uy.com.demente.ideas.exception.InvalidSessionException;
import uy.com.demente.ideas.factory.BOFactory;
import uy.com.demente.ideas.factory.DTOFactory;
import uy.com.demente.ideas.model.Person;
import uy.com.demente.ideas.services.PersonService;
import uy.com.demente.ideas.services.SecurityService;

/**
 * @author 1987diegog
 */
@Path("persons")
public class PersonResource {

	private ResourceBundle messages = ResourceBundle.getBundle("/locale/MessageBundle");
	private static Logger log = LoggerFactory.getLogger(PersonResource.class);
	private static String TAG = "[PERSON_RESOURCE] - ";

	@EJB
	private PersonService personServices;

	@EJB
	private SecurityService securtyServices;

	/**
	 */
	@POST
	@Produces("application/json")
	public Response addPerson(@HeaderParam("Session-Token") String sessionToken, PersonDTO personDTO) {

		Response.ResponseBuilder builder = null;

		try {

			securtyServices.validateSessionToken(sessionToken);

			Person person = BOFactory.getPerson(personDTO);
			Long idPerson = personServices.addPerson(person);

			Person personAdded = personServices.getPerson(Long.valueOf(idPerson));
			PersonDTO personAddedDTO = DTOFactory.getPersonDTO(personAdded);

			// Creo una respuesta con codigo "OK".
			builder = Response.status(Response.Status.CREATED).entity(personAddedDTO);

		} catch (InvalidSessionException e) {
			ResponseDTO responseDTO = new ResponseDTO();
			log.error(TAG + messages.getString("error.unauthorized"), e);
			responseDTO.setMenssage(messages.getString("error.unauthorized"));
			builder = Response.status(Response.Status.UNAUTHORIZED).entity(responseDTO);
		} catch (Throwable e) {
			ResponseDTO responseDTO = new ResponseDTO();
			log.error(TAG + messages.getString("error.unexpected"), e);
			responseDTO.setMenssage(messages.getString("error.unexpected"));
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
		}

		return builder.build();
	}

	/**
	 * @return the person with equal id param
	 */
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getPerson(@PathParam("id") String id) {

		Response.ResponseBuilder builder = null;

		try {

			System.out.println("ID person: " + id);
			Person person = personServices.getPerson(Long.valueOf(id));

			PersonDTO personDTO = DTOFactory.getPersonDTO(person);
			// Creo una respuesta con codigo "OK".
			builder = Response.status(Response.Status.OK).entity(personDTO);

		} catch (Throwable e) {
			ResponseDTO responseDTO = new ResponseDTO();
			log.error(TAG + messages.getString("error.unexpected"), e);
			responseDTO.setMenssage(messages.getString("error.unexpected"));
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
		}

		return builder.build();
	}

	/**
	 * @return all person on registred.
	 */
	@GET
	@Produces("application/json")
	public Response getAllPerson() {

		Response.ResponseBuilder builder = null;

		try {

			List<Person> listPersons = personServices.getPersons();
			List<PersonDTO> listPersonsDTO = DTOFactory.getListPersonDTO(listPersons);

			// Creo una respuesta con codigo "OK".
			builder = Response.status(Response.Status.OK).entity(listPersonsDTO);

		} catch (Throwable e) {
			ResponseDTO responseDTO = new ResponseDTO();
			log.error(TAG + messages.getString("error.unexpected"), e);
			responseDTO.setMenssage(messages.getString("error.unexpected"));
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
		}

		return builder.build();
	}

	/**
	 */
	@PUT
	@Produces("application/json")
	public Response modifyPersonPUT(@HeaderParam("Session-Token") String sessionToken, PersonDTO personDTO) {

		Response.ResponseBuilder builder = null;

		try {

			securtyServices.validateSessionToken(sessionToken);

			Person person = BOFactory.getPerson(personDTO);
			personServices.modifyPerson(person);
			log.info("Person with id/name: " + personDTO.getId() + "/" + personDTO.getName() + " modify ok");

			// Creo una respuesta con codigo "OK".
			builder = Response.status(Response.Status.OK).entity("");

		} catch (InvalidSessionException e) {
			ResponseDTO responseDTO = new ResponseDTO();
			log.error(TAG + messages.getString("error.unauthorized"), e);
			responseDTO.setMenssage(messages.getString("error.unauthorized"));
			builder = Response.status(Response.Status.UNAUTHORIZED).entity(responseDTO);
		} catch (Throwable e) {
			ResponseDTO responseDTO = new ResponseDTO();
			log.error(TAG + messages.getString("error.unexpected"), e);
			responseDTO.setMenssage(messages.getString("error.unexpected"));
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDTO);
		}

		return builder.build();

	}

	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public String deletePerson(@HeaderParam("Session-Token") String sessionToken, @PathParam("id") String id) {

		System.out.println("ID person: " + id);
		personServices.deletePerson(Long.valueOf(id));

		return "OK";
	}
}
