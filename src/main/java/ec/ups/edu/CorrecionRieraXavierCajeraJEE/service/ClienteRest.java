package ec.ups.edu.CorrecionRieraXavierCajeraJEE.service;

import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cliente;
import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cuenta;
import ec.ups.edu.CorrecionRieraXavierCajeraJEE.negocio.CajeroON;


@Path("clientes")
public class ClienteRest {

    @Inject
    private CajeroON on;

    @POST
    @Path("/recarga")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta crearProducto2(Parametos p) throws IOException, Exception {
    	Random r = new Random();
    	int valorDado = r.nextInt(6)+1; 
    	
    	Respuesta resp = new Respuesta();

        Cuenta c = on.buscarCuenta(p.getIdCuenta());
        System.out.println("Cuenta ID " + c.getIdCuenta());

        if (c.getSaldo() <= p.getSaldo()) {
            resp.setCodigo(2);
            resp.setMensaje("Registro No satisfactorio no se Generara La Factura");
        } else {
        		if (valorDado==2 || valorDado==4 || valorDado==0) {
        			  Cliente cli = on.buscarCliente(c.getCliente().getCedula());
        	            System.out.println("Cliente" + cli.getCedula() + cli.getNombre());

        	            on.recarga(cli.getCedula(), p.getSaldo(), c.getIdCuenta());
        	            System.out.println("aleatorio: "+valorDado);
        	            resp.setCodigo(1);
        	            resp.setMensaje("Recarga satisfactorio");
				} else {
					 resp.setCodigo(2);
			            resp.setMensaje("Su recarga fue reclazada");
				}
          
        }

       
        return resp;
    }
}
