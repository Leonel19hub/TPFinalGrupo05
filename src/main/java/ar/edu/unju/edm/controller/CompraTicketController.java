package ar.edu.unju.edm.controller;

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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CompraTicket;
import ar.edu.unju.edm.service.ICompraTicketService;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class CompraTicketController {

	private static final Log GRUPO05 = LogFactory.getLog(UsuarioController.class);

	@Autowired
	ICompraTicketService compraTicketService;

	@Autowired
	IUsuarioService usuarioService;

	@Autowired
	IPeliculaService peliculaService;

	@GetMapping("/nuevaCompraTicket")
	public ModelAndView addBuyTicket() {
		GRUPO05.info("INgresando al Metodo: ***addBuyTicket***");
		ModelAndView modelView = new ModelAndView("compraTicket");
		modelView.addObject("unaCompraTicket", compraTicketService.nuevaCompraTicket());
		modelView.addObject("usuarios", usuarioService.mostrarUsuarios());
		modelView.addObject("peliculas", peliculaService.listarPeliculas());
		return modelView;
	}

	@PostMapping("/guardarCompraTicket")
	public ModelAndView saveCompraTicket(@Valid @ModelAttribute("unaCompraTicket") CompraTicket compraTicketNuevo,
			BindingResult resultado, Authentication authentication) {
		ModelAndView modelView = new ModelAndView();
		if (resultado.hasErrors()) {
			GRUPO05.fatal("ERROR DE VALIDACION");
			modelView.setViewName("detallesPelicula");
			modelView.addObject("unaCompraTicket", compraTicketNuevo);
			return modelView;
		}
		try {
			// compraTicketNuevo.setEstadoCompra(true);
			compraTicketService.guardarCompraTicket(compraTicketNuevo);
		} catch (Exception e) {
			modelView.addObject("formUsuarioErrorMessage", e.getMessage());
			modelView.addObject("unaCompra", compraTicketService.nuevaCompraTicket());
			GRUPO05.error("saliendo del metodo: saveCompraTicket");
			modelView.setViewName("detallesPelicula");
			return modelView;
		}
		
		if (authentication == null) {

		} else {
			modelView.addObject("idUsuario", authentication.getName());
			
		}

		modelView.addObject("buyMovie", "Pelicula comprada con exito");
		modelView.addObject("unaCompra", compraTicketService.nuevaCompraTicket());
		// modelView.addObject("misCompras",
		// compraTicketService.listarComprasUsuario(id));

		// Manda los datos para mostrar en el Index, sino estara vacio -- CORREGUIR
		// ERRORES
		modelView.addObject("mostrarPeliculas", peliculaService.listarPeliculas());
		modelView.addObject("mostrarSlider", peliculaService.peliculaSlider());
		modelView.addObject("band", false);
		modelView.setViewName("/index");
		return modelView;
	}

	@GetMapping("/mostrarComprasTicket")
	public ModelAndView showTicket(Authentication authentication) {
		ModelAndView modelView = new ModelAndView("mostrarCompras");
		modelView.addObject("listaCompras", compraTicketService.listarComprasTickets());
		if (authentication == null) {

		} else {
			modelView.addObject("idUsuario", authentication.getName());
			modelView.addObject("band", false);
		}

		return modelView;
	}

	@GetMapping("/misTickets/{id}")
	public ModelAndView mostrarMisCompras(@PathVariable Long id, Authentication authentication) {
		ModelAndView modelView = new ModelAndView("misTickets");
		System.out.println(compraTicketService.listarComprasUsuario(id));
		modelView.addObject("misCompras", compraTicketService.listarComprasUsuario(id));
		modelView.addObject("band", true);
		if (authentication == null) {

		} else {
			modelView.addObject("idUsuario", authentication.getName());
			modelView.addObject("sesion", authentication.getAuthorities().toString());
			modelView.addObject("band", false);
		}

		return modelView;
	}

	@GetMapping("/eliminarTicket/{id}")
	public String eliminarTicket(@PathVariable Integer id, Model model) {
		try {
			compraTicketService.eliminarCompraTicket(id);
		} catch (Exception e) {
			GRUPO05.error("encontrando: usuario");
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			return "redirect:/mostrarComprasTicket";
		}
		return "redirect:/mostrarComprasTicket";
	}

	@GetMapping("/editarTicket/{id}")
	public ModelAndView ObtenerFormularioEditarTicket(Model model, @PathVariable(name = "compraTiket") Integer id)
			throws Exception {
		// buscar compra en el listado
		CompraTicket compraEncontrada = new CompraTicket();
		try {
			compraEncontrada = compraTicketService.buscarCompraTicket(id);

		} catch (Exception e) {
			model.addAttribute("formTicketErrorMessage", e.getMessage());
		}
		ModelAndView modelView = new ModelAndView("registroUsuario");
		modelView.addObject("unaCompraTicket", compraEncontrada);
		GRUPO05.error("saliendo del metodo: ObtenerFormularioEditarCompra" + compraEncontrada.getCompraTiket());
		modelView.addObject("band", true);
		return modelView;
	}

	@PostMapping("/editarTicket")
	public ModelAndView postEditarTicket(@ModelAttribute("compraTicketF") CompraTicket ticketModificado) {

		compraTicketService.modificarCompraTicket(ticketModificado);
		ModelAndView modelView = new ModelAndView("mostrarUsuarios");
		modelView.addObject("listaUsuario", compraTicketService.listarComprasTickets());
		modelView.addObject("formTicketErrorMessage", "Ticket Modificado Correctamente");
		GRUPO05.info("Ticket modificado se ha guardado correctamente");
		return modelView;
	}

}
