package ar.edu.unju.edm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;

@Service
public interface IUsuarioService {
	
	public void guardarUsuario(@Valid Usuario usuario);
	public void eliminarUsuario(Long dni) throws Exception;
	public void modificarUsuario(Usuario usuario);
	public List<Usuario>mostrarUsuarios();
	public Usuario buscarUsuario(Long id) throws Exception;
	
}