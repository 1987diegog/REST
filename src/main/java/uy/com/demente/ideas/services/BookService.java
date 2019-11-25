package uy.com.demente.ideas.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.com.demente.ideas.dao.BookDAO;
import uy.com.demente.ideas.model.Book;

/**
 * @author 1987diegog
 */
@Stateless
@Local
public class BookService {

	private BookDAO bookDAO;

	public void addBook(Book book) {
		bookDAO = BookDAO.getInstance();
		bookDAO.addBook(book);
	}

	public Book getBook(Long id) {
		bookDAO = BookDAO.getInstance();
		return bookDAO.getBook(id);
	}

	public List<Book> getBooks() {
		bookDAO = BookDAO.getInstance();
		return bookDAO.getBooks();
	}

	public Book modifyBook(Book book) {
		bookDAO = BookDAO.getInstance();
		return bookDAO.modifyBook(book);
	}

	public void deleteBook(Long id) {
		bookDAO = BookDAO.getInstance();
		bookDAO.deleteBook(id);
	}
}
