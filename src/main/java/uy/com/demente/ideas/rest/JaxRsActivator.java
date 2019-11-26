package uy.com.demente.ideas.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author 1987diegog
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(PersonResource.class, BookResource.class));
	}
}