package uy.com.demente.ideas.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.com.demente.ideas.model.Person;
import uy.com.demente.ideas.utils.CacheHelper;

@Stateless
@Local
public class PersonServices {

	private CacheHelper<Person> cachePerson;

	public PersonServices() {
	}

	public void initCachePersons(String nameCache, int sizeHeap) {
		cachePerson = new CacheHelper<Person>(Person.class, nameCache);
		cachePerson.initCacheHelper(sizeHeap);
	}

	public void addPerson(Person person) {
		cachePerson.add(person);
	}

	public Person getPerson(Long id) {
		return cachePerson.get(id);
	}

	public List<Person> getPersons() {
		return cachePerson.getAll();
	}

	public Person modifyPerson(Person person) {
		return cachePerson.replace(person.getId(), person);
	}

	public void deletePerson(Long id) {
		cachePerson.delete(id);
	}
}
