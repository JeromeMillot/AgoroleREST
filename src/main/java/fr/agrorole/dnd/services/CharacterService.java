package fr.agrorole.dnd.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import fr.agrorole.dnd.dto.PJ;
import fr.agrorole.dnd.exceptions.CharFieldsException;
import fr.agrorole.dnd.metier.CharacterMetier;
import fr.agrorole.dnd.outils.serializers.GsonSerializationManager;

@Path("/characters")
public class CharacterService {
	@Context transient HttpServletRequest request;
	CharacterMetier  characterMetier = new CharacterMetier();
	private GsonSerializationManager serializeService = new GsonSerializationManager();	
	
	@GET
	@Path("/getCharacter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharacter(@QueryParam("name")String name, @QueryParam("userId")String userId) throws CharFieldsException {
		String json = serializeService.pjSerializer(characterMetier.getCharacter(name, userId));
		return Response.status(Status.OK).entity(json).build();		
	}
	
	@GET
	@Path("/getAllChars")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharList() {
		String json = serializeService.pjListSerializer(characterMetier.getAllCharacters());
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Path("/getAllCharsByUserId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCharsByUserId(@QueryParam("userId")String userId) throws CharFieldsException {
		String json = serializeService.pjListSerializer(characterMetier.getAllCharactersByUserId(userId));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Path("/getAllCharsByKW")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharListbyKW(@QueryParam("keyword")String kw) throws CharFieldsException {
		String json = serializeService.pjListSerializer(characterMetier.getCharacterListFromKW(kw));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createCharacter")
	public Response postNewCharacter(String body) throws CharFieldsException {
		PJ pj = serializeService.pjDeserializer(body);		
		String json = serializeService.pjSerializer(characterMetier.createNewCharacter(pj));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateCharacter")
	public Response putNewCharacter(String body) throws CharFieldsException {
		PJ pj = serializeService.pjDeserializer(body);		
		String json = serializeService.pjSerializer(characterMetier.updateChar(pj));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@DELETE
	@Path("/deleteCharacter")
	public Response delCharacter(@QueryParam("name")String name, @QueryParam("userId")String userId) throws CharFieldsException {
		characterMetier.delCharacter(name, userId);
		return Response.status(Status.OK).build();
	}
}
