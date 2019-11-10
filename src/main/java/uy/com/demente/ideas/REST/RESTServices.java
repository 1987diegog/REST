package uy.com.demente.ideas.REST;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

@Path("person")
public class RESTServices {

	private PersonServices personServices;

	/**
	 */
	@POST
	@Produces("application/json")
	public String addPerson(PersonDTO personDTO) {

		System.out.println("Name: " + personDTO.getName());
		System.out.println("LastName: " + personDTO.getLastName());
		System.out.println("Age: " + personDTO.getAge());

		personServices.addPerson(personDTO);
		System.out.println("Nuevo estudiante");
		return "OK";
	}

	/**
	 * @return the person with equal id param
	 */
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getPerson(@PathParam("id") String id) {

		System.out.println("ID person: " + id);
		PersonDTO personDTO = personServices.getPerson(Long.valueOf(id));
		Gson gson = new Gson();
		String json = gson.toJson(personDTO);

		return json;
	}

	/**
	 * @return all person on registred.
	 */
	@GET
	@Path("/all")
	@Produces("application/json")
	public String getAllPerson() {

		List<PersonDTO> listPersonDTO = personServices.getPersons();

		Gson gson = new Gson();
		String json = gson.toJson(listPersonDTO);

		return json;
	}

	/**
	 */
	@PUT
	@Produces("application/json")
	public String modifyPersonPUT(PersonDTO personDTO) {

		System.out.println("Name: " + personDTO.getName());
		System.out.println("LastName: " + personDTO.getLastName());
		System.out.println("Age: " + personDTO.getAge());

		personServices.modifyPerson(personDTO);
		System.out.println("Persona modificada con exito");
		return "OK";
	}

	/**
	 */
	@PATCH
	@Produces("application/json")
	public String modifyPersonPATCH(PersonDTO personDTO) {

		System.out.println("Name: " + personDTO.getName());
		System.out.println("LastName: " + personDTO.getLastName());
		System.out.println("Age: " + personDTO.getAge());

		personServices.modifyPerson(personDTO);
		System.out.println("Persona modificada con exito");
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
