package ar.edu.unju.edm.service.imp;

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
	    public void eliminarUsuario(Integer id) throws Exception {
	        // TODO Auto-generated method stub
	        
	    }

	    @Override
	    public void modificarUsuario(Usuario usuario) {
	        // TODO Auto-generated method stub
	        
	    }

	    @Override
	    public List<Usuario> listarUsuarios() {
	        // TODO Auto-generated method stub
	        return null;
	    }

	    @Override
	    public Usuario buscarUsuario(Integer id) throws Exception {
	        // TODO Auto-generated method stub
	        return null;
	    }
	    
	
	

}
