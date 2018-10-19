package fr.agrorole.dnd.services;

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
import fr.agrorole.dnd.dto.User;
import fr.agrorole.dnd.exceptions.UserFieldsException;
import fr.agrorole.dnd.metier.UserMetier;
import fr.agrorole.dnd.outils.serializers.GsonSerializationManager;

@Path("/users")
public class UserService {
	
	@Context transient HttpServletRequest request;
	private UserMetier userMetier = new UserMetier();
	private GsonSerializationManager serializeService = new GsonSerializationManager();	
	
	@GET
	@Path("/singleUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("id")String id) throws JsonProcessingException {
		User user = userMetier.getUserFromId(id);
		String json = serializeService.userSerializer(user);
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allUsers")
	public Response getUserList(@DefaultValue("all") @QueryParam("like")String sr) throws JsonProcessingException {
		List<User> users = userMetier.getUserList(sr);
		String json = serializeService.userListSerializer(users);
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
	public Response postUser(String body) throws JsonParseException, JsonMappingException, IOException, UserFieldsException {
		User user = serializeService.userDeserializer(body);		
		String json = serializeService.userSerializer(userMetier.createUser(user));
		
		return Response.status(Status.OK).entity(json).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateUser")
	public Response putUser(String body) throws JsonParseException, JsonMappingException, IOException, UserFieldsException {
		User user = serializeService.userDeserializer(body);
		
		String json = serializeService.userSerializer(userMetier.updateUser(user));
		
		return Response.status(Status.OK).entity(json).build();			
	}
	
	@SuppressWarnings("static-access")
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
