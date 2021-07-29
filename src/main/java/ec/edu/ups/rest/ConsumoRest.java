package ec.edu.ups.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.annotation.RequestParameterMap;
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

	private Clientes cliente;
	private Reservas reserva;
	private Restaurantes restauran;

	
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
			System.out.println("Error al crear el Cliente");
			return Response.status(500).build();
		}
		return Response.ok("Cliente creado correctamente: "+ jsonCliente).build();
		
	}
	
	@POST
	@Path("/registro/restauran")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response crearRestauran(@FormParam("nombre") String nombre,  
			                       @FormParam("direccion") String direccion ,
			                       @FormParam("telefono") String telefono, 
			                       @FormParam("maxAforo") int maxAforo) throws IOException{
		restauran = new Restaurantes();
		restauran.setNombre(nombre);
		restauran.setDireccion(direccion);
		restauran.setTelefono(telefono);
		restauran.setMaxAforo(maxAforo);
		
		try {
			ejbRestauranteFacade.create(restauran);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear el Restaurante");
			return Response.status(500).build();
		}
		return Response.ok("Restaurante creado correctamente: "+restauran).build();
		
	}
	
	@POST
	@Path("/registro/reservas")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response crearReservas(@FormParam("numeroP") int numeroP,  
			                      @FormParam("fecha") String fecha ,
			                      @FormParam("hora") String hora, 
			                      @FormParam("cedula") String cedula,
			                      @FormParam("nombreRestaurante") String nombreRestaurante) throws IOException{
		
		cliente = new Clientes();
		cliente = ejbClienteFacade.buscarCliente(cedula);
		
		if(cliente != null) {
			try {
			reserva = new Reservas();
			reserva.setNumeroP(numeroP);
			reserva.setFecha(fecha);
			reserva.setHora(hora);
			
			cliente = ejbClienteFacade.buscarCliente(cedula);
			reserva.setCliente(cliente);
			
			restauran = new Restaurantes();
			restauran = ejbRestauranteFacade.buscarRestaurante(nombreRestaurante);
			
			if(numeroP <= restauran.getMaxAforo()) {
				reserva.setRestaurante(restauran);
				ejbReservaFacade.create(reserva);
				return Response.ok("Reserva creada correctamente: "+reserva).build();
			}else{
	        	return Response.ok("EL Restaurante no cuenta con espacio suficiente: "+restauran).build();
	        }
            
            }catch (Exception e){
               e.printStackTrace();
               return Response.status(500).build();
            }
        }else{
        	return Response.ok("EL numero de cedula no existe: "+reserva).build();
        }
	}
	
	@GET
	@Path("/listar/reserva/cliente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIdPed(@QueryParam("cedula") String cedula) {

		Jsonb jsonb = JsonbBuilder.create();
		cliente = new    Clientes();
		Clientes clientes = ejbClienteFacade.buscarCliente(cedula);
		
		List<Reservas> list = new ArrayList<>();
		list.clear();
		List<Reservas> reserva = new ArrayList<Reservas>();
		reserva.clear();
		
		list = ejbReservaFacade.findAll();
		
		
		for (int i = 0; i < list.size(); i++) {			
			if (list.get(i).getCliente().getCedula()==cedula) {
				reserva.add(list.get(i));
			}
		}
		
		return Response.ok(jsonb.toJson(reserva)).build();
		
	}
	
	@GET
	@Path("/listar/reserva/restaurante")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarReservasRestauran(@QueryParam("nombre") String nombre) {
		Jsonb jsonb = JsonbBuilder.create();
		cliente = new    Clientes();
		Restaurantes restaurante = ejbRestauranteFacade.buscarRestaurante(nombre);
		
		List<Reservas> list = new ArrayList<>();
		list.clear();
		List<Reservas> reserva = new ArrayList<Reservas>();
		reserva.clear();
		
		list = ejbReservaFacade.findAll();
		
		
		for (int i = 0; i < list.size(); i++) {			
			if (list.get(i).getRestaurante().getNombre()==nombre) {
				reserva.add(list.get(i));
			}
		}
		
		return Response.ok(jsonb.toJson(reserva)).build();
	}
}	
