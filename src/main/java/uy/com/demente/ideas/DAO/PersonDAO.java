package uy.com.demente.ideas.DAO;

import java.util.List;

import uy.com.demente.ideas.model.Person;

/**
 * @author diego.gonzalezdurand
 */
public class PersonDAO extends CacheHelper<Person> {

	private static PersonDAO personDAO;

	public static final int CONFIG_HEAP = 10;
	public static final String NAME_CACHE_PERSONS = "dementeCachePersons";

	private static Long sequence;

	private PersonDAO() {

		super(Person.class, NAME_CACHE_PERSONS);
		initCacheHelper(CONFIG_HEAP);

		sequence = 0L;
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static PersonDAO getInstance() {

		if (personDAO == null) {
			personDAO = new PersonDAO();
		}

		return personDAO;
	}

	public static Long getSequence() {
		System.out.println("Current sequence Person: " + sequence);
		sequence++;
		System.out.println("Next sequence Person: " + sequence);
		return sequence;
	}

	public long addPerson(Person person) {
		this.add(person, getSequence());
		return sequence;
	}

	public Person getPerson(Long id) {
		return get(id);
	}

	public List<Person> getPersons() {
		return getAll();
	}

	public Person modifyPerson(Person person) {
		return replace(person.getId(), person);
	}

	public void deletePerson(Long id) {
		delete(id);
	}
}
