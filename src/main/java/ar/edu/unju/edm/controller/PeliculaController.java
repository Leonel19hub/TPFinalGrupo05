package ar.edu.unju.edm.controller;

import java.util.Base64;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.PeliculaRepository;
import ar.edu.unju.edm.service.ICompraTicketService;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class PeliculaController {

	private static final Log GRUPO05 = LogFactory.getLog(PeliculaController.class);

	@Autowired
	IPeliculaService peliculaService;

	@Autowired
	PeliculaRepository peliculaRepository;

	@Autowired
	ICompraTicketService compraTicketService;

	@Autowired
	IUsuarioService usuarioService;

	@GetMapping("/nuevaPelicula")
	public ModelAndView addMovie(Authentication authentication) {
		GRUPO05.info("ingresando al metodo: bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		ModelAndView modelView = new ModelAndView("subirPelicula");
		modelView.addObject("unaPelicula", peliculaService.nuevaPelicula());
		if (authentication == null) {

		} else {
			modelView.addObject("idUsuario", authentication.getName());
		}
		return modelView;
	}

	@PostMapping(value = "/guardarPelicula", consumes = "multipart/form-data")
	public ModelAndView saveMovie(@Valid @ModelAttribute("unaPelicula") Pelicula peliculaNueva, BindingResult resultado,
			@RequestParam("file") MultipartFile[] file) {
		ModelAndView modelView = new ModelAndView();
		if (resultado.hasErrors()) {
			GRUPO05.fatal("ERROR DE VALIDACION");
			modelView.setViewName("subirPelicula");
			modelView.addObject("unaPelicula", peliculaNueva);
			return modelView;
		}
		MultipartFile file1 = file[0], file2 = file[1];

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
	public ModelAndView showCourses(Authentication authentication) {
		ModelAndView vista = new ModelAndView("mostrarPeliculas");
		vista.addObject("listaPeliculas", peliculaService.listarPeliculas());
		if (authentication == null) {

		} else {
			vista.addObject("idUsuario", authentication.getName());
		}
		return vista;
	}

	Integer rescatarId;
	String rescatarPortada, rescatarBanner;

	@GetMapping("/editarPelicula/{idPelicula}")
	public ModelAndView getFormEditMovie(Model model, @PathVariable(name = "idPelicula") Integer idPelicula)
			throws Exception {
		Pelicula peliculaEncontrada = new Pelicula();
		try {
			peliculaEncontrada = peliculaService.buscarPelicula(idPelicula);
		} catch (Exception e) {
			model.addAttribute("formMovieErrorMessage", e.getMessage());
		}
		GRUPO05.info("EL ESTADO DE LA PELICULA ES: " + peliculaEncontrada.getEstadoPelicula());
		ModelAndView modelView = new ModelAndView("subirPelicula");
		modelView.addObject("unaPelicula", peliculaEncontrada);
		rescatarId = peliculaEncontrada.getIdPelicula();
		rescatarPortada = peliculaEncontrada.getPortada();
		rescatarBanner = peliculaEncontrada.getBanner();
		modelView.addObject("band", true);

		return modelView;
	}

	@PostMapping("/editarPelicula")
	public ModelAndView postEditMovie(@ModelAttribute("peliculaF") Pelicula peliculaModificada) {
		peliculaModificada.setIdPelicula(rescatarId);
		peliculaModificada.setPortada(rescatarPortada);
		peliculaModificada.setBanner(rescatarBanner);
		GRUPO05.info("EL ID DE LA PELICULA: " + peliculaModificada.getIdPelicula());
		peliculaModificada.setEstadoPelicula(true);
		GRUPO05.info("EL ESTADO DE LA PELICULA ES: " + peliculaModificada.getEstadoPelicula());

		peliculaService.modificarPelicula(peliculaModificada);
		ModelAndView modelView = new ModelAndView("mostrarPeliculas");

		modelView.addObject("listaPeliculas", peliculaService.listarPeliculas());
		modelView.addObject("formMovieErrorMessage", "Pelicula Modificada con EXITO");
		return modelView;
	}

	@GetMapping("/eliminarPelicula/{idPelicula}")
	public String deleteMovie(@PathVariable Integer idPelicula, Model model) {
		GRUPO05.info("El id de la pelicula a eliminar es: " + idPelicula);
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
	public ModelAndView showDetailsMovie(@PathVariable Integer id, Model model, Authentication authentication)
			throws Exception {
		ModelAndView modelView = new ModelAndView("detallesPelicula");
		
		Pelicula pelicula = new Pelicula();
		Optional<Pelicula> peliculaOptional = peliculaService.buscarPeliculaById(id);
		pelicula = peliculaOptional.get();
		GRUPO05.info(pelicula.getTipo());
		pelicula.setTrailer(peliculaService.generarLinkTrailer(pelicula));
		GRUPO05.info("El id es: " + pelicula.getIdPelicula());
		GRUPO05.info("El link es: " + pelicula.getTrailer());
		modelView.addObject("movie", pelicula);
		modelView.addObject("bandCompra", false);
		modelView.addObject("linkMovie", pelicula.getTrailer());
		modelView.addObject("bandSesion", true);
		modelView.addObject("band", true);

		if (authentication == null) {
			modelView.addObject("linkMovie", pelicula.getTrailer());
		} else {
			
			String usuarioIdCompra = authentication.getName();
			Long idUsuarioCompra = Long.parseLong(usuarioIdCompra);
			Usuario usuarioCompra = new Usuario();
			usuarioCompra = usuarioService.buscarUsuario(idUsuarioCompra);
			modelView.addObject("unaCompraTicket", compraTicketService.nuevaCompraTicket());
			modelView.addObject("usuarios", usuarioCompra);
			modelView.addObject("peliculas", pelicula);
			modelView.addObject("idUsuario", authentication.getName());
			modelView.addObject("band", false);
			modelView.addObject("bandSesion", false);
			modelView.addObject("ocultar", true);
			modelView.addObject("bandCompra", true);
			modelView.addObject("sesion", authentication.getAuthorities().toString());
		}

		return modelView;
	}

}
