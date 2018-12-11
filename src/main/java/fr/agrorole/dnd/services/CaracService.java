package fr.agrorole.dnd.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.agrorole.dnd.exceptions.CaracFieldsException;
import fr.agrorole.dnd.metier.CaracMetier;
import fr.agrorole.dnd.outils.serializers.GsonSerializationManager;

@Path("/caracs")
public class CaracService {
	
	private CaracMetier metier = new CaracMetier();
	private GsonSerializationManager serializeService = new GsonSerializationManager();
	
	@GET
	@Path("/getCarac")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharacter(@QueryParam("label")String label) throws CaracFieldsException {
		String json = serializeService.caracSerializer(metier.getCaracByKw(label));
		return Response.status(Status.OK).entity(json).build();		
	}

}
