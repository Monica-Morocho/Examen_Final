package ec.edu.ups.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Restaurantes;


public class RestauranteFacade extends AbstractFacade<Restaurantes>{

	@PersistenceContext(unitName = "Prueba")
    private EntityManager entityManager; 
	
	public RestauranteFacade() {
		super(Restaurantes.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	public Restaurantes buscarRestaurante (String nombre) {
		try {
			String jpql = "SELECT rest FROM Restaurantes rest WHERE rest.nombre='" + nombre + "'";
			Restaurantes restaurantes = (Restaurantes) entityManager.createQuery(jpql).getSingleResult();
			return restaurantes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
