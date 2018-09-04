package jetonService.outils.fjdrjwt.auth0;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtParser {

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	private static Logger logger = LogManager.getLogger(JwtParser.class);

	private static long expirationMinutes = 1;

	/**
	 * 
	 * @param credentials 
	 * @return
	 * @throws IllegalArgumentException when secret is null
	 * @throws UnsupportedEncodingException when string charset is different to UTF-8
	 */
	public static String donneJetonPrive(Map<String,Object> credentials, String password, long expirationMinutes) throws IllegalArgumentException, UnsupportedEncodingException
	{

		Algorithm algorithm = null;
		String token ="";

		algorithm = Algorithm.HMAC256(password);
		Builder builder = JWT.create();
		for(String s : credentials.keySet())
		{
			Object o = credentials.get(s);

			if(o instanceof String)
			{
				builder = builder.withClaim(s,o.toString());
			}
			if(o instanceof Date)
			{
				builder = builder.withClaim(s,(Date)o);
			}
			if(o instanceof Long)
			{
				builder = builder.withClaim(s,(Long)o);
			}

		}

		builder = builder.withIssuer("GroupeJDR");

		Instant expiredInstant = Instant.now().plus(Duration.ofDays(expirationMinutes));
		builder = builder.withExpiresAt(Date.from(expiredInstant));

		token = builder.sign(algorithm);

		return token;
	}
	public static String donneJetonPrive(Map<String,Object> credentials, String password) throws IllegalArgumentException, UnsupportedEncodingException
	{
		return donneJetonPrive(credentials, password, expirationMinutes);
	}

	/**
	 * decrypts the token with the use of passphrase
	 * @param token
	 * @param passPhrase
	 * @return the map of claims contained in the token
	 * @throws InvalidClaimException when the token's issuer is incorrect 
	 * @throws TokenExpiredException when the token is expired
	 * @throws AlgorithmMismatchException when the token's algorithm is incorrect 
	 * @throws IllegalArgumentException when secret is null
	 * @throws UnsupportedEncodingException when string charset is different to UTF-8
	 * 
	 */	
	public static Map<String, Claim> lireJeton(String token, String passPhrase) 
			throws JWTVerificationException,  InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, IllegalArgumentException, UnsupportedEncodingException
	{
		Algorithm algorithm = null;

		algorithm = Algorithm.HMAC256(passPhrase);
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer("GroupeJDR")
				.acceptExpiresAt(0)
				.build();
		DecodedJWT jwt = verifier.verify(token);
		
		return jwt.getClaims();
	}
	
	
	public static void main(String[] args) throws InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		String token ="tokentest";
		
		lireJeton(token, "secret");
	}
	
	/**
	 * decrypts the token with the use of passphrase
	 * @param token
	 * @param passPhrase
	 * @return the map of claims contained in the token
	 * @throws InvalidClaimException when the token's issuer is incorrect 
	 * @throws TokenExpiredException when the token is expired
	 * @throws AlgorithmMismatchException when the token's algorithm is incorrect 
	 * @throws IllegalArgumentException when secret is null
	 * @throws UnsupportedEncodingException when string charset is different to UTF-8
	 * 
	 */
	public static Map<String,String> lireJetonPublic(String token, String passPhrase) 
			throws JWTVerificationException,  InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, IllegalArgumentException, UnsupportedEncodingException
	{
		Algorithm algorithm = null;

		algorithm = Algorithm.HMAC256(passPhrase);
		JWTVerifier verifier = JWT.require(algorithm)
				//.acceptExpiresAt(0)
				.build(); 
		DecodedJWT jwt = verifier.verify(token);
		
		
		return parseClaims(jwt.getClaims());
	}

	/**
	 * decrypts the token with the use of passphrase and converts the claims to string
	 * @param token
	 * @param passPhrase
	 * @return the map of claims converted into String contained in the token
	 * @throws InvalidClaimException when the token's issuer is incorrect 
	 * @throws TokenExpiredException when the token is expired
	 * @throws AlgorithmMismatchException when the token's algorithm is incorrect 
	 * @throws IllegalArgumentException when secret is null
	 * @throws UnsupportedEncodingException when string charset is different to UTF-8
	 */
	public static Map<String,String> lireJetonConverter(String token, String passPhrase)
			throws InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, IllegalArgumentException, UnsupportedEncodingException
	{
		return parseClaims(lireJeton(token, passPhrase));
	}

	/**
	 * decrypts the token with the use of passphrase and converts the claims to string
	 * @param token
	 * @param passPhrase
	 * @return the map of claims converted into String contained in the token
	 * @throws InvalidClaimException when the token's issuer is incorrect 
	 * @throws TokenExpiredException when the token is expired
	 * @throws AlgorithmMismatchException when the token's algorithm is incorrect 
	 * @throws IllegalArgumentException when secret is null
	 * @throws UnsupportedEncodingException when string charset is different to UTF-8
	 */
	public static Map<String,String> lireJetonPublicConverter(String token, String passPhrase) 
			throws InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, IllegalArgumentException, UnsupportedEncodingException
	{
		return lireJetonPublic(token, passPhrase);
	}

	/**
	 * converts the map of Claims to map of Strings
	 * @param claims map of claims from JWT token
	 * @return the map of String
	 */
	public static Map<String,String> parseClaims(Map<String,Claim> claims)
	{

		Map<String,String> result = new HashMap<String,String>();

		for(String c : claims.keySet())
		{
			if(logger.isDebugEnabled())
				logger.debug(c);
			try
			{
				long longType = claims.get(c).as(long.class);
				Instant instantType = Instant.ofEpochMilli(longType);
				result.put(c,String.valueOf(longType));
				if(logger.isDebugEnabled())
					logger.debug(longType + ": " + instantType.toString());
			}catch(JWTDecodeException el)
			{
				try
				{
					Date dateType = claims.get(c).as(Date.class);
					long btime = dateType.getTime()*1000l;
					Date parsedDate = new Date(btime);
					result.put(c,String.valueOf(parsedDate));
					if(logger.isDebugEnabled())
						logger.debug(c+": "+parsedDate);
				}catch(JWTDecodeException ed)
				{
					try{
						String StringType = claims.get(c).as(String.class);
						result.put(c,StringType);
						if(logger.isDebugEnabled())
							logger.debug(c+": "+StringType);
					}catch(JWTDecodeException es){

					}
				}
			}
		}
		return result;
	}
	
	/**
	 * converts the map of Claims to map of Strings
	 * @param claims map of claims from JWT token
	 * @return the map of String
	 */
	public static Map<String,Object> parseClaimsToObjects(Map<String,Claim> claims)
	{

		Map<String,Object> result = new HashMap<String,Object>();

		for(String c : claims.keySet())
		{
			if(logger.isDebugEnabled())
				logger.debug(c);
			try
			{
				long longType = claims.get(c).as(long.class);
				Instant instantType = Instant.ofEpochMilli(longType);
				result.put(c,String.valueOf(longType));
				if(logger.isDebugEnabled())
					logger.debug(longType + ": " + instantType.toString());
			}catch(JWTDecodeException el)
			{
				try
				{
					Date dateType = claims.get(c).as(Date.class);
					long btime = dateType.getTime()*1000l;
					Date parsedDate = new Date(btime);
					result.put(c,String.valueOf(parsedDate));
					if(logger.isDebugEnabled())
						logger.debug(c+": "+parsedDate);
				}catch(JWTDecodeException ed)
				{
					try{
						String StringType = claims.get(c).as(String.class);
						result.put(c,StringType);
						if(logger.isDebugEnabled())
							logger.debug(c+": "+StringType);
					}catch(JWTDecodeException es){

					}
				}
			}
		}
		return result;
	}

//	public static void main(String[] args) throws ParseException {
//
//		Map<String,Object> credentials = new HashMap<String,Object>();
//
//		credentials.put("username","DaUsEr");
//		credentials.put("expt", Instant.now().plus(Duration.ofDays(1)).toEpochMilli());
//		credentials.put("deceas","DaUsEr");
//
//		String jeton;
//		Map<String, String> credits = null;
//
//		try {
//			jeton = donneJetonPrive(credentials);
//			credits = lireJetonConverter(jeton,"secret");
//		} catch (InvalidClaimException e) {
//			logger.error(e.getMessage(),e);
//		} catch (TokenExpiredException e) {
//			logger.error(e.getMessage(),e);
//		} catch (AlgorithmMismatchException e) {
//			logger.error(e.getMessage(),e);
//		} catch (IllegalArgumentException e) {
//			logger.error(e.getMessage(),e);
//		} catch (UnsupportedEncodingException e) {
//			logger.error(e.getMessage(),e);
//		}
//
//		for(String c : credits.keySet())
//		{
//			System.out.println(c+": "+credits.get(c));
//		}
//
//	}
}
