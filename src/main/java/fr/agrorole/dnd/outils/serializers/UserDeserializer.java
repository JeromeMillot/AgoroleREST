package fr.agrorole.dnd.outils.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import fr.agrorole.dnd.dto.User;

public class UserDeserializer extends JsonDeserializer<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6075934563237001583L;

	private String userMajId;
	
	public UserDeserializer(String userMajId) {
		super();
		this.userMajId = userMajId;
	}

	@Override
	public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

}
