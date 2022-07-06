package ar.edu.unju.edm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;


@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//Busqueda de usuario
		Usuario usuarioEncontrado=usuarioRepository.findById(Long.parseLong(dni).orElseThrow(()->new UsernameNoteFoundException("Usuario Invalido"));
		
		//Definir Autorizaciones
		List <GrantedAuthority> tipoUser = new ArrayList<>();
		GrantedAutority grantedAuthority = new SimpleGrantedAuthority (usuarioEncontrado.getTipoUser());
		TipoUser.add(grantedAuthority);
		
		//Definir el usuario en sesion
		UserDetails usuarioEnSesion = new User(dni,usuarioEncontrado.getContra(),tipoUser);
		
		
		return usuarioEnsesion;
	}

}
