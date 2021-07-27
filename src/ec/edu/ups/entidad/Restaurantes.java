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
public class Restaurantes implements  Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonbProperty
    private int idRestauran;
	
	@JsonbProperty
    private String nombre;
	@JsonbProperty
    private String direccion;
	@JsonbProperty
    private String telefono;
	@JsonbProperty
    private int maxAforo;
	
	@JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurante")
    private ArrayList<Reservas> reservas_list;

	public Restaurantes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Restaurantes(int idRestauran, String nombre, String direccion, String telefono, int maxAforo) {
		super();
		this.idRestauran = idRestauran;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.maxAforo = maxAforo;
	}

	public int getIdRestauran() {
		return idRestauran;
	}

	public void setIdRestauran(int idRestauran) {
		this.idRestauran = idRestauran;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public int getMaxAforo() {
		return maxAforo;
	}

	public void setMaxAforo(int maxAforo) {
		this.maxAforo = maxAforo;
	}

	public ArrayList<Reservas> getReservas_list() {
		return reservas_list;
	}

	public void setReservas_list(ArrayList<Reservas> reservas_list) {
		this.reservas_list = reservas_list;
	}
	
	
}
