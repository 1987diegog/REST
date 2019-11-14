package uy.com.demente.ideas.utils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.model.LoadEnum;
import uy.com.demente.ideas.model.Person;

@Startup
@Singleton
public class Init {

	public static final int CONFIG_HEAP = 200;
	public static final LoadEnum LOAD = LoadEnum.ALL;
	public static final String NAME_CACHE_PERSONS = "dementeCachePersons";
	public static final String NAME_CACHE_BOOKS = "dementeCacheBooks";

	public Init() {
	}

	@PostConstruct
	public void loadDataApp() {

		if (LOAD.equals(LoadEnum.ALL) || LOAD.equals(LoadEnum.PERSONS)) {

			CacheHelper<Person> cachePerson = new CacheHelper<Person>(Person.class, NAME_CACHE_PERSONS);
			cachePerson.initCacheHelper(CONFIG_HEAP);

			for (int i = 0; i < CONFIG_HEAP; i++) {
				Person mockPerson = MockPersons.generateMockPerson(CacheHelper.getSequence());
				cachePerson.add(mockPerson);
			}
		}

		if (LOAD.equals(LoadEnum.ALL) || LOAD.equals(LoadEnum.BOOKS)) {

			CacheHelper<Book> cacheBook = new CacheHelper<Book>(Book.class, NAME_CACHE_BOOKS);
			cacheBook.initCacheHelper(CONFIG_HEAP);

			for (int i = 0; i < CONFIG_HEAP; i++) {
				Book mockBook = MockBooks.generateMockBook(CacheHelper.getSequence());
				cacheBook.add(mockBook);
			}
		}
	}
}
