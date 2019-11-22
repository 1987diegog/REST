package uy.com.demente.ideas.factrory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uy.com.demente.ideas.dto.BookDTO;
import uy.com.demente.ideas.dto.PersonDTO;
import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.model.Person;

public class BOFactory {

	public static List<Person> getListPerson(List<PersonDTO> listPersonDTO) {

		List<Person> listPerson = null;
		if (listPersonDTO != null) {

			listPerson = listPersonDTO.stream().map(BOFactory::getPerson)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		return listPerson;
	}

	public static Person getPerson(PersonDTO personDTO) {

		Person person = null;

		if (personDTO != null) {

			person = new Person();

			System.out.println("Name: " + personDTO.getName());
			person.setName(personDTO.getName());
			System.out.println("LastName: " + personDTO.getLastName());
			person.setLastName(personDTO.getLastName());
			System.out.println("Email: " + personDTO.getEmail());
			person.setEmail(personDTO.getEmail());
			System.out.println("CellPhone: " + personDTO.getCellPhone());
			person.setCellPhone(personDTO.getCellPhone());
			System.out.println("StreetAddress: " + personDTO.getStreetAddress());
			person.setStreetAddress(personDTO.getStreetAddress());
			System.out.println("Age: " + personDTO.getAge());
			person.setAge(personDTO.getAge());

		}

		return person;
	}

	public static List<Book> getListBooks(List<BookDTO> listBookDTO) {

		List<Book> listBook = null;
		if (listBookDTO != null) {

			listBook = listBookDTO.stream().map(BOFactory::getBook).collect(Collectors.toCollection(ArrayList::new));
		}

		return listBook;
	}

	public static Book getBook(BookDTO bookDTO) {

		Book book = null;

		if (bookDTO != null) {

			book = new Book();

			System.out.println("Title: " + bookDTO.getTitle());
			book.setTitle(bookDTO.getTitle());

			System.out.println("Author: " + bookDTO.getAuthor());
			book.setAuthor(bookDTO.getAuthor());

			System.out.println("Genre: " + bookDTO.getGenre());
			book.setGenre(bookDTO.getGenre());

			System.out.println("Publisher: " + bookDTO.getPublisher());
			book.setPublisher(bookDTO.getPublisher());

		}

		return book;
	}

}
