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
}
