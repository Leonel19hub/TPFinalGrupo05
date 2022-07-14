package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Usuario {
	
	@NotEmpty(message = "Por favor ingrese su nombre")
	private String nombre;
	@NotEmpty(message = "Por favor ingrese su apellido")
	private String apellido;
	private String email;
	private String contrasena;
	private Boolean estado;
	@Max (value = 99999999, message="El DNI debe ser menor a 99.999.999")
	@Min (value = 10000000, message="El DNI debe ser mayor a 10.000.000")
	@Id
	private Long dni;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private String tipo;
	private Boolean permisosAdmin;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getPermisosAdmin() {
		return permisosAdmin;
	}

	public void setPermisosAdmin(Boolean permisosAdmin) {
		this.permisosAdmin = permisosAdmin;
	}
	
	
}
