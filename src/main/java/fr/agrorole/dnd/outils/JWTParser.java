package fr.agrorole.dnd.outils;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.codec.binary.Base64;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTParser {	
	
	private static Algorithm algorithm;
	private static final String secret = "LaguardaStandStill";
	private static final String issuer = "Agorole";
	
	public JWTParser() {
		super();		
	}

	public static String buildJWT(String userId) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {		
		Instant now = Instant.now();
		Date oneDayMore = Date.from(now.plus(Duration.ofDays(1)));
		Builder tokenBuilder = JWT.create();
		tokenBuilder.withSubject(userId);
		tokenBuilder.withIssuer(issuer);
		tokenBuilder.withIssuedAt(Date.from(now));
		tokenBuilder.withExpiresAt(oneDayMore);
		
		return tokenBuilder.sign(Algorithm.HMAC256(secret));
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
		System.out.println(decodedJWT.getHeader());
		return decodedJWT.getSignature();
	}
	
	public static String checkCredentials(String token) throws IllegalArgumentException, UnsupportedEncodingException {
		String credentials = getClaims(token).get("sub").asString();
		System.out.println(Base64.encodeBase64("castelgoulz:mdpalacon".getBytes()).toString());
		System.out.println(credentials);
		return credentials.toString();
		
	}
	
	public static Boolean verifyToken(String token) throws IllegalArgumentException, UnsupportedEncodingException {
		Map<String, Claim> claims = getClaims(token);
		for(Entry<String, Claim> entry : claims.entrySet()) {
			if (entry.getKey() == "exp") {
				long exp = entry.getValue().asDate().getTime();
				long today = Date.from(Instant.now()).getTime();
				if(exp>=today) {
					throw new JWTVerificationException("Le jeton est périmé.");
				}
			} else if(entry.getKey() == "iss") {
				String iss = entry.getValue().asString();
				if(iss != issuer) {
					throw new JWTVerificationException("Le fournisseur du jeton est invalide.");
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IllegalArgumentException, UnsupportedEncodingException {
		String jeton = buildJWT("castelgoulz");
		checkCredentials(jeton);
		String test = getSignature(jeton);
		System.out.println(jeton);
	}
}
