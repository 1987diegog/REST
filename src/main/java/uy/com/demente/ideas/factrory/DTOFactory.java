package uy.com.demente.ideas.factrory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.model.Person;

public class DTOFactory {

	public static List<PersonDTO> getListPersonDTO(List<Person> listPerson) {

		List<PersonDTO> listPersonDTO = null;
		if (listPerson != null) {

			listPersonDTO = listPerson.stream().map(DTOFactory::getPersonDTO)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		return listPersonDTO;
	}

	public static PersonDTO getPersonDTO(Person person) {

		PersonDTO personDTO = null;
		if (person != null) {

			personDTO = new PersonDTO();

			System.out.println("Id: " + person.getId());
			personDTO.setId(person.getId());
			System.out.println("Name: " + person.getName());
			personDTO.setName(person.getName());
			System.out.println("LastName: " + person.getLastName());
			personDTO.setLastName(person.getLastName());
			System.out.println("Age: " + person.getAge());
			personDTO.setAge(person.getAge());
			System.out.println("CellPhone: " + person.getCellPhone());
			personDTO.setCellPhone(person.getCellPhone());
			System.out.println("StreetAddress: " + person.getStreetAddress());
			personDTO.setStreetAddress(person.getStreetAddress());
		}

		return personDTO;
	}

}
