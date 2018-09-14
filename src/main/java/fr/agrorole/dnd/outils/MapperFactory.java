package fr.agrorole.dnd.outils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MapperFactory {

	private static ObjectMapper objectMapper;

	public static ObjectMapper getObjectMapper() {
		if(null==objectMapper){

			objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);   
			objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  
			objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
			objectMapper.setVisibility(
					objectMapper.getSerializationConfig().

					getDefaultVisibilityChecker().
					withFieldVisibility(JsonAutoDetect.Visibility.ANY).
					withGetterVisibility(JsonAutoDetect.Visibility.NONE).
					withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
					);
		}
		return objectMapper;
	}

}
