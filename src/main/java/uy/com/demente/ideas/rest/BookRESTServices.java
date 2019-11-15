package uy.com.demente.ideas.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import uy.com.demente.ideas.dto.BookDTO;
import uy.com.demente.ideas.factrory.BOFactory;
import uy.com.demente.ideas.factrory.DTOFactory;
import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.services.BookServices;

@Path("books")
public class BookRESTServices {

	@EJB
	private BookServices bookServices;

	/**
	 */
	@POST
	@Produces("application/json")
	public Response addBook(BookDTO bookDTO) {

		Response.ResponseBuilder builder = null;

		Book book = BOFactory.getBook(bookDTO);
		bookServices.addBook(book);
		System.out.println("Added book");

		Gson gson = new Gson();
		String message = "Added Book:  Ok";
		String json = gson.toJson(message);

		// Creo una respuesta con codigo "OK".
		builder = Response.status(Response.Status.OK).entity(json);
		return builder.build();
	}

	/**
	 * @return the book with equal id param
	 */
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getBook(@PathParam("id") String id) {

		System.out.println("ID book: " + id);
		Book book = bookServices.getBook(Long.valueOf(id));

		Gson gson = new Gson();
		BookDTO bookDTO = DTOFactory.getBookDTO(book);
		String json = gson.toJson(bookDTO);

		return json;
	}

	/**
	 * @return all book on registred.
	 */
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllBook() {

		Response.ResponseBuilder builder = null;

		List<Book> listBooks = bookServices.getBooks();
		List<BookDTO> listBooksDTO = DTOFactory.getListBooks(listBooks);

		Gson gson = new Gson();
		String json = gson.toJson(listBooksDTO);

		// Creo una respuesta con codigo "OK".
		builder = Response.status(Response.Status.OK).entity(json);
		return builder.build();
	}

	/**
	 */
	@PUT
	@Produces("application/json")
	public String modifyBookPUT(BookDTO bookDTO) {

		Book book = BOFactory.getBook(bookDTO);
		bookServices.modifyBook(book);
		System.out.println("Book modify");
		return "OK";
	}

	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public String deleteBook(@PathParam("id") String id) {

		System.out.println("ID book: " + id);
		bookServices.deleteBook(Long.valueOf(id));

		return "OK";
	}
}
