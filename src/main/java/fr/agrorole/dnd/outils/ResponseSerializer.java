package fr.agrorole.dnd.outils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import fr.agrorole.dnd.dto.User;

public class ResponseSerializer extends StdSerializer<User> {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseSerializer() {
        this(null);
    }
 
    public ResponseSerializer(Class<User> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      User user, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("ID", user.getUserName());
        jsonGenerator.writeStringField("Email", user.getEmail());
        jsonGenerator.writeStringField("Role", user.getRole());
        jsonGenerator.writeStringField("FirstName", user.getFirstName());
        jsonGenerator.writeStringField("LastName", user.getLastName());
        jsonGenerator.writeStringField("DateCrea", user.getDatecrea().toString());
        jsonGenerator.writeEndObject();
    }
}