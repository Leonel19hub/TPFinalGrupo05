package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {
	
	private static final Log GRUPO05 = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
    Usuario nuevoUsuario;

    @Autowired
    IUsuarioService usuarioService;
    
    @GetMapping("/registroUsuario")
	public ModelAndView addUser() {
		GRUPO05.info("ingresando al metodo: ***Registro nuevo Usuario***");
		ModelAndView modelView = new ModelAndView("registroUsuario");
		modelView.addObject("unUsuario", nuevoUsuario);
		
		modelView.addObject("bandUser", false);
		modelView.addObject("band",false);
		
		return modelView;
	}

}
