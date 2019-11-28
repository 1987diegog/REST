package uy.com.demente.ideas.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Contact;
import io.swagger.models.Info;

/**
 * @author 1987diegog
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

	public JaxRsActivator() {

		BeanConfig conf = new BeanConfig();

		conf.setTitle("REST API - (JAX-RS) impl. RESTEasy");
		conf.setDescription("Ejemplo de API REST, los servicios forman parte del"
				+ " backend de una libreria, donde se podrá consultar las personas" //
				+ " que sean miembros de la libreria, los libros de la misma, y que libros" //
				+ " posee cada persona");

		conf.setVersion("v1.0.0");
		conf.setHost("localhost:8080");
		conf.setResourcePackage("uy.com.demente.ideas");
		conf.setBasePath("/rest-jax-rs-resteasy/rest");
		conf.setSchemes(new String[] { "HTTP", "HTTPS" });

		Contact contact = new Contact();
		contact.setEmail("1987diegog@gmail.com");
		contact.setName("Diego González");
		contact.setUrl("https://github.com/1987diegog");

		Info info = new Info();
		info.setDescription("API REST de servicios backend");
		info.setTitle("REST API - (JAX-RS) impl. RESTEasy");
		info.setVersion("v1.0.0");
		info.setContact(contact);
		conf.setInfo(info);

		// Carga la info de BeanConfig
		conf.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList( //
				PersonResource.class, //
				BookResource.class, //
				// Classes Swagger...
				ApiListingResource.class, //
				SwaggerSerializers.class));
	}
}