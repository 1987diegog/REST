package uy.com.demente.ideas.dto;

import java.io.Serializable;

/**
 * @author 1987diegog
 */
public class BookDTO extends ResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String author;
	private String publisher;
	private String genre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
