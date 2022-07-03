package ar.edu.unju.edm;

import org.apache.commons.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
			modelView.addObject("band",false);
			
			return modelView;
		}

	    @PostMapping("/guardarUsuario")
		public String saveUser(@Valid @ModelAttribute("unUsuario") Usuario usuarioNuevo, BindingResult resultado, ModelMap model) {			
				// VERIFICACION DEL NOMBRE Y DNI	
			if (resultado.hasErrors()) {
				GRUPO05.fatal("ERROR DE VALIDACION");			
				model.addAttribute("unUsuario", usuarioNuevo);			
				return "registroUsuario";
			}		
			try {
				usuarioService.guardarUsuario(usuarioNuevo);
			} catch (Exception e) {			
				model.addAttribute("formUsuarioErrorMessage", e.getMessage());
				model.addAttribute("unUsuario", usuarioNuevo);
				GRUPO05.error("saliendo del metodo: guardar usuario");
				return "registroUsuario";
			}		
			
			model.addAttribute("formUsuarioErrorMessage", "Usuario guardado correctamente");
			model.addAttribute("unUsuario", nuevoUsuario);			
			return "registroUsuario";
		}


}
