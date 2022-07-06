package ar.edu.unju.edm;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;

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
	 @GetMapping("/listadoUsuario")	
		public ModelAndView showUser() {
			ModelAndView modelView = new ModelAndView("mostrarUsuarios");		
			modelView.addObject("listaUsuario", usuarioService.mostrarUsuarios());	
			//GRUPO05.info("ingresando al metodo: show User"+usuarioService.listarUsuarios().get(0).getApellido());
			return modelView;
		}
	  @GetMapping("/eliminarUsuario/{id}")
		public String eliminar(@PathVariable Long id, Model model) {	
			try {
			usuarioService.eliminarUsuario(id);
			}catch(Exception e) {
				GRUPO05.error("encontrando: usuario");
				model.addAttribute("formUsuarioErrorMessage", e.getMessage());
				return "redirect:/registroUsuario";			
			}
			return "redirect:/listadoUsuario";
		}
	    
	    @GetMapping("/editarUsuario/{dni}")
		public ModelAndView ObtenerFormularioEditarUsuario(Model model, @PathVariable(name="dni") Long dni) throws Exception {
			//buscar usuario en el listado
			Usuario usuarioEncontrado = new Usuario();
			try {
				usuarioEncontrado = usuarioService.buscarUsuario(dni);	
				
			} catch (Exception e) {
				model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			}
			ModelAndView modelView = new ModelAndView("registroUsuario");
			modelView.addObject("unUsuario", usuarioEncontrado);
			GRUPO05.error("saliendo del metodo: ObtenerFormularioEditarUsuario"+ usuarioEncontrado.getDni());
			modelView.addObject("band",true);		
			return modelView;
		}
	    
	    @PostMapping("/editarUsuario")
		public ModelAndView postEditarUsuario(@ModelAttribute("usuarioF") Usuario usuarioModificado) {		
					
			usuarioService.modificarUsuario(usuarioModificado);
			ModelAndView modelView = new ModelAndView("mostrarUsuarios");		
			modelView.addObject("listaUsuario", usuarioService.mostrarUsuarios());	
			modelView.addObject("formUsuarioErrorMessage","Usuario Modificado Correctamente");
			GRUPO05.info("Usuario modificado se ha guardado correctamente");
			return modelView;
		}



}
