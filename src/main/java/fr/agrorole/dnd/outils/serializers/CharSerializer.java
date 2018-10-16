package fr.agrorole.dnd.outils.serializers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fr.agrorole.dnd.dto.PJ;

public class CharSerializer extends JsonSerializer<PJ> {
	
	public CharSerializer() {
		this(null);
	}

	public CharSerializer(Class<PJ> t) {
		super();
	}
	
	

	@Override
	public void serialize(PJ value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeStartObject("CHARACTER");
			gen.writeStringField("NAME", value.getNom());
			gen.writeStringField("USER", value.getUser());
			gen.writeArrayFieldStart("CLASSES");
			for(Entry<String, Short> classe : value.getClasses().entrySet()) {
				gen.writeNumberField(classe.getKey(), classe.getValue());
			}
			gen.writeEndArray();
			gen.writeNumberField("XP", value.getExperience());
			gen.writeStartObject("ABILITIES");
				gen.writeNumberField("FOR", value.getForce());
				gen.writeNumberField("DEX", value.getDexterite());
				gen.writeNumberField("CON", value.getConstitution());
				gen.writeNumberField("SAG", value.getSagesse());
				gen.writeNumberField("INT", value.getIntelligence());
				gen.writeNumberField("CHA", value.getCharisme());
			gen.writeEndObject();
			gen.writeArrayFieldStart("COMP");
				for(Entry<String, Short> comp : value.getCompetences().entrySet()) {
					gen.writeNumberField(comp.getKey(), comp.getValue());
				}
			gen.writeEndArray();
			gen.writeArrayFieldStart("FEATS");
				for (String don : value.getDons()) {
					gen.writeString(don);
				}
			gen.writeEndArray();
			gen.writeStartObject("SAVES");
				gen.writeNumberField("WILLSAVE", value.getSauvVolonte());
				gen.writeNumberField("FORTITUDESAVE", value.getSauvVigueur());
				gen.writeNumberField("REFLEXSAVE", value.getSauvReflexe());
			gen.writeEndObject();			
			gen.writeNumberField("AGE", value.getAge());
			gen.writeNumberField("WEIGHT", value.getPoids());
			gen.writeStringField("EYECOLOR", value.getCouleurYeux());
			gen.writeStringField("HAIRCOLOR", value.getCouleurCheveux());
			gen.writeStringField("SKINCOLOR", value.getCouleurPeau());
		gen.writeEndObject();
		
	}

}
