package ar.edu.unju.edm.controller;

import java.util.Base64;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.service.IPeliculaService;

@Controller
public class PeliculaController {
	
	private static final Log GRUPO05 = LogFactory.getLog(PeliculaController.class);

    @Autowired
    IPeliculaService peliculaService;

	/*@GetMapping({"/home", "/inicio", "/index", "/"})
    public ModelAndView inicio(){
		ModelAndView modelView = new ModelAndView("index");
		modelView.addObject("mostrarPeliculas", peliculaService.listarPeliculas());
        return modelView;
    }*/
	
	@GetMapping("/nuevaPelicula")
	public ModelAndView addMovie() {
		GRUPO05.info("ingresando al metodo: bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		ModelAndView modelView = new ModelAndView("subirPelicula");
		modelView.addObject("unaPelicula", peliculaService.nuevaPelicula());		
		return modelView;
	}

	// @PostMapping(value="/guardarPelicula", consumes = "multipart/form-data" )
	// public ModelAndView saveMovie(@Valid @ModelAttribute("unaPelicula") Pelicula peliculaNueva, BindingResult resultado, @RequestParam("file") MultipartFile file) {			
	// 	ModelAndView modelView = new ModelAndView();
	// 	if (resultado.hasErrors()) {
	// 		GRUPO05.fatal("ERROR DE VALIDACION");			
	// 		modelView.setViewName("subirPelicula");
	// 		modelView.addObject("unaPelicula", peliculaNueva);			
	// 		return modelView;
	// 	}		
	// 	try {
	// 		byte[] content = file.getBytes();
	// 		String base64 = Base64.getEncoder().encodeToString(content);
	// 		peliculaNueva.setPortada(base64);
			
			
	// 		peliculaNueva.setEstadoPelicula(true);
	// 		peliculaService.guardarPelicula(peliculaNueva);
	// 	} catch (Exception e) {			
	// 		modelView.addObject("subirPeliculaErrorMessage", e.getMessage());
	// 		modelView.addObject("unaPelicula", peliculaService.nuevaPelicula());
	// 		GRUPO05.error("saliendo del metodo: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	// 		modelView.setViewName("subirPelicula");
	// 		return modelView;		
	// 	}		
		
	// 	modelView.addObject("subirPeliculaErrorMessage", "Pelicula guardado correctamente");
	// 	modelView.addObject("unaPelicula", peliculaService.nuevaPelicula());
	// 	modelView.addObject("listaPeliculas", peliculaService.listarPeliculas());
	// 	modelView.setViewName("mostrarPeliculas");
	// 	return modelView;
	// 	}

	
	@PostMapping(value="/guardarPelicula", consumes = "multipart/form-data" )
	public ModelAndView saveMovie(@Valid @ModelAttribute("unaPelicula") Pelicula peliculaNueva, BindingResult resultado, @RequestParam("file") MultipartFile[] file) {			
		ModelAndView modelView = new ModelAndView();
		if (resultado.hasErrors()) {
			GRUPO05.fatal("ERROR DE VALIDACION");			
			modelView.setViewName("subirPelicula");
			modelView.addObject("unaPelicula", peliculaNueva);			
			return modelView;
		}	
		MultipartFile file1=file[0], file2=file[1];
		
		try {
			byte[] content = file1.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliculaNueva.setPortada(base64);

			byte[] contentBanner = file2.getBytes();
			String base64Banner = Base64.getEncoder().encodeToString(contentBanner);
			peliculaNueva.setBanner(base64Banner);
			
			
			peliculaNueva.setEstadoPelicula(true);
			peliculaService.guardarPelicula(peliculaNueva);
		} catch (Exception e) {			
			modelView.addObject("subirPeliculaErrorMessage", e.getMessage());
			modelView.addObject("unaPelicula", peliculaService.nuevaPelicula());
			GRUPO05.error("saliendo del metodo: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			modelView.setViewName("subirPelicula");
			return modelView;		
		}		
		
		modelView.addObject("subirPeliculaErrorMessage", "Pelicula guardado correctamente");
		modelView.addObject("unaPelicula", peliculaService.nuevaPelicula());
		modelView.addObject("listaPeliculas", peliculaService.listarPeliculas());
		modelView.setViewName("mostrarPeliculas");
		return modelView;
		}
	
	@GetMapping("/mostrarPeliculas")	
	public ModelAndView showCourses() {
		ModelAndView vista = new ModelAndView("mostrarPeliculas");		
		vista.addObject("listaPeliculas", peliculaService.listarPeliculas());		
		return vista;
	}

	Integer rescatarId;
	String rescatarPortada, rescatarBanner;

	@GetMapping("/editarPelicula/{idPelicula}")
	public ModelAndView getFormEditMovie(Model model, @PathVariable(name = "idPelicula") Integer idPelicula) throws Exception{
		Pelicula peliculaEncontrada = new Pelicula();
		try {
			peliculaEncontrada = peliculaService.buscarPelicula(idPelicula);
		} catch (Exception e) {
			model.addAttribute("formMovieErrorMessage", e.getMessage());
		}
		GRUPO05.info("EL ESTADO DE LA PELICULA ES: "+peliculaEncontrada.getEstadoPelicula());
		ModelAndView modelView = new ModelAndView("subirPelicula");
		modelView.addObject("unaPelicula", peliculaEncontrada);
		rescatarId = peliculaEncontrada.getIdPelicula();	
		rescatarPortada = peliculaEncontrada.getPortada();
		rescatarBanner = peliculaEncontrada.getBanner();
		modelView.addObject("band", true);

		return modelView;
	}


	@PostMapping("/editarPelicula")
	public ModelAndView postEditMovie(@ModelAttribute("peliculaF") Pelicula peliculaModificada){
		peliculaModificada.setIdPelicula(rescatarId);
		peliculaModificada.setPortada(rescatarPortada);
		peliculaModificada.setBanner(rescatarBanner);
		GRUPO05.info("EL ID DE LA PELICULA: "+peliculaModificada.getIdPelicula());
		peliculaModificada.setEstadoPelicula(true);
		GRUPO05.info("EL ESTADO DE LA PELICULA ES: "+peliculaModificada.getEstadoPelicula());
		
		peliculaService.modificarPelicula(peliculaModificada);
		ModelAndView modelView = new ModelAndView("mostrarPeliculas");

		modelView.addObject("listaPeliculas", peliculaService.listarPeliculas());
		modelView.addObject("formMovieErrorMessage", "Pelicula Modificada con EXITO");
		return modelView;
	}

	@GetMapping("/eliminarPelicula/{idPelicula}")
	public String deleteMovie(@PathVariable Integer idPelicula, Model model){
		GRUPO05.info("El id de la pelicula a eliminar es: "+idPelicula);
		try {
			peliculaService.eliminarPelicula(idPelicula);
		} catch (Exception e) {
			GRUPO05.error("Buscando PELICULA");
			model.addAttribute("formMovieErrorMessage", e.getMessage());
			return "redirect:/nuevaPelicula";
		}
		return "redirect:/mostrarPeliculas";
	}
	
	

	@GetMapping("index/movie/{id}")
	public ModelAndView showDetailsMovie(@PathVariable Integer id, Model model){
		ModelAndView modelView = new ModelAndView("detallesPelicula");
		
		Pelicula pelicula = new Pelicula();
		Optional<Pelicula> peliculaOptional = peliculaService.buscarPeliculaById(id);
		pelicula = peliculaOptional.get();
		GRUPO05.info(pelicula.getTipo());
		// Pelicula aux = new Pelicula();
		pelicula.setTrailer(peliculaService.generarLinkTrailer(pelicula));
		GRUPO05.info("El id es: "+pelicula.getIdPelicula());
		GRUPO05.info("El link es: "+pelicula.getTrailer());
		// pelicula.setTrailer("prueba xd");
		modelView.addObject("movie", pelicula);
		modelView.addObject("esAdmin", false);
		modelView.addObject("linkMovie", pelicula.getTrailer());

		return modelView;
	}
	Integer idMov;
	@GetMapping("/subirBanner/{id}")
	public ModelAndView putBanner(@PathVariable Integer id) {
		GRUPO05.info("INGRESANDO AL METODO PUTBANNER");
		ModelAndView modelView = new ModelAndView("subirBanner");
		
		Pelicula pelicula = new Pelicula();
		Optional<Pelicula> peliculaOptional = peliculaService.buscarPeliculaById(id);
		pelicula = peliculaOptional.get();
		idMov = pelicula.getIdPelicula();
		modelView.addObject("movieBanner", pelicula);
		
		modelView.addObject("peliculas", peliculaService.listarPeliculas());
		return modelView;
	}

	@PostMapping(value="/subirBanner", consumes = "multipart/form-data" )
	public ModelAndView saveBanner(@Valid @ModelAttribute("unaPelicula") Pelicula peliculaNueva, BindingResult resultado, @RequestParam("banner") MultipartFile file) {			
		ModelAndView modelView = new ModelAndView();
		if (resultado.hasErrors()) {
			GRUPO05.fatal("ERROR DE VALIDACION");			
			modelView.setViewName("subirPelicula");
			modelView.addObject("unaPelicula", peliculaNueva);			
			return modelView;
		}		
		try {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliculaNueva.setPortada(base64);
			
			
			peliculaNueva.setEstadoPelicula(true);
			peliculaService.guardarPelicula(peliculaNueva);
		} catch (Exception e) {			
			modelView.addObject("subirPeliculaErrorMessage", e.getMessage());
			modelView.addObject("movieBanner", peliculaService.nuevaPelicula());
			GRUPO05.error("saliendo del metodo: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			modelView.setViewName("subirPelicula");
			return modelView;		
		}		
		
		modelView.addObject("subirPeliculaErrorMessage", "Pelicula guardado correctamente");
		modelView.addObject("unaPelicula", peliculaService.nuevaPelicula());
		modelView.addObject("listaPeliculas", peliculaService.listarPeliculas());
		modelView.setViewName("mostrarPeliculas");
		return modelView;
		}
}
