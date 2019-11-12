package uy.com.demente.ideas.services;

import java.util.List;

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

@Path("persons")
public class RESTServices {

	private PersonServices personServices;

	/**
	 */
	@POST
	@Produces("application/json")
	public Response addPerson(PersonDTO newPersonDTO) {

		Response.ResponseBuilder builder = null;

		PersonDTO personDTO = new PersonDTO();

		System.out.println("Name: " + newPersonDTO.getName());
		System.out.println("LastName: " + newPersonDTO.getLastName());
		System.out.println("CellPhone: " + newPersonDTO.getCellPhone());
		System.out.println("StreetAddress: " + newPersonDTO.getStreetAddress());
		System.out.println("Age: " + newPersonDTO.getAge());

		String firstName = newPersonDTO.getName();
		personDTO.setName(firstName);

		String lastName = newPersonDTO.getLastName();
		personDTO.setLastName(lastName);

		String streetAddress = newPersonDTO.getStreetAddress();
		personDTO.setStreetAddress(streetAddress);

		String cellPhone = newPersonDTO.getCellPhone();
		personDTO.setCellPhone(cellPhone);

		int age = newPersonDTO.getAge();
		personDTO.setAge(age);

		personServices = new PersonServices();
		personServices.addPerson(personDTO);
		System.out.println("Nuevo estudiante");

		// Creo una respuesta con codigo "OK".
		builder = Response.status(Response.Status.OK).entity("Add Person:  Ok");
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
	public Response getAllPerson() {

		Response.ResponseBuilder builder = null;

		personServices = new PersonServices();
		List<PersonDTO> listPersonDTO = personServices.getPersons();

		Gson gson = new Gson();
		String json = gson.toJson(listPersonDTO);

		// Creo una respuesta con codigo "OK".
		builder = Response.status(Response.Status.OK).entity(json);
		return builder.build();
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

	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public String deletePerson(@PathParam("id") String id) {

		System.out.println("ID person: " + id);
		personServices.deletePerson(Long.valueOf(id));

		return "OK";
	}
}
