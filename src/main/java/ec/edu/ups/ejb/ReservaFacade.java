package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Clientes;
import ec.edu.ups.entidad.Reservas;

@Stateless
public class ReservaFacade extends AbstractFacade<Reservas>{

	@PersistenceContext(unitName = "Prueba")
    private EntityManager entityManager; 
	
	public ReservaFacade() {
		super(Reservas.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	public List<Reservas> listarReservasCliente(int idcliente){
		String jpql = "select cl, rest, res from Clientes cl, Restaurantes rest, Reservas res WHERE res.cliente.idCliente="+idcliente+" and res.cliente.idCliente=cl.idCliente and res.restaurante.idRestauran=rest.idRestauran";
		List<Reservas> reserva = entityManager.createQuery(jpql).getResultList();
		return reserva;
	}

	public List<Reservas> listarReservasRestaurante(int idrestaurante){
		String jpql = "select cl, rest, res from Clientes cl, Restaurantes rest, Reservas res WHERE res.restaurante.idRestauran="+idrestaurante+" and res.restaurante.idRestauran=rest.idRestauran and res.cliente.idCliente=cl.idCliente";
		List<Reservas> reservas = entityManager.createQuery(jpql).getResultList();
		return reservas;
	}

}
