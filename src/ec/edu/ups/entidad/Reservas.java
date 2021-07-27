package ec.edu.ups.entidad;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservas implements  Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonbProperty
    private int idReserva;
	
	@JsonbProperty
    private int numeroP;
	@JsonbProperty
    private String fecha;
	@JsonbProperty
    private String hora;
	
	@JsonbTransient
    @ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Clientes cliente;
	
	@JsonbTransient
    @ManyToOne
	@JoinColumn(name = "ID_RESTAURANTE", nullable = false)
	private Restaurantes restaurante;

	public Reservas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservas(int idReserva, int numeroP, String fecha, String hora, Clientes cliente, Restaurantes restaurante) {
		super();
		this.idReserva = idReserva;
		this.numeroP = numeroP;
		this.fecha = fecha;
		this.hora = hora;
		this.cliente = cliente;
		this.restaurante = restaurante;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getNumeroP() {
		return numeroP;
	}

	public void setNumeroP(int numeroP) {
		this.numeroP = numeroP;
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

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Restaurantes getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurantes restaurante) {
		this.restaurante = restaurante;
	}
	
	
}
