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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api( // Swagger config
		value = "Resources for Person", //
		produces = MediaType.APPLICATION_JSON, //
		consumes = MediaType.APPLICATION_JSON)
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
	 * Add a new Person
	 * 
	 * @param sessionToken
	 * @param personDTO
	 * @return The new person that was created
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( // Swagger Api Operation
			value = "Add a new Person", //
			httpMethod = "POST")
	@ApiResponses(value = { //
			@ApiResponse(code = 201, message = "Person added successfully", response = PersonDTO.class),
			@ApiResponse(code = 401, message = "Access not allowed", response = ResponseDTO.class),
			@ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class) })
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
	 * @param id
	 * @return The person whose id is indicated in the path parameter
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( // Swagger Api Operation
			value = "Returns the person whose id is indicated in the path parameter", //
			httpMethod = "POST")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Request successful, the person was returned", response = PersonDTO.class),
			@ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class) })
	public Response getPerson(@PathParam("id") String id) {

		Response.ResponseBuilder builder = null;

		try {

			System.out.println(TAG + "ID person: " + id);
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
	 * @return All people in the system
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( // Swagger Api Operation
			value = "Returns all people in the system", //
			httpMethod = "POST")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Request successful, all people are returned", response = PersonDTO.class),
			@ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class) })
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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( // Swagger Api Operation
			value = "Modify the indicated person", //
			httpMethod = "POST")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Request successful, the person was modified", response = PersonDTO.class),
			@ApiResponse(code = 401, message = "Access not allowed", response = ResponseDTO.class),
			@ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class) })
	public Response modifyPersonPUT(@HeaderParam("Session-Token") String sessionToken, PersonDTO personDTO) {

		Response.ResponseBuilder builder = null;

		try {

			securtyServices.validateSessionToken(sessionToken);

			Person person = BOFactory.getPerson(personDTO);
			Person personModify = personServices.modifyPerson(person);
			log.info(TAG + "Person with id/name: " + personDTO.getId() + "/" + personDTO.getName() + " modify ok");
			PersonDTO personModifyDTO = DTOFactory.getPersonDTO(personModify);

			// Creo una respuesta con codigo "OK".
			builder = Response.status(Response.Status.OK).entity(personModifyDTO);

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
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( // Swagger Api Operation
			value = "Remove the person", //
			httpMethod = "POST")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Request successful, the person was removed", response = PersonDTO.class),
			@ApiResponse(code = 401, message = "Access not allowed", response = ResponseDTO.class),
			@ApiResponse(code = 500, message = "Unexpected Server Error", response = ResponseDTO.class) })
	public Response deletePerson(@HeaderParam("Session-Token") String sessionToken, @PathParam("id") String id) {

		Response.ResponseBuilder builder = null;

		try {

			securtyServices.validateSessionToken(sessionToken);

			log.info(TAG + "Deleted person whit id: " + id);
			personServices.deletePerson(Long.valueOf(id));
			log.info(TAG + "Person with id/name: " + id + " was deleted");

			// Creo una respuesta con codigo "OK".
			builder = Response.status(Response.Status.OK).entity(messages.getString("success.deleted"));

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
}
