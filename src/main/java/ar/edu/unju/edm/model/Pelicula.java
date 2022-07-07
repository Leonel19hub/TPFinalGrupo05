package ar.edu.unju.edm.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Pelicula {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPelicula;
    private String nombrePelicula;
    private String descripcion;
    // private String[] tipo; 
    private String tipo; // Genero de pelicula
    private String duracion;
    private Integer sala; // 1 sala
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinal;
    private LocalTime horario1;
    private LocalTime horario2;
    private LocalTime horario3;
    private String trailer;
    private Float rating; //1 - 10
    private Boolean estadoPelicula;
    @Lob
    private String portada;
    @Lob
    private String banner;
    private Boolean estadoBanner;
	private Boolean permisosCliente;
    
    public Pelicula() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Integer idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getNombrePelicula() {
		return nombrePelicula;
	}

	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public LocalTime getHorario1() {
		return horario1;
	}

	public void setHorario1(LocalTime horario1) {
		this.horario1 = horario1;
	}

	public LocalTime getHorario2() {
		return horario2;
	}

	public void setHorario2(LocalTime horario2) {
		this.horario2 = horario2;
	}

	public LocalTime getHorario3() {
		return horario3;
	}

	public void setHorario3(LocalTime horario3) {
		this.horario3 = horario3;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Boolean getEstadoPelicula() {
		return estadoPelicula;
	}

	public void setEstadoPelicula(Boolean estadoPelicula) {
		this.estadoPelicula = estadoPelicula;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Boolean getEstadoBanner() {
		return estadoBanner;
	}

	public void setEstadoBanner(Boolean estadoBanner) {
		this.estadoBanner = estadoBanner;
	}
    
    
}
