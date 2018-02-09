/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.rest.ClienteRESTClient;
import gestiondeproyectos.ui.controller.ClienteBean;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

/**
 * This class implements the bussiness logic interface by returning data obtained
 * from the REST service for the Cliente
 * @author Miguel Axier Lafuente Peñas
 */
public class ClienteManagerImplementation implements ClientesManager{
    //REST Cliente web client
    private ClienteRESTClient webClient;
    //Logger
    private static final Logger LOGGER = Logger.getLogger("javafxclientside");
    
    public ClienteManagerImplementation(){
        webClient=new ClienteRESTClient();
    }
    
    @Override
    public Collection<ClienteBean> getAllClientes() {
        LOGGER.info("ClienteManager:Finding all customer from REST service (XML).");
        List<ClienteBean> clientes = webClient.getAllClientes_XML(new GenericType<List<ClienteBean>>(){});
        return clientes;
    }

    @Override
    public Collection buscarClientes(Boolean pendiente, String email, String nif) {
//        Collection<ClienteBean> clientesFiltro = getAllClientes();
//        Collection<ClienteBean> clientesFiltroAux;
//        if(pendiente){
//            //TODO
//        }
//        if(email.lengh()!=0){
//            
//        }
    }

    @Override
    public void agnadirCliente(ClienteBean cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarCliente(ClienteBean cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean emailValido(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean clienteExiste(String nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarCliente(ClienteBean cliente, String nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
