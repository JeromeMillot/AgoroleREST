package jetonService.outils;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import fr.agrorole.dnd.outils.JWTParser;

public class JWTParserTest {

	@Before
	
	
	@Test
	public void test() throws IllegalArgumentException, UnsupportedEncodingException {
		assertEquals("castelgoulz:mdpalacon", JWTParser.checkCredentials("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJZMkZ6ZEdWc1oyOTFiSG82YldSd1lXeGhZMjl1IiwibmFtZSI6IkpvaG4gRG9lIiwiaXNzIjoiQWdvcm9sZSIsImlhdCI6MTUxNjIzOTAyMn0.PYyBr8fhIND5R9wEMeF1vvljqtN8natKlt2RkMAkyg0"));
	}

}
