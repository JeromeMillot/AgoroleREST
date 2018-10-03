package fr.agrorole.dnd.outils.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import fr.agrorole.dnd.dto.User;

public class UserSerializer extends JsonSerializer<User> {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserSerializer() {
        this(null);
    }
 
    public UserSerializer(Class<User> t) {
        super();
    }
 
    @Override
    public void serialize(
      User user, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
	        jsonGenerator.writeStringField("ID", user.getUserName());
	        jsonGenerator.writeStringField("EMAIL", user.getEmail());
	        jsonGenerator.writeStringField("ROLE", user.getRole());
	        jsonGenerator.writeStringField("FIRST_NAME", user.getFirstName());
	        jsonGenerator.writeStringField("LAST_NAME", user.getLastName());
	        jsonGenerator.writeStringField("DATECREA", user.getDatecrea().toString());
        jsonGenerator.writeEndObject();
    }
}