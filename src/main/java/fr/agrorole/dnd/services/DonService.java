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

import fr.agrorole.dnd.dto.Don;
import fr.agrorole.dnd.exceptions.DonFieldsException;
import fr.agrorole.dnd.metier.DonMetier;
import fr.agrorole.dnd.outils.serializers.GsonSerializationManager;

@Path("/don")
public class DonService {
	
	private DonMetier donMetier = new DonMetier();
	private GsonSerializationManager serializeService = new GsonSerializationManager();
	
	@GET
	@Path("/getAllDon")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGetAll() {
		String json = serializeService.listDonSerializer(donMetier.getAlldons());
		return Response.status(Status.OK).entity(json).build();
	}
	
	@GET
	@Path("/getDon")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGetDon(@QueryParam("label")String label) throws DonFieldsException {
		String json = serializeService.donSerializer(donMetier.getDonbyKeyWord(label));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@POST
	@Path("/addDon")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doPostDon(String body) throws DonFieldsException {
		Don don = serializeService.donDeserializer(body);
		String json = serializeService.donSerializer(donMetier.addDon(don));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@PUT
	@Path("/updateDon")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doPutDon(String body) throws DonFieldsException {
		Don don = serializeService.donDeserializer(body);
		String json = serializeService.donSerializer(donMetier.updateDon(don));
		return Response.status(Status.OK).entity(json).build();
	}
	
	@DELETE
	@Path("/deleteDon")
	public Response doDeleteDon(@QueryParam("label")String label) throws DonFieldsException {
		donMetier.delDon(label);
		return Response.status(Status.OK).build();
	}

}
