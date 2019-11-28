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

		conf.setTitle("REST API - (JAX-RS) impl Resteasy");
		conf.setDescription("Servicios REST");
		conf.setVersion("1.0.0");
		conf.setHost("localhost:8080");
		conf.setBasePath("/rest-jax-rs-resteasy/rest");

		Info info = new Info();

		Contact contact = new Contact();
		contact.setEmail("1987diegog@gmail.com");
		contact.setName("Diego Gonzalez");
		info.setContact(contact);

		info.setDescription("API REST de servicios backend.");
		info.setTitle("REST API - (JAX-RS) impl Resteasy");
		info.setVersion("1.0.0");
		conf.setInfo(info);

		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("uy.com.demente.ideas");

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