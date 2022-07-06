package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Usuario {
	
	private Integer idUser;
	@NotEmpty
	private String apellido;
	@NotEmpty
	@Size (min=3, max=10, message="El nombre de contener entre 3 a 10 caracteres")
	private String nombre;
	@Id
	@Min (value=1000000, message= "El DNI debe ser mayor que millon")
	@Max (value=99999999, message= "El DNI debe ser menor que 99999999")
	private Long dni;
	private String email;
	@NotEmpty
	private String contra;
	
	private String tipoUser;
	private Boolean estado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechanaci;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getTipoUser() {
		return tipoUser;
	}

	public void setTipoUser(String tipoUser) {
		this.tipoUser = tipoUser;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public LocalDate getFechanaci() {
		return fechanaci;
	}

	public void setFechanaci(LocalDate fechanaci) {
		this.fechanaci = fechanaci;
	}
	
	

}
