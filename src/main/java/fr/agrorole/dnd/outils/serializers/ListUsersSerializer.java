package fr.agrorole.dnd.outils.serializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fr.agrorole.dnd.dto.User;

public class ListUsersSerializer extends JsonSerializer<List<User>> {

	@Override
	public void serialize(List<User> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeStartObject();
			gen.writeArrayFieldStart("USER_LIST");
				for(User user : value) {
					gen.writeStartObject();
						gen.writeObjectField("ID", user.getUserName());
						gen.writeObjectField("EMAIL", user.getEmail());
						gen.writeObjectField("ROLE", user.getRole());
						gen.writeObjectField("FIRST_NAME", user.getFirstName());
						gen.writeObjectField("LAST_NAME", user.getLastName());
						gen.writeObjectField("DATECREA", user.getDatecrea());
					gen.writeEndObject();
				}
			gen.writeEndArray();
		gen.writeEndObject();
		
	}

}
