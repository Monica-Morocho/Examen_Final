package ec.edu.ups.rest;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class ReservaTrasient implements Serializable{


	private int numeroPersona;
	private String fecha;
	private String hora;
	private String cedula;
	private String nombreRestaurante;
	
	public ReservaTrasient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservaTrasient(int numeroPersona, String fecha, String hora, String cedula, String nombreRestaurante) {
		super();
		this.numeroPersona = numeroPersona;
		this.fecha = fecha;
		this.hora = hora;
		this.cedula = cedula;
		this.nombreRestaurante = nombreRestaurante;
	}

	public int getNumeroPersona() {
		return numeroPersona;
	}

	public void setNumeroPersona(int numeroPersona) {
		this.numeroPersona = numeroPersona;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreRestaurante() {
		return nombreRestaurante;
	}

	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	
	
}
