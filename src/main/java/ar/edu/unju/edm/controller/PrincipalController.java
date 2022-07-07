package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.service.IPeliculaService;

@Controller
public class PrincipalController {

	@Autowired
    IPeliculaService peliculaService;
	
	@GetMapping({"/home", "/inicio", "/index", "/"})
    public ModelAndView inicio(Model model){
		ModelAndView modelView = new ModelAndView("index");
		modelView.addObject("mostrarPeliculas", peliculaService.listarPeliculas());
		modelView.addObject("mostrarSlider", peliculaService.peliculaSlider());
		modelView.addObject("band", true);
        return modelView;
    }
	
	
	@GetMapping("/login")
	public String ingresar() {
		return "login";
	}

}