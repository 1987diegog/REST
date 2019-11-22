package uy.com.demente.ideas.rest;

import java.util.List;

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

import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.factrory.BOFactory;
import uy.com.demente.ideas.factrory.DTOFactory;
import uy.com.demente.ideas.model.Person;
import uy.com.demente.ideas.services.PersonServices;

@Path("persons")
public class PersonsRESTServices {

	@EJB
	private PersonServices personServices;

	/**
	 */
	@POST
	@Produces("application/json")
	public Response addPerson(PersonDTO personDTO) {

		Response.ResponseBuilder builder = null;

		Person person = BOFactory.getPerson(personDTO);
		Long idPerson = personServices.addPerson(person);
		System.out.println("Added person");

		Person personAdded = personServices.getPerson(Long.valueOf(idPerson));

		Gson gson = new Gson();
		PersonDTO personAddedDTO = DTOFactory.getPersonDTO(personAdded);
		String json = gson.toJson(personAddedDTO);

		// Creo una respuesta con codigo "OK".
		builder = Response.status(Response.Status.OK).entity(json);
		return builder.build();
	}

	/**
	 * @return the person with equal id param
	 */
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getPerson(@PathParam("id") String id) {

		System.out.println("ID person: " + id);
		Person person = personServices.getPerson(Long.valueOf(id));

		Gson gson = new Gson();
		PersonDTO personDTO = DTOFactory.getPersonDTO(person);
		String json = gson.toJson(personDTO);

		return json;
	}

	/**
	 * @return all person on registred.
	 */
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllPerson() {

		Response.ResponseBuilder builder = null;

		List<Person> listPersons = personServices.getPersons();
		List<PersonDTO> listPersonsDTO = DTOFactory.getListPersonDTO(listPersons);

		Gson gson = new Gson();
		String json = gson.toJson(listPersonsDTO);

		// Creo una respuesta con codigo "OK".
		builder = Response.status(Response.Status.OK).entity(json);
		return builder.build();
	}

	/**
	 */
	@PUT
	@Produces("application/json")
	public String modifyPersonPUT(PersonDTO personDTO) {

		Person person = BOFactory.getPerson(personDTO);
		personServices.modifyPerson(person);
		System.out.println("Person modify");
		return "OK";
	}

	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public String deletePerson(@PathParam("id") String id) {

		System.out.println("ID person: " + id);
		personServices.deletePerson(Long.valueOf(id));

		return "OK";
	}
}
