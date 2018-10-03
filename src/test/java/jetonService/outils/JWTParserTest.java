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
		assertTrue(JWTParser.verifyToken(JWTParser.buildJWT("castelgoulz")));
	}

}
