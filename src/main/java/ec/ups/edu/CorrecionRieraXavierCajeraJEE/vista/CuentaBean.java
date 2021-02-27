package ec.ups.edu.CorrecionRieraXavierCajeraJEE.vista;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cliente;
import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cuenta;
import ec.ups.edu.CorrecionRieraXavierCajeraJEE.negocio.CajeroON;

@ManagedBean
@ViewScoped
public class CuentaBean {

	@Inject
	private CajeroON on;
	private String cedula;
	private Cuenta newCuenta;
	private Cliente newCliente;
	private double saldo;
	private List<Cuenta>  listaCuenta;
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Cuenta getNewCuenta() {
		return newCuenta;
	}
	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}
	public Cliente getNewCliente() {
		return newCliente;
	}
	public void setNewCliente(Cliente newCliente) {
		this.newCliente = newCliente;
	}	
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public List<Cuenta> getListaCuenta() {
		return listaCuenta;
	}
	public void setListaCuenta(List<Cuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}
	@PostConstruct
	public void init() {
		newCliente = new Cliente();
		newCuenta = new Cuenta();
		listarCuentas();
	}
	public String guardarCuenta() {
		String aleatorio = String.valueOf(numbGen());
		try {
			on.insertCliente(newCliente);
			newCuenta.setCliente(newCliente);
			newCuenta.setIdCuenta(aleatorio);
			newCuenta.setSaldo(saldo);
			on.insertCuenta(newCuenta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "listado-cuentas.xhtml";
	}
	public void listarCuentas() {
		try {
			listaCuenta=on.listaCuentas();
			System.out.println("lista cuentas !!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error lsita cuentas !!!");
		}
	}
	   public String redirigeSolicitudRecarga(String idCuenta) {

	        System.out.println("Redirigir:" + idCuenta);
	       // idCuentaPoliza = idCuenta;
	                
	        return "Poliza?faces-redirect=true&idCuenta=" + idCuenta;
	        
	    }
	
	private String solicitudRecarga(String cedula) {
		 try {
			newCliente = on.buscarCliente(cedula);
			on.recarga(newCliente.getCedula(), saldo, newCliente.getCuenta().getIdCuenta());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return"";
	}
	
	
	private long numbGen() {
        while (true) {
            long numb = (long)(Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
            if (String.valueOf(numb).length() == 12)
                return numb;
            }
	}
}
