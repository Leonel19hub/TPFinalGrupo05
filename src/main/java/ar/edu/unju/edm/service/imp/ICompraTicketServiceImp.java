package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CompraTicket;
import ar.edu.unju.edm.model.Usuario;
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

	@Override
	public List<CompraTicket> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return compraTicketRepository.findByUsuario(usuario);
	}

	@Override
	public List<CompraTicket> listarComprasUsuario(Long id) {
		List<CompraTicket> auxiliar = new ArrayList<>(); int cont=0;
		auxiliar = (List<CompraTicket>) compraTicketRepository.findByEstadoCompra(true);
		System.out.println(compraTicketRepository.findByEstadoCompra(true));
		List<CompraTicket> auxCompra = new ArrayList<>();
		for(int i=0;i<auxiliar.size();i++) {
			if(auxiliar.get(i).getUsuario().getDni().equals(id)) {
				auxCompra.add(auxiliar.get(i));
				cont++;
			}
		}
		System.out.println(cont);
		return auxCompra;
	}

}
