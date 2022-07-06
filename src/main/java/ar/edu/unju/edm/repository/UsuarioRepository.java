package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.edm.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
	public List<Usuario> findByEstado(Boolean estado);
	

}
