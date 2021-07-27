package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Clientes;

@Stateless
public class ClienteFacade extends AbstractFacade<Clientes>{

	@PersistenceContext(unitName = "Prueba")
    private EntityManager entityManager; 
	
	public ClienteFacade() {
		super(Clientes.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	public Clientes buscarCliente (String cedula) {
		try {
			String jpql = "SELECT cli FROM Clientes cli WHERE cli.cedula='" + cedula + "'";
			Clientes cliente = (Clientes) entityManager.createQuery(jpql).getSingleResult();
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
