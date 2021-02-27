package ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cuenta {
	
	@Id
	private String idCuenta;
	private double saldo;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="cedula")
	private Cliente cliente;


	public String getIdCuenta() {
		return idCuenta;
	}


	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
