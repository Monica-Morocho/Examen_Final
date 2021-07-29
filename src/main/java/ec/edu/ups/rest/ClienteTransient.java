package ec.edu.ups.rest;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class ClienteTransient implements Serializable{
	
	private String cedula;

	public ClienteTransient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteTransient(String cedula) {
		super();
		this.cedula = cedula;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	

}
