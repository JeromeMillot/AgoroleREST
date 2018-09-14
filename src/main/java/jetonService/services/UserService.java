package jetonService.services;

import static jetonService.outils.fjdrjwt.MapperFactory.getObjectMapper;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/users")
public class UserService {
	
	@Context transient HttpServletRequest request;
	

	@GET
	@Path("/single_user")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@QueryParam("id")String id) throws JsonProcessingException {
		return getObjectMapper().writeValueAsString(this);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserList(@DefaultValue("all") @QueryParam("like")String sr) throws JsonProcessingException {
		return getObjectMapper().writeValueAsString(this);
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response postUser() {
		return Response.status(Status.OK).build();
	}
	
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public Response putUser() {
		return Response.status(Status.OK).build();
	}
}
