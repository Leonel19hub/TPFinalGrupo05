package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.controller.PeliculaController;
import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.repository.PeliculaRepository;
import ar.edu.unju.edm.service.IPeliculaService;

@Service
public class IPeliculaServiceImp implements IPeliculaService{
	
	public static final Log GRUPO05 = LogFactory.getLog(PeliculaController.class);
	
	@Autowired
	Pelicula nuevaPelicula;
	
	@Autowired
	PeliculaRepository peliculaRepository;

	@Override
	public Pelicula nuevaPelicula() {
		// TODO Auto-generated method stub
		return nuevaPelicula;
	}

	@Override
	public void guardarPelicula(Pelicula pelicula) {
		// TODO Auto-generated method stub
		peliculaRepository.save(pelicula);
	}

	@Override
	public void eliminarPelicula(Integer idPelicula) throws Exception {
		// TODO Auto-generated method stub
		GRUPO05.info("LA PELICULA A ELIMINAR ES: "+idPelicula);
        Pelicula aux = new Pelicula();
        aux = buscarPelicula(idPelicula);
        aux.setEstadoPelicula(false);
        GRUPO05.info("EL ESTAOD ES: "+aux.getEstadoPelicula());
        peliculaRepository.save(aux);
	}

	@Override
	public void modificarPelicula(Pelicula pelicula) {
		// TODO Auto-generated method stub
		peliculaRepository.save(pelicula);
	}

	@Override
	public List<Pelicula> listarPeliculas() {
		// TODO Auto-generated method stub
		return peliculaRepository.findByEstadoPelicula(true);
	}

	@Override
	public Pelicula buscarPelicula(Integer idPelicula) throws Exception {
		// TODO Auto-generated method stub
		Pelicula peliculaEncontrada = new Pelicula();
        peliculaEncontrada = peliculaRepository.findById(idPelicula).orElseThrow(()-> new Exception("Pelicula No Encotrada"));
        return peliculaEncontrada;
	}

	@Override
	public Optional<Pelicula> buscarPeliculaById(Integer id) {
		// TODO Auto-generated method stub
		return peliculaRepository.findById(id);
	}

	@Override
	public String generarLinkTrailer(Pelicula movie) {
		String src = "https://www.youtube.com/embed/"+movie.getTrailer();
        String iframeTrailer = "<iframe width='510' height='265' "+src+" title='YouTube video player' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>";
        movie.setTrailer(src);    
        return movie.getTrailer();
	}

	@Override
	public String[] generoPelicula() {
		String [] genero = new String[10];
        genero[0] = "Accion ";
        genero[1] = "Aventura ";
        genero[2] = "Animacion ";
        genero[3] = "Ciencia Ficcion ";
        genero[4] = "Comedia ";
        genero[5] = "Drama ";
        genero[6] = "Fantasia ";
        genero[7] = "Musical ";
        genero[8] = "Suspenso ";
        genero[9] = "Terror ";
        return genero;
	}

	@Override
	public List<Pelicula> peliculaSlider() {
		List<Pelicula> movieSlider = (List<Pelicula>)  peliculaRepository.findByEstadoBanner(true);
		List<Pelicula> aux = new ArrayList<>();
		for (int i = 0; i < movieSlider.size(); i++) {
			if(movieSlider.get(i).getEstadoPelicula()){
				aux.add(movieSlider.get(i));
			}
		}
		return aux;
        // return peliculaRepository.findByEstadoBanner(true);
	}

}
