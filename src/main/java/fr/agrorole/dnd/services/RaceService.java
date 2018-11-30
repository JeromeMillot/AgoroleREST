package fr.agrorole.dnd.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.agrorole.dnd.dto.PJ;
import fr.agrorole.dnd.dto.Race;
import fr.agrorole.dnd.exceptions.CharFieldsException;
import fr.agrorole.dnd.exceptions.RaceFieldsException;
import fr.agrorole.dnd.metier.RaceMetier;
import fr.agrorole.dnd.outils.serializers.GsonSerializationManager;

@Path("/race")
public class RaceService {
	
	private RaceMetier raceMetier = new RaceMetier();
	private GsonSerializationManager serializeService = new GsonSerializationManager();

	@GET
	@Path("/getRace")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharacter(@QueryParam("label")String label) throws RaceFieldsException {
		String json = serializeService.raceSerializer(raceMetier.getRace(label));
		return Response.status(Status.OK).entity(json).build();		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createCharacter")
	public Response postNewCharacter(String body) throws RaceFieldsException {
		Race race = serializeService.raceDeserializer(body);		
		String json = serializeService.raceSerializer(raceMetier.addRace(race));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateCharacter")
	public Response putNewCharacter(String body) throws RaceFieldsException {
		Race race = serializeService.raceDeserializer(body);		
		String json = serializeService.raceSerializer(raceMetier.updateRace(race));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@DELETE
	@Path("/deleteCharacter")
	public Response delCharacter(@QueryParam("label")String label) throws RaceFieldsException {
		raceMetier.delRace(label);
		return Response.status(Status.OK).build();
	}
}
