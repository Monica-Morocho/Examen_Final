package ec.edu.ups.entidad;

import java.io.Serializable;
import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Clientes implements  Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonbProperty
    private int idCliente;
	
	@JsonbProperty
    private String nombre;
	@JsonbProperty
    private String cedula;
	@JsonbProperty
    private String correo;
	@JsonbProperty
    private String direccion;
	@JsonbProperty
    private String telefono;
	
	@JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private ArrayList<Reservas> reservas_list;

	
	public Clientes() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Clientes(int idCliente, String nombre, String cedula, String correo, String direccion, String telefono) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.cedula = cedula;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public ArrayList<Reservas> getReservas_list() {
		return reservas_list;
	}


	public void setReservas_list(ArrayList<Reservas> reservas_list) {
		this.reservas_list = reservas_list;
	}
	
	
}
