package ar.edu.unju.edm.service.imp;

import java.util.List;

import ar.edu.unju.edm.model.Usuario;

public interface IUsuarioService {
	//el service tiene que guardar usuario 
		public void guardarUsuario(Usuario usuario ) ;
		public List <Usuario> mostrarUsuario();
		public void eliminarUsuario(Integer dni) throws Exception;
		public void modificarUsuario(Usuario usuario);
		public Usuario buscarUsuario(Integer dni) throws Exception;

}
