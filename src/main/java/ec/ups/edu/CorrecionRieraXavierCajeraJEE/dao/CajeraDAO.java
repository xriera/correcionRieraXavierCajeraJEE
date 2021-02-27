package ec.ups.edu.CorrecionRieraXavierCajeraJEE.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cliente;
import ec.ups.edu.CorrecionRieraXavierCajeraJEE.modelo.Cuenta;


@Stateless
public class CajeraDAO {
	
	@PersistenceContext
    private EntityManager em;

    
    public void insertCliente(Cliente cliente) throws Exception {

        em.persist(cliente);

    }
    public void insertCuenta(Cuenta cuenta) throws Exception {

        em.persist(cuenta);

    }

//        return em.find(Cuenta.class, id);
//    }

    public void recarga(String cedula, double saldo, String idCuenta) throws Exception {

        String jpql = "UPDATE Cliente p SET p.saldo =p.saldo+ " + saldo + " WHERE cedula='" + cedula + "'";

        Query query = em.createQuery(jpql);
        query.executeUpdate();
        
        actualizarSaldoCuenta(idCuenta, saldo);

    }

    public Cuenta buscarCuenta(String idCuenta) throws Exception {
        Cuenta c = null;
        try {
            String jpql = "SELECT p FROM Cuenta p " + "WHERE p.idCuenta LIKE :idCuenta";
          //  String jpql = "SELECT p FROM Cuenta p "
    //    + "WHERE p.idCuenta LIKE :idCuenta OR p.cliente LIKE :idCuenta";
            
            TypedQuery<Cuenta> query = em.createQuery(jpql, Cuenta.class);
            query.setParameter("idCuenta", idCuenta);

            c = query.getSingleResult();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return c;
    }
    
    
    
     public Cliente buscarCliente(String cedula) throws Exception {
        Cliente c = null;
        try {
            String jpql = "SELECT p FROM Cliente p " + "WHERE p.cedula LIKE :cedula";
          //  String jpql = "SELECT p FROM Cuenta p "
    //    + "WHERE p.idCuenta LIKE :idCuenta OR p.cliente LIKE :idCuenta";
            
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("cedula", cedula);

            c = query.getSingleResult();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return c;
    }
    

    public void actualizarSaldoCuenta(String idCuenta, double abono) throws Exception {

        String jpql = "UPDATE Cuenta p SET p.saldo = p.saldo-" + abono + " WHERE idCuenta='" + idCuenta + "'";

        Query query = em.createQuery(jpql);
        query.executeUpdate();

    }

    public List<Cuenta> listaCuentas() throws Exception {
        String jpql = "SELECT p FROM Cuenta p";

        Query q = em.createQuery(jpql, Cuenta.class);
        // q.setParameter("filtro", filtro + "%");
        return q.getResultList();
    }
    
    public static long numbGen() {
       while (true) {
           long numb = (long)(Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
           if (String.valueOf(numb).length() == 12)
               return numb;
       }
   }
}