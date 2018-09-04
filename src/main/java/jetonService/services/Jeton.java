package jetonService.services;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jetonService.metier.DonneJetonMetier;

@Path("/jeton")
public class Jeton {
    private static Logger logger = LogManager.getLogger(Jeton.class);
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJetonPublic(String jetonPublic) throws InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException, JsonProcessingException {
        if (logger.isDebugEnabled()) {
            logger.debug(jetonPublic);
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Claim> map = DonneJetonMetier.donneJetonPrive(jetonPublic);        
        if (logger.isDebugEnabled()) {
            for (Entry<String, Claim> var : map.entrySet()) {
                logger.debug(var.getKey());
                logger.debug(var.getValue().asString());
            }
        }
        if (logger.isDebugEnabled()) {
            for (Claim var : map.values()) {
                logger.debug(var.asString());
            }
        }
        String str = mapper.writeValueAsString(map);
        if (logger.isDebugEnabled()) {
            logger.debug(str);
        }        
        return str;
    }
    
}