/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.rest.ClienteRESTClient;

import gestiondeproyectos.ui.controller.ClienteBean;
import gestiondeproyectos.ui.controller.FacturaBean;
import gestiondeproyectos.ui.controller.PersonaDeContactoBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.ws.rs.core.GenericType;

/**
 * This class implements the bussiness logic interface by returning data obtained
 * from the REST service for the Cliente
 * @author Miguel Axier Lafuente Peñas
 */
public class ClienteManagerImplementation implements ClientesManager{
    //REST Cliente web client
    private ClienteRESTClient webClient;
    //private PersonaDeContactoRESTClient webContacto;
   
    private List<ClienteBean> clientes;
    //Logger
    private static final Logger LOGGER = Logger.getLogger("javafxclientside");
    
    public ClienteManagerImplementation(){
        webClient=new ClienteRESTClient();
        //webContacto=new PersonaDeContactoRESTClient();
        clientes = new ArrayList<>();
        LOGGER.info("ClienteManagerImplementation:Finding all customer from REST service (XML).");
        clientes = webClient.getAllClientes_XML(new GenericType<List<ClienteBean>>(){});
        
    }
    
    @Override
    public Collection<ClienteBean> getAllClientes() {    
        return clientes;
    }
    
    
    @Override
    public Collection buscarClientes(Boolean pendiente, String email, String nif,
                                                List<FacturaBean> facturasPendientes) {
        Collection clientesFiltro = clientes;
        List<ClienteBean> clientesFiltroAux;
        ArrayList <ClienteBean> clientesMorosos = new ArrayList<>();
       
            if(pendiente){
                clientes.forEach((cliente) -> {
                    facturasPendientes.stream().filter((facturaPendiente) -> (facturaPendiente.getNif().compareTo(cliente.getNif())==0)).forEachOrdered((_item) -> {
                        if(!clientesMorosos.contains(cliente)){
                            clientesMorosos.add(cliente);
                        }
                        
                    });
                });
                clientesFiltro = clientesMorosos;
            }
            if(email.length()!=0){
                LOGGER.info("ClienteManagerImplementation:Finding customer by e-mail.");
                clientesFiltroAux =(ArrayList<ClienteBean>) clientesFiltro; 
                clientesFiltro=clientesFiltroAux.stream().filter(c -> c.getEmail().contains(email))
                                    .map(c -> c).collect(Collectors.toList());

            } 
            if(nif.length()!=0){
                LOGGER.info("ClienteManagerImplementation:Finding customer by nif.");
                clientesFiltroAux=(ArrayList<ClienteBean>) clientesFiltro;
                clientesFiltro=clientesFiltroAux.stream().filter(c -> c.getNif().contains(nif))
                                    .map(c -> c).collect(Collectors.toList());
            }
            
            return clientesFiltro;
    }

    @Override
    public void agnadirCliente(ClienteBean cliente) {
        LOGGER.log(Level.INFO,"ClienteManagerImplementation: creating customer {0},",cliente.getNombre());
        webClient.createCliente_XML(cliente);
        //webContacto.createPersonaDeContacto_XML(cliente.getContacto());
        //agnadirContacto(cliente.getContacto());
        clientes.add(cliente);
    }

    @Override
    public void eliminarCliente(ClienteBean cliente) {
        LOGGER.log(Level.INFO,"ClienteManagerImplementation: deleting customer {0},",cliente.getNombre());
        webClient.deleteCliente(cliente.getNif());
        clientes.remove(cliente);
    }

    @Override
    public boolean emailValido(String email) {
        String patternEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(patternEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean clienteExiste(String nif) {
        return clientes.stream().filter(c -> c.getNif().equals(nif)).count()!=0;
    }

    @Override
    public void modificarCliente(ClienteBean cliente, String nif) {
        LOGGER.log(Level.INFO,"ClienteManagerImplementation: Updating customer {0}.",cliente.getNombre());
        webClient.updateCliente_XML(cliente);
        clientes.set(clientes.indexOf(cliente), cliente);
    }

    @Override
    public void agnadirContacto(PersonaDeContactoBean contacto) {
        
    }

    @Override
    public void modificarContacto(PersonaDeContactoBean contacto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarContacto(PersonaDeContactoBean contacto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}
