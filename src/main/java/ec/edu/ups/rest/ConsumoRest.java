package ec.edu.ups.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	
	@EJB
	private ReservaFacade ejbReservaFacade;
	
	@EJB
	private RestauranteFacade ejbRestauranteFacade;

	
	@POST
	@Path("/registro/cliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response crearEmpleado(String jsonCliente) throws IOException{
		Jsonb jsonb = JsonbBuilder.create();
		Clientes cliente = jsonb.fromJson(jsonCliente, Clientes.class);
		try {
			ejbClienteFacade.create(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.ok("Cliente creado correctamente").build();
		
	}
	
	@POST
	@Path("/registro/restauran")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response crearRestauran(String jsonRestaurante) throws IOException{
		Jsonb jsonb = JsonbBuilder.create();
		Restaurantes restaurante = jsonb.fromJson(jsonRestaurante, Restaurantes.class);
		
		try {
			ejbRestauranteFacade.create(restaurante);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear el Restaurante");
			return Response.status(500).build();
		}
		return Response.ok("Restaurante creado correctamente: "+restaurante).build();
		
	}
	
	@POST
	@Path("/registro/reservas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response crearReservas(String jsonReserva) throws IOException{
		Jsonb jsonb = JsonbBuilder.create();
		ReservaTrasient reservaTra = jsonb.fromJson(jsonReserva, ReservaTrasient.class);
		
		Clientes cliente = ejbClienteFacade.buscarCliente(reservaTra.getCedula());
		if(cliente != null) {
			try {
			Reservas reserva = new Reservas();
			reserva.setNumeroP(reservaTra.getNumeroPersona());
			reserva.setFecha(reservaTra.getFecha());
			reserva.setHora(reservaTra.getHora());
			reserva.setCliente(cliente);
		
			Restaurantes restauran = ejbRestauranteFacade.buscarRestaurante(reservaTra.getNombreRestaurante());
			reserva.setRestaurante(restauran);
			ejbReservaFacade.create(reserva);
			return Response.ok("Reserva creada correctamente").build();
            
            }catch (Exception e){
               e.printStackTrace();
               return Response.status(500).build();
            }
        }else{
        	return Response.ok("EL numero de cedula no existe").build();
        }
	}
	
	@GET
	@Path("/listar/reserva/cliente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarReservasCliente(@QueryParam("cedula") String cedula) {
		Jsonb jsonb = JsonbBuilder.create();
		Clientes cliente = ejbClienteFacade.buscarCliente(cedula);
		List<Reservas> list = ejbReservaFacade.listarReservasCliente(cliente.getIdCliente());
		return Response.ok(jsonb.toJson(list)).build();
	}
	
	@GET
	@Path("/listar/reserva/restaurante")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarReservasRestauran(@QueryParam("nombre") String nombre) {
		Jsonb jsonb = JsonbBuilder.create();
		Restaurantes restaurante = ejbRestauranteFacade.buscarRestaurante(nombre);
		List<Reservas> list = ejbReservaFacade.listarReservasRestaurante(restaurante.getIdRestauran());
		return Response.ok(jsonb.toJson(list)).build();
	}
}	
