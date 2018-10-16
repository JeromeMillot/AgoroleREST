package fr.agrorole.dnd.outils.serializers;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fr.agrorole.dnd.dto.PJ;

public class ListCharSerializer extends JsonSerializer<List<PJ>> {

	@Override
	public void serialize(List<PJ> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeStartObject();
			gen.writeArrayFieldStart("CHAR_LIST");
				for(PJ pj : value) {
					gen.writeStartObject();
						gen.writeStringField("NAME", pj.getNom());
						gen.writeStringField("USER", pj.getUser());
						gen.writeArrayFieldStart("CLASSES");
						for(Entry<String, Short> classe : pj.getClasses().entrySet()) {
							gen.writeNumberField(classe.getKey(), classe.getValue());
						}
						gen.writeEndArray();
						gen.writeNumberField("XP", pj.getExperience());
						gen.writeStartObject("ABILITIES");
							gen.writeNumberField("FOR", pj.getForce());
							gen.writeNumberField("DEX", pj.getDexterite());
							gen.writeNumberField("CON", pj.getConstitution());
							gen.writeNumberField("SAG", pj.getSagesse());
							gen.writeNumberField("INT", pj.getIntelligence());
							gen.writeNumberField("CHA", pj.getCharisme());
						gen.writeEndObject();
						gen.writeArrayFieldStart("COMP");
							for(Entry<String, Short> comp : pj.getCompetences().entrySet()) {
								gen.writeNumberField(comp.getKey(), comp.getValue());
							}
						gen.writeEndArray();
						gen.writeArrayFieldStart("FEATS");
							for (String don : pj.getDons()) {
								gen.writeString(don);
							}
						gen.writeEndArray();
						gen.writeStartObject("SAVES");
							gen.writeNumberField("WILLSAVE", pj.getSauvVolonte());
							gen.writeNumberField("FORTITUDESAVE", pj.getSauvVigueur());
							gen.writeNumberField("REFLEXSAVE", pj.getSauvReflexe());
						gen.writeEndObject();			
						gen.writeNumberField("AGE", pj.getAge());
						gen.writeNumberField("WEIGHT", pj.getPoids());
						gen.writeStringField("EYECOLOR", pj.getCouleurYeux());
						gen.writeStringField("HAIRCOLOR", pj.getCouleurCheveux());
						gen.writeStringField("SKINCOLOR", pj.getCouleurPeau());
					gen.writeEndObject();
				}
			gen.writeEndArray();
		gen.writeEndObject();
		
	}

}
