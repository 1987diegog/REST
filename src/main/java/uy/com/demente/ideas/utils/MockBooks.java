package uy.com.demente.ideas.utils;

import com.github.javafaker.Faker;

import uy.com.demente.ideas.model.Book;

public class MockBooks {

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Book generateMockBook(Long id) {

		Faker faker = new Faker();
		Book book = new Book();

		book.setId(id);
		book.setAuthor(faker.book().author());
		book.setGenre(faker.book().genre());
		book.setPublisher(faker.book().publisher());
		book.setTitle(faker.book().title());

		return book;
	}
}
