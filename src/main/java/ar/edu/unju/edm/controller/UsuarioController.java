package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	private static final Log GRUPO05 =LogFactory.getLog(UsuarioController.class);
	
	@GetMapping("/otroUsuario")
	public ModelAndView addUser() {
		GRUPO05.info("ingresando al metodo: agregar Usuario");
		ModelAndView modelView = new ModelAndView("cargarUsuario");
		modelView.addObject("unUsuario", nuevoUsuario);
		modelView.addObject("band",false);
		
		return modelView;
	}
	BCrytp

}
