package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CompraTicket;
import ar.edu.unju.edm.repository.CompraTicketRepository;
import ar.edu.unju.edm.service.ICompraTicketService;

@Service
public class ICompraTicketServiceImp implements ICompraTicketService{
	
	@Autowired
    CompraTicket compraTicket;

    @Autowired
    CompraTicketRepository compraTicketRepository;

	@Override
	public CompraTicket nuevaCompraTicket() {
		// TODO Auto-generated method stub
		return compraTicket;
	}

	@Override
	public void guardarCompraTicket(CompraTicket compraTicket) {
		// TODO Auto-generated method stub
		compraTicket.setEstadoCompra(true);
		compraTicketRepository.save(compraTicket);
	}

	@Override
	public void eliminarCompraTicket(Integer id) throws Exception {
		// TODO Auto-generated method stub
		CompraTicket compra = new CompraTicket();
		compra = buscarCompraTicket(id);
		compra.setEstadoCompra(false);
		compraTicketRepository.save(compra);
	}

	@Override
	public void modificarCompraTicket(CompraTicket compra) {
		// TODO Auto-generated method stub
		compraTicketRepository.save(compra);
	}

	@Override
	public List<CompraTicket> listarComprasTickets() {
		// TODO Auto-generated method stub
		// return (List<CompraTicket>) compraTicketRepository.findAll();
		return compraTicketRepository.findByEstadoCompra(true);
	}

	@Override
	public CompraTicket buscarCompraTicket(Integer id) throws Exception {
		// TODO Auto-generated method stub
		CompraTicket encontrado = new CompraTicket();
		
        encontrado = compraTicketRepository.findById(id).orElseThrow(()-> new Exception("Usuario no encontrado"));
		
		return encontrado;
	}

}
