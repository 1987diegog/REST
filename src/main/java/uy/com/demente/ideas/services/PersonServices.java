package uy.com.demente.ideas.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.com.demente.ideas.model.Person;
import uy.com.demente.ideas.utils.CacheHelper;

@Stateless
@Local
public class PersonServices {

	public void addPerson(Person person) {
		CacheHelper.getSingletonCacheHelper().add(person);
	}

	public Person getPerson(Long id) {
		return CacheHelper.getSingletonCacheHelper().get(id);
	}

	public List<Person> getPersons() {
		return CacheHelper.getSingletonCacheHelper().getAll();
	}

	public Person modifyPerson(Person person) {
		return CacheHelper.getSingletonCacheHelper().replace(person.getId(), person);
	}

	public void deletePerson(Long id) {
		CacheHelper.getSingletonCacheHelper().deletePerson(id);
	}
}
