package uy.com.demente.ideas.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import uy.com.demente.ideas.dto.BookDTO;
import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.dto.SessionDTO;
import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.model.Person;
import uy.com.demente.ideas.model.Session;

/**
 * @author 1987diegog
 */
public class DTOFactory {

	/////////////////////////////////////////////////////////////
	//////////////////////// SESSIONS ///////////////////////////
	/////////////////////////////////////////////////////////////

	public static SessionDTO getSessionDTO(Session session) {

		SessionDTO sessionDTO = null;
		if (session != null) {
			sessionDTO = new SessionDTO();
			BeanUtils.copyProperties(session, sessionDTO);
		}

		return sessionDTO;
	}

	/////////////////////////////////////////////////////////////
	///////////////////////// PERSONS ///////////////////////////
	/////////////////////////////////////////////////////////////

	public static List<PersonDTO> getListPersonDTO(List<Person> listPerson) {

		List<PersonDTO> listPersonDTO = null;
		if (listPerson != null) {

			listPersonDTO = listPerson.stream().map(DTOFactory::getPersonDTO)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		return listPersonDTO;
	}

	public static PersonDTO getPersonDTO(Person person) {

		PersonDTO personDTO = null;
		if (person != null) {
			personDTO = new PersonDTO();
			BeanUtils.copyProperties(person, personDTO);
		}

		return personDTO;
	}

	/////////////////////////////////////////////////////////////
	////////////////////////// BOOKS ////////////////////////////
	/////////////////////////////////////////////////////////////

	public static List<BookDTO> getListBooks(List<Book> listBook) {

		List<BookDTO> listBookDTO = null;
		if (listBook != null) {

			listBookDTO = listBook.stream().map(DTOFactory::getBookDTO)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		return listBookDTO;
	}

	public static BookDTO getBookDTO(Book book) {

		BookDTO bookDTO = null;

		if (book != null) {
			bookDTO = new BookDTO();
			BeanUtils.copyProperties(book, bookDTO);
		}

		return bookDTO;
	}

}
