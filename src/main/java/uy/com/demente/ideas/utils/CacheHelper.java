package uy.com.demente.ideas.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import com.github.javafaker.Faker;

import uy.com.demente.ideas.model.Person;

/**
 * 
 * @author diego.gonzalezdurand
 *
 */
public class CacheHelper {

	private static CacheHelper cacheHelper;

	private static final int HEAP = 200;
	private static final int AGE_INIT = 18;
	private static final int AGE_FINAL = 68;

	private CacheManager cacheManager;
	private Cache<Long, Person> dementeCache;
	private Long sequence;

	private CacheHelper() {

		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();

		dementeCache = cacheManager.createCache("dementeCache", CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Long.class, Person.class, ResourcePoolsBuilder.heap(HEAP)));

		sequence = 0L;
	}

	public void loadData() {

		Faker faker;
		Person person = null;

		for (int indice = 0; indice < HEAP; indice++) {

			person = new Person();
			faker = new Faker();

			// String name = faker.name().fullName();
			String firstName = faker.name().firstName();
			person.setName(firstName);

			String lastName = faker.name().lastName();
			person.setLastName(lastName);

			String streetAddress = faker.address().streetAddress();
			person.setStreetAddress(streetAddress);

			String cellPhone = faker.phoneNumber().cellPhone();
			person.setCellPhone(cellPhone);

			int age = faker.number().numberBetween(AGE_INIT, AGE_FINAL);
			person.setAge(age);

			this.add(person);
		}
	}

	public static CacheHelper getSingletonCacheHelper() {

		if (cacheHelper == null) {
			cacheHelper = new CacheHelper();
		}

		return cacheHelper;
	}

	public Cache<Long, Person> getDementeCacheFromCacheManager() {
		return cacheManager.getCache("dementeCache", Long.class, Person.class);
	}

	public void add(Person data) {
		data.setId(sequence);
		dementeCache.put(sequence, data);
		sequence++;
		System.out.println("Current sequence: " + sequence);
	}

	public Person get(Long id) {
		return dementeCache.get(id);
	}

	public Person replace(Long id, Person data) {
		return dementeCache.replace(id, data);
	}

	public void deletePerson(Long id) {
		dementeCache.remove(id);
	}

	public List<Person> getAll() {

		List<Cache.Entry<Long, Person>> listEntry = StreamSupport.stream(dementeCache.spliterator(), false)
				.collect(Collectors.toList());

		List<Person> listPerson = listEntry.stream().map(Cache.Entry::getValue).collect(Collectors.toList());

		return listPerson;

	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public Cache<Long, Person> getDementeCache() {
		return dementeCache;
	}

	public void setDementeCache(Cache<Long, Person> dementeCache) {
		this.dementeCache = dementeCache;
	}

}
