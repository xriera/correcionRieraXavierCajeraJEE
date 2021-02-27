package ec.ups.edu.CorrecionRieraXavierCajeraJEE.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import ec.ups.edu.CorrecionRieraXavierCajeraJEE.dao.CajeraDAO;
import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cliente;
import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cuenta;

@Stateless
public class CajeroON {
	
	
	@Inject
	private CajeraDAO daoCajera;
	
	 public void insertCuenta(Cuenta cuenta) throws Exception {
	      daoCajera.insertCuenta(cuenta);
	    }
	 public void insertCliente(Cliente cliente) throws Exception {
	       daoCajera.insertCliente(cliente);
	    }
		public void recarga(String cedula, double saldo, String idCuenta) throws Exception {

			daoCajera.recarga(cedula, saldo, idCuenta);

		}
		 public Cliente buscarCliente(String cedula) throws Exception {
			 return daoCajera.buscarCliente(cedula);
		 }
		 public Cuenta buscarCuenta(String idcuenta) throws Exception {
			 return daoCajera.buscarCuenta(idcuenta);
		 }
		 public void ActualizarSaldo(String idCuenta, double abono) throws Exception {
			 daoCajera.actualizarSaldoCuenta(idCuenta, abono);
		 }
		 public List<Cuenta> listaCuentas() throws Exception {
			 return daoCajera.listaCuentas();
		 }
}
