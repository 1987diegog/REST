package uy.com.demente.ideas.DAO;

import java.util.List;

import uy.com.demente.ideas.model.Book;

/**
 * @author diego.gonzalezdurand
 */
public class BookDAO extends CacheHelper<Book> {

	private static BookDAO bookDAO;

	public static final int CONFIG_HEAP = 200;
	public static final String NAME_CACHE_BOOKS = "dementeCacheBooks";

	private static Long sequence;

	private BookDAO() {

		super(Book.class, NAME_CACHE_BOOKS);
		initCacheHelper(CONFIG_HEAP);

		sequence = 0L;
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static BookDAO getInstance() {

		if (bookDAO == null) {
			bookDAO = new BookDAO();
		}

		return bookDAO;
	}

	public static Long getSequence() {
		return sequence++;

	}

	public void addBook(Book book) {
		this.add(book, sequence);
	}

	public Book getBook(Long id) {
		return get(id);
	}

	public List<Book> getBooks() {
		return getAll();
	}

	public Book modifyBook(Book book) {
		return replace(book.getId(), book);
	}

	public void deleteBook(Long id) {
		delete(id);
	}
}
