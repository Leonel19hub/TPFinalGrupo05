package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;

@Service
public class IUsuarioServiceImp implements IUsuarioService{
	
	 @Autowired
	    UsuarioRepository usuarioRepository;

	 @Override
		public void guardarUsuario(@Valid Usuario usuario) {
			// TODO Auto-generated method stub
			String pw = usuario.getContrasena();
	    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
	    	usuario.setContrasena(encoder.encode(pw));
			if(usuario.getTipo() == "ADMIN"){
				usuario.setPermisosAdmin(true);
			}
			usuario.setEstado(true);
	        usuarioRepository.save(usuario);
		}

	 @Override
		public void eliminarUsuario(Long dni) throws Exception {
			// TODO Auto-generated method stub
			Usuario auxiliar = new Usuario();
	        auxiliar = buscarUsuario(dni);
	        auxiliar.setEstado(false);
	        usuarioRepository.save(auxiliar);
		}

		@Override
		public void modificarUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			usuarioRepository.save(usuario);
		}

		@Override
		public List<Usuario> mostrarUsuarios() {
			// TODO Auto-generated method stub
			List<Usuario> auxiliar = new ArrayList<>();
	        auxiliar = (List<Usuario>) usuarioRepository.findAll();
	        List<Usuario> auxiliar2 = new ArrayList<>();
			// GRUPO05.info("ingresando al metodo: listarUsuarios");
			for (int i = 0; i < auxiliar.size(); i++) {
				// GRUPO05.error("recorriendo: listadoUsuario"+auxiliar.get(i).getDni());
				
				if (auxiliar.get(i).getEstado()==true) {
					auxiliar2.add(auxiliar.get(i));		
				}            
	        }
	        // auxiliar = (List<Usuario>) usuarioRepository.findAll();
			return auxiliar2;
		}

		@Override
		public Usuario buscarUsuario(Long id) throws Exception {
			// TODO Auto-generated method stub
			Usuario usuarioEncontrado = new Usuario();
			
	        usuarioEncontrado = usuarioRepository.findByDni(id).orElseThrow(()-> new Exception("Usuario no encontrado"));

			return usuarioEncontrado;
		}

	    
	
	

}
