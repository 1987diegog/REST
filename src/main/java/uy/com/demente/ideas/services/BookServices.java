package uy.com.demente.ideas.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.utils.CacheHelper;

@Stateless
@Local
public class BookServices {

	private CacheHelper<Book> cacheBook;

	public void initCacheBooks(String nameCache, int sizeHeap) {
		cacheBook = new CacheHelper<Book>(Book.class, nameCache);
		cacheBook.initCacheHelper(sizeHeap);
	}

	public void addBook(Book book) {
		cacheBook.add(book);
	}

	public Book getBook(Long id) {
		return cacheBook.get(id);
	}

	public List<Book> getBooks() {
		return cacheBook.getAll();
	}

	public Book modifyBook(Book book) {
		return cacheBook.replace(book.getId(), book);
	}

	public void deleteBook(Long id) {
		cacheBook.delete(id);
	}
}
