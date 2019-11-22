package uy.com.demente.ideas.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.com.demente.ideas.DAO.PersonDAO;
import uy.com.demente.ideas.model.Person;

@Stateless
@Local
public class PersonServices {

	private PersonDAO personDAO;

	public long addPerson(Person person) {
		personDAO = PersonDAO.getInstance();
		return personDAO.addPerson(person);
	}

	public Person getPerson(Long id) {
		personDAO = PersonDAO.getInstance();
		return personDAO.getPerson(id);
	}

	public List<Person> getPersons() {
		personDAO = PersonDAO.getInstance();
		return personDAO.getPersons();
	}

	public Person modifyPerson(Person person) {
		personDAO = PersonDAO.getInstance();
		return personDAO.modifyPerson(person);
	}

	public void deletePerson(Long id) {
		personDAO = PersonDAO.getInstance();
		personDAO.deletePerson(id);
	}
}
