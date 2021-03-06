package ec.edu.ups.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Reservas;

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
		String jpql = "SELECT res, clie, rest FROM reservas res, clientes cli, restaurantes rest WHERE res.id_cliente='" + idcliente + "'";
		List<Reservas> reservas = entityManager.createQuery(jpql).getResultList();
		return reservas;
	}

	public List<Reservas> listarReservasRestaurante(int idrestaurante){
		String jpql = "SELECT res, clie, rest FROM reservas res, clientes cli, restaurantes rest WHERE res.id_resaurante='" + idrestaurante + "'";
		List<Reservas> reservas = entityManager.createQuery(jpql).getResultList();
		return reservas;
	}
}
