package uy.com.demente.ideas.utils;

import com.github.javafaker.Faker;

import uy.com.demente.ideas.model.Person;

public class MockPersons {

	private static final int AGE_INIT = 18;
	private static final int AGE_FINAL = 68;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Person generateMockPerson(Long id) {

		Faker faker = new Faker();
		Person person = new Person();

		person.setId(id);
		person.setName(faker.name().firstName());
		person.setLastName(faker.name().lastName());
		person.setStreetAddress(faker.address().streetAddress());
		person.setCellPhone(faker.phoneNumber().cellPhone());

		int age = faker.number().numberBetween(AGE_INIT, AGE_FINAL);
		person.setAge(age);

		return person;
	}
}
