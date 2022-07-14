package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.CompraTicket;
import ar.edu.unju.edm.model.Usuario;

@Repository
public interface CompraTicketRepository extends CrudRepository<CompraTicket, Integer>{

    public List<CompraTicket> findByEstadoCompra(Boolean estadoCompra);
    public List<CompraTicket> findByUsuario(Usuario usuario);

}
