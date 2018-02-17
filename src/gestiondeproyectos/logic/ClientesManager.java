
package gestiondeproyectos.logic;

import gestiondeproyectos.ui.controller.ClienteBean;
import gestiondeproyectos.ui.controller.PersonaDeContactoBean;
import java.util.Collection;

/**
 * Interfaz que conecta la capa de logica con la ui
 * @author Miguel Axier Lafuent Peñas
 */
public interface ClientesManager {
    /**
     * Metodo que devuelve toda la colección de clientes
     * @return Coleccion de clientes
     */
    public Collection getAllClientes();
   
    /**
     * Metodo que realiza una busqueda de clientes segun los parametros indicados
     * @param pendiente Boolean True para clientes con facturas pendientes
     * @param email String
     * @param nif String
     * @return colección de clientes
     */
    public Collection buscarClientes(Boolean pendiente,String email,String nif);
 
    /**
     * Metodo que añade un cliente.
     * @param cliente ClienteBean
     */
    public void agnadirCliente(ClienteBean cliente);
    /**
     * Metodo que elimina un cliene
     * @param cliente ClienteBean
     */
    public void eliminarCliente(ClienteBean cliente);
    /**
     * Metodo que comprueba si el email e correcto
     * @param email
     * @return boolean. True si el email e correct y false si no lo es
     */
    public boolean emailValido(String email);
    /**
     * Metodo que comprueba si un cliente existe
     * @param nif String
     * @return boolean. True si existe el cliente
     */
    public boolean clienteExiste(String nif);
    /**
     * Metodo que modifica un cliente en concreto.
     * @param cliente ClienteBean. Nuevo cliente
     * @param nif String. Nif del antiguo cliente
     */
    public void modificarCliente(ClienteBean cliente, String nif);
    
    public Collection<ClienteBean> getClientesMorosos();
    public void agnadirContacto(PersonaDeContactoBean contacto);
    public void modificarContacto(PersonaDeContactoBean contacto);
    public void eliminarContacto(PersonaDeContactoBean contacto);
    
}
