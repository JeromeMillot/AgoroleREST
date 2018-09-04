package jetonService.metier;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;

import jetonService.outils.fjdrjwt.auth0.JwtParser;

public class DonneJetonMetier {

    public static Map<String, Claim> donneJetonPrive(String jetonPublic) throws InvalidClaimException, TokenExpiredException, AlgorithmMismatchException, JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
        return JwtParser.lireJeton(jetonPublic, "secret");
    }
}