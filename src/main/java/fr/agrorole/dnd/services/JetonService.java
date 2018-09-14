package fr.agrorole.dnd.services;

import java.io.UnsupportedEncodingException;
import java.util.Map;
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
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fr.agrorole.dnd.dto.User;
import fr.agrorole.dnd.metier.DonneJetonMetier;
import fr.agrorole.dnd.outils.ResponseSerializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/jeton")
public class JetonService {
    private static Logger logger = LogManager.getLogger(JetonService.class);
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJetonPublic(String jetonPublic) throws InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException, JsonProcessingException {
    	System.out.println("ICI: " + jetonPublic);
    	String str = "";
    	if (logger.isDebugEnabled()) {
            logger.debug(jetonPublic);            
        }
        
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ResponseSerializer", new Version(1, 0, 0, null, null, null));
        		module.addSerializer(User.class, new ResponseSerializer());
        		mapper.registerModule(module);
        Map<String, Claim> map = DonneJetonMetier.donneJetonPrive(jetonPublic);   
        
        if (logger.isDebugEnabled()) {
            for (Entry<String, Claim> var : map.entrySet()) {
                logger.debug(var.getKey());
                logger.debug(var.getValue().asString());
            }
            logger.debug(map.toString());
        }        
        
//        String str = mapper.writeValueAsString(map);
        if (logger.isDebugEnabled()) {
            logger.debug(str);
        }        
        return str;
    }
    
}