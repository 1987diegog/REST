package uy.com.demente.ideas.utils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import uy.com.demente.ideas.DAO.BookDAO;
import uy.com.demente.ideas.DAO.PersonDAO;
import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.model.LoadEnum;
import uy.com.demente.ideas.model.Person;

@Startup
@Singleton
public class Init {

	public static final LoadEnum LOAD = LoadEnum.ALL;
	public static final String NAME_CACHE_PERSONS = "dementeCachePersons";

	public Init() {
	}

	@PostConstruct
	public void loadDataApp() {

		if (LOAD.equals(LoadEnum.ALL) || LOAD.equals(LoadEnum.PERSONS)) {

			PersonDAO personDAO = PersonDAO.getInstance();

			for (int i = 0; i < PersonDAO.CONFIG_HEAP; i++) {
				Person mockPerson = MockPersons.generateMockPerson(PersonDAO.getSequence());
				personDAO.addPerson(mockPerson);
			}
		}

		if (LOAD.equals(LoadEnum.ALL) || LOAD.equals(LoadEnum.BOOKS)) {

			BookDAO bookDAO = BookDAO.getInstance();

			for (int i = 0; i < BookDAO.CONFIG_HEAP; i++) {
				Book mockBook = MockBooks.generateMockBook(BookDAO.getSequence());
				bookDAO.addBook(mockBook);
			}
		}
	}
}
