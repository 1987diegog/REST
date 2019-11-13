package uy.com.demente.ideas.utils;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.github.javafaker.Faker;

import uy.com.demente.ideas.dto.PersonDTO;

@Startup
@Singleton
public class DataCollection {

	private List<PersonDTO> list;
	private static final int CANT_PERSONS = 200;
	private static final int AGE_INIT = 18;
	private static final int AGE_FINAL = 68;

	public DataCollection() {

		list = new ArrayList<>();

		Faker faker;
		PersonDTO personDTO = null;

		for (int i = 0; i < CANT_PERSONS; i++) {

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

			int age = faker.number().numberBetween(AGE_INIT, AGE_FINAL);
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
