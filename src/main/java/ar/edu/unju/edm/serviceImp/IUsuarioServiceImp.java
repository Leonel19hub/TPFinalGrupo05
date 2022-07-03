package ar.edu.unju.edm.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;

@Service
public class IUsuarioServiceImp {
	
	 @Autowired
	    UsuarioRepository usuarioRepository;

	    @Override
	    public void guardarUsuario(Usuario usuario) {
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
