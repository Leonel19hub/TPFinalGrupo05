package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CompraTicket;
import ar.edu.unju.edm.model.Usuario;

@Service
public interface ICompraTicketService {

	public CompraTicket nuevaCompraTicket();
    public void guardarCompraTicket(CompraTicket compraTicket);
    public void eliminarCompraTicket(Integer id) throws Exception;
    public void modificarCompraTicket(CompraTicket compra);
    public List<CompraTicket> listarComprasTickets();
    public CompraTicket buscarCompraTicket(Integer id) throws Exception;
    public List<CompraTicket> findByUsuario(Usuario usuario);
    public List<CompraTicket> listarComprasUsuario(Long id);
}
