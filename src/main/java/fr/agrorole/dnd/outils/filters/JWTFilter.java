package fr.agrorole.dnd.outils.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.auth0.jwt.exceptions.JWTVerificationException;

import fr.agrorole.dnd.outils.JWTParser;

public class JWTFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getAttribute("token").toString();
		if(null != token) {
			if(JWTParser.verifyToken(token)) {
				chain.doFilter(request, response);
			}
		} else {
			throw new ServletException("Le jeton de connexion est manquant.");
		}		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
