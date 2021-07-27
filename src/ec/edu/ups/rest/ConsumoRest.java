package ec.edu.ups.rest;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidad.Clientes;
import ec.edu.ups.entidad.Reservas;
import ec.edu.ups.entidad.Restaurantes;

@Path("/examen/")
public class ConsumoRest {
	
	@EJB
	private ClienteFacade ejbClienteFacade;

	private Clientes cliente;
	private Reservas reserva;
	private Restaurantes restaurante;

	@POST
	@Path("/registro/cliente")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response crearEmpleado(@FormParam("nombre") String nombre, 
			                       @FormParam("cedula") String cedula,
			                       @FormParam("correo") String correo, 
			                       @FormParam("direccion") String direccion ,
			                       @FormParam("telefono") String telefono ) throws IOException{
		Clientes cliente = new Clientes();
		cliente.setNombre(nombre);
		cliente.setCedula(cedula);
		cliente.setCorreo(correo);
		cliente.setDireccion(direccion);
		cliente.setTelefono(telefono);
		
		try {
			ejbClienteFacade.create(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear el Cliente");
			return Response.status(500).build();
		}
		return Response.ok("Cliente creado correctamente: "+cliente).build();
		
	}
}
