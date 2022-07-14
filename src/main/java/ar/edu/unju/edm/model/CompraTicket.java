package ar.edu.unju.edm.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class CompraTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer compraTiket;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCompra;
    private LocalTime horario;
    private Boolean estadoCompra;
    
    public CompraTicket() {
    }
    
    public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Integer getCompraTiket() {
        return compraTiket;
    }

    public void setCompraTiket(Integer compraTiket) {
        this.compraTiket = compraTiket;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Boolean getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(Boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    
    
}