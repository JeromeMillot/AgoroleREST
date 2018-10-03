package fr.agrorole.dnd.services;

import static fr.agrorole.dnd.outils.MapperFactory.getObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.internal.util.Base64;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fr.agrorole.dnd.dto.User;
import fr.agrorole.dnd.exceptions.UserFieldsException;
import fr.agrorole.dnd.metier.UserMetier;
import fr.agrorole.dnd.outils.MapperFactory;
import fr.agrorole.dnd.outils.serializers.ListUsersSerializer;
import fr.agrorole.dnd.outils.serializers.UserDeserializer;
import fr.agrorole.dnd.outils.serializers.UserSerializer;

@Path("/users")
public class UserService {
	
	@Context transient HttpServletRequest request;
	private UserMetier userMetier = new UserMetier();
	@GET
	@Path("/singleUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("id")String id) throws JsonProcessingException {
		ObjectMapper mapper = MapperFactory.getObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(new UserSerializer());
		mapper.registerModule(module);
		User user = userMetier.getUserFromId(id);
		String json = mapper.writeValueAsString(user);
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allUsers")
	public Response getUserList(@DefaultValue("all") @QueryParam("like")String sr) throws JsonProcessingException {
		ObjectMapper mapper = MapperFactory.getObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ListUsersSerializer());
		mapper.registerModule(module);
		List<User> users = userMetier.getUserList(sr);
		String json = mapper.writeValueAsString(users);
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logIn")
	public Response logIn(@Context HttpHeaders headers) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException, UserFieldsException {
		List<String> credentialList = getCredentials(headers);
		String token = userMetier.getAuthToken(credentialList.get(0), credentialList.get(1));		
		return Response.status(Status.OK).entity(token).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createUser")
	public Response postUser(String body, @Context HttpHeaders headers) throws JsonParseException, JsonMappingException, IOException, UserFieldsException {
		Class<User> classe = User.class;
		ObjectMapper mapper = getObjectMapper();
		SimpleModule module = new SimpleModule();
		String userMajId = getCredentials(headers).get(0);
		module.addDeserializer(classe, new UserDeserializer(userMajId));
		mapper.registerModule(module);
		
		User user = mapper.readValue(body, classe);		
		String json = mapper.writeValueAsString(userMetier.createUser(user));
		
		return Response.status(Status.OK).entity(json).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUser(String body, @Context HttpHeaders headers) throws JsonParseException, JsonMappingException, IOException, UserFieldsException {
		Class<User> classe = User.class;
		ObjectMapper mapper = getObjectMapper();
		SimpleModule module = new SimpleModule();
		String userMajId = getCredentials(headers).get(0);
		module.addDeserializer(classe, new UserDeserializer(userMajId));
		mapper.registerModule(module);
		
		User user = mapper.readValue(body, classe);
		
		String json = mapper.writeValueAsString(userMetier.updateUser(user));
		
		return Response.status(Status.OK).entity(json).build();			
	}
	
	private List<String> getCredentials(HttpHeaders headers) {
		String b64Credentials = headers.AUTHORIZATION;
		String decoded = Base64.decodeAsString(b64Credentials);
		List<String> credentialList = new ArrayList<String>();
		for(String cred : decoded.split(":")) {
			credentialList.add(cred);
		}
		return credentialList;
	}
}
