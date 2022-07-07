package ar.edu.unju.edm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pelicula;

@Service
public interface IPeliculaService {
	public Pelicula nuevaPelicula();
    public void guardarPelicula(Pelicula pelicula);
    public void eliminarPelicula(Integer idPelicula) throws Exception;
    public void modificarPelicula(Pelicula pelicula);
    public List<Pelicula> listarPeliculas();
    public Pelicula buscarPelicula(Integer idPelicula) throws Exception;
    public Optional<Pelicula> buscarPeliculaById(Integer id);
    public String generarLinkTrailer(Pelicula movie);
    public String[] generoPelicula();
    public List<Pelicula> peliculaSlider();
}
