package ar.edu.unju.edm;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class Autenticacion implements AuthenticationSuccessHandler{
	private RedirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	//peticion y la respuesta
	
	//
	Boolean tipoCliente=false;
	Boolean tipoAdmin=false;
	
	Collection<?extends GrantedAuthority> autorizaciones = authentication.getAuthorities();
	for (GrantedAuthority grantedAuthority:autorizaciones) {
		if(grantedAuthority.getAuthority().equals("CLIENTE")) {
			tipoCliente=true;
			break;
			
		}else {
			if(grantedAuthority.getAuthority().equals("ADMIN")) {
				tipoAdmin=true;
				break;
			}
	}
	}
	if (tipoCLiente) {
	redirectStrategy.sendRedirect(request,response,"/listadodePeliculas");
	}else {
		if(tipoAdmin) {
			redirectStrategy.sendRedirect (request,response,"/cargarPeliculas");
		}
	}
	

}
