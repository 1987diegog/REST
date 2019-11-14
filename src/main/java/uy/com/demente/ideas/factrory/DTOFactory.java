package uy.com.demente.ideas.factrory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uy.com.demente.ideas.dto.BookDTO;
import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.model.Person;

public class DTOFactory {

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

			System.out.println("Id: " + person.getId());
			personDTO.setId(person.getId());
			System.out.println("Name: " + person.getName());
			personDTO.setName(person.getName());
			System.out.println("LastName: " + person.getLastName());
			personDTO.setLastName(person.getLastName());
			System.out.println("Age: " + person.getAge());
			personDTO.setAge(person.getAge());
			System.out.println("CellPhone: " + person.getCellPhone());
			personDTO.setCellPhone(person.getCellPhone());
			System.out.println("StreetAddress: " + person.getStreetAddress());
			personDTO.setStreetAddress(person.getStreetAddress());
		}

		return personDTO;
	}

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

			System.out.println("Title: " + book.getTitle());
			bookDTO.setTitle(book.getTitle());

			System.out.println("Author: " + book.getAuthor());
			bookDTO.setAuthor(book.getAuthor());

			System.out.println("Genre: " + book.getGenre());
			bookDTO.setGenre(book.getGenre());

			System.out.println("Publisher: " + book.getPublisher());
			bookDTO.setPublisher(book.getPublisher());

		}

		return bookDTO;
	}

}
