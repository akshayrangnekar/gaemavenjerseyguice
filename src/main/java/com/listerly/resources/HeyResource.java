package com.listerly.resources;

import com.listerly.SecondTest;
import com.listerly.TestClass;
import com.listerly.api.SimpleReturnObject;
import com.listerly.entities.SimpleTestEntity;
import org.glassfish.jersey.server.mvc.Template;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/Hey")
public class HeyResource {

	@Inject
	TestClass first;
	@Inject
	SecondTest second;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return ("Hey there");
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/foo")
	public String foo(@QueryParam("test") String test) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		StringBuilder builder = new StringBuilder();

		builder.append("Hi there. You entered ").append(test).append("\n");
		builder.append("The first date is:").append(sdf.format(first.getDate())).append("\n");
		builder.append("The second date is:").append(sdf.format(second.getDate())).append("\n");

		return builder.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json")
	public Object json() {
		SimpleReturnObject ret = new SimpleReturnObject();

		ret.setFruit("Apple");
		ret.setHome("Hong Kong");

		return ret;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/template")
	@Template(name = "/foo.ftl")
	public Map<String, Object> template() {
		Map<String, Object> map = new HashMap<>();
		map.put("foo", "Akshay");
		map.put("bar", "Yo Yo Whatsup?");
		return map;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/persist")
	@Template(name = "/foo.ftl")
	public Map<String, Object> persist(@QueryParam("name") String name, @QueryParam("message") String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("foo", name);
		map.put("bar", message);
		SimpleTestEntity ste = new SimpleTestEntity();
		ste.setName(name);

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactions-optional");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(ste);
		entityManager.getTransaction().commit();

		return map;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/retrieve")
	@Template(name = "/foo.ftl")
	public Map<String, Object> retrieve() {
		Map<String, Object> map = new HashMap<>();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactions-optional");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery("select t from " + SimpleTestEntity.class.getSimpleName() + " t");
		List<?> list = q.getResultList();
		map.put("foo", "Number of Entries");
		map.put("bar", "The number of database entries is: " + list.size());

		entityManager.getTransaction().commit();

		return map;
	}

}
