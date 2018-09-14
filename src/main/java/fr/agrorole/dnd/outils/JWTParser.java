package fr.agrorole.dnd.outils;

import java.io.UnsupportedEncodingException;import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTParser {	
	
	private static Algorithm algorithm;
	private static final String secret = "LaguardaStandStill";
	private static final String issuer = "Agorole";
	
	public JWTParser() {
		super();		
	}



	public static Map<String, Claim> getClaims(String token) throws IllegalArgumentException, UnsupportedEncodingException {	
		
		algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer(issuer)
				.acceptExpiresAt(0)
				.build();
		
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT.getClaims();
	}
	
	public static String getSignature(String token) throws IllegalArgumentException, UnsupportedEncodingException {	
		
		algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer(issuer)
				.acceptExpiresAt(0)
				.build();
		
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT.getSignature();
	}
	
	public static String checkCredentials(String token) throws IllegalArgumentException, UnsupportedEncodingException {
		byte[] credentials = Base64.decodeBase64(getClaims(token).get("sub").asString());
		System.out.println(Base64.encodeBase64("castelgoulz:mdpalacon".getBytes()));
		System.out.println(credentials);
		return credentials.toString();
		
	}
	
	public static void main(String[] args) throws IllegalArgumentException, UnsupportedEncodingException {
		checkCredentials("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJZMkZ6ZEdWc1oyOTFiSG82YldSd1lXeGhZMjl1IiwibmFtZSI6IkpvaG4gRG9lIiwiaXNzIjoiQWdvcm9sZSIsImlhdCI6MTUxNjIzOTAyMn0.PYyBr8fhIND5R9wEMeF1vvljqtN8natKlt2RkMAkyg0");
	}
}
