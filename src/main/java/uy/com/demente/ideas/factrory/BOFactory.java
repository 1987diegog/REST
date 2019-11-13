package uy.com.demente.ideas.factrory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.model.Person;

public class BOFactory {

	public static List<Person> getListPerson(List<PersonDTO> listPersonDTO) {

		List<Person> listPerson = null;
		if (listPersonDTO != null) {

			listPerson = listPersonDTO.stream().map(BOFactory::getPerson)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		return listPerson;
	}

	public static Person getPerson(PersonDTO personDTO) {

		Person person = null;
		
		if (personDTO != null) {

			person = new Person();

			System.out.println("Name: " + personDTO.getName());
			person.setName(personDTO.getName());
			System.out.println("LastName: " + personDTO.getLastName());
			person.setLastName(personDTO.getLastName());
			System.out.println("CellPhone: " + personDTO.getCellPhone());
			person.setCellPhone(personDTO.getCellPhone());
			System.out.println("StreetAddress: " + personDTO.getStreetAddress());
			person.setStreetAddress(personDTO.getStreetAddress());
			System.out.println("Age: " + personDTO.getAge());
			person.setAge(personDTO.getAge());

		}

		return person;
	}

}
