package uy.com.demente.ideas.utils;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import uy.com.demente.ideas.dto.PersonDTO;

public class DataCollection {

	private List<PersonDTO> list;

	public DataCollection(int cant) {

		list = new ArrayList<>();

		Faker faker;
		PersonDTO personDTO = null;

		for (int i = 0; i < cant; i++) {

			personDTO = new PersonDTO();
			faker = new Faker();

			// String name = faker.name().fullName();
			String firstName = faker.name().firstName();
			personDTO.setName(firstName);

			String lastName = faker.name().lastName();
			personDTO.setLastName(lastName);

			String streetAddress = faker.address().streetAddress();
			personDTO.setStreetAddress(streetAddress);

			String cellPhone = faker.phoneNumber().cellPhone();
			personDTO.setCellPhone(cellPhone);

			int age = faker.number().numberBetween(18, 84);
			personDTO.setAge(age);

			list.add(personDTO);
		}

	}

	public List<PersonDTO> getList() {
		return list;
	}

	public void setList(List<PersonDTO> list) {
		this.list = list;
	}

}
