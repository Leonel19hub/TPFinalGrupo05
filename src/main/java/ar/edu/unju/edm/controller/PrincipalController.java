package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.service.IPeliculaService;

@Controller
public class PrincipalController implements ErrorController {

	// Declaraciondevariablepararedirigirelerror
	private static final String PATH = "/error";

	@Autowired
	IPeliculaService peliculaService;

	@GetMapping({ "/home", "/inicio", "/index", "/" })
	public ModelAndView inicio(Authentication authentication) {
		ModelAndView modelView = new ModelAndView("index");
		modelView.addObject("mostrarPeliculas", peliculaService.listarPeliculas());
		modelView.addObject("mostrarSlider", peliculaService.peliculaSlider());
		modelView.addObject("band", true);
		
		//System.out.println(authentication.isAuthenticated());
		if(authentication==null) {
			
		}
		else {
			modelView.addObject("sesion", authentication.getAuthorities().toString());
			System.out.println(authentication.getAuthorities().toString());
			System.out.println(authentication.getPrincipal());
			modelView.addObject("idUsuario", authentication.getName());
			modelView.addObject("band", false);
		}
		
		//modelView.addObject("idUsuario", authentication.getName().toString());
		
		return modelView;
	}

	@GetMapping("/login")
	public String ingresar() {
		return "login";
	}

	// Valor que retorna en este caso el html error
	@RequestMapping(value = PATH)
	public String defaultErrorMessage() {
		return "error";
	}

	public static String getPath() {
		return PATH;
	}

}
