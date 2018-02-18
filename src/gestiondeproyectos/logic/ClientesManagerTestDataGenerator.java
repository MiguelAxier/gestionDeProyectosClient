//
//package gestiondeproyectos.logic;
//
//import gestiondeproyectos.ui.controller.ClienteBean;
//import gestiondeproyectos.ui.controller.PersonaDeContactoBean;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//
///**
// * Clase que simula el contenido y acceso a una base de datos.
// * @author Miguel Axier Lafuente Peñas
// */
//public class ClientesManagerTestDataGenerator{
//    private final ArrayList<ClienteBean> clientes;
//    /**
//     * Constructor de la clase que genera una ArrayList con 10 clientes
//     */
//    public ClientesManagerTestDataGenerator (){
//        clientes = null;
////        clientes = new ArrayList();
////        for(int i=0;i<10;i++){
////            //Los clientes que tienen como indice un numero par son morosos.
////            if(i%2==0){
////               clientes.add(new ClienteBean("MorosoNIF000"+i,"Miguel"+i,"Euskal Herria,18 "+i+"ªD"
////                        ,65972040+i,"miguel"+i+"@gmail.es","WEB"+i,"Federico"+1
////                        ,63258917+i,"federico"+i+"@gmail.com"));
////            } else {
////                clientes.add(new ClienteBean("NIF000"+i,"Miguel"+i,"Euskal Herria,18"+i+"ªD"
////                        ,65972040+i,"miguel"+i+"@gmail.es","WEB"+i,"Federico"+1
////                        ,63258917+i,"federico"+i+"@gmail.com"));
////            }
////        }
//    }
//    
//    /**
//     * Devuelve toda la colección de clientes
//     * @return Colleción de clientes
//     */
//    @Override
//    public Collection getAllClientes() {
//  
//       return clientes;
//    }
//    
//    /**Añade un nuevo cliente
//     * @param cliente cliente para añadir
//     */
//    @Override
//    public void agnadirCliente(ClienteBean cliente) {
//        clientes.add(cliente);
//    }
//
//    /**
//     * Metodo que elimina un cliente en concreto
//     * @param cliente cliente a eliminar
//     */
//    @Override
//    public void eliminarCliente(ClienteBean cliente) {
//        clientes.remove(cliente);
//        
//    }
//    /**
//     * Metodo que modifica un cliente en concreto.
//     * @param cliente Nuevo cliente
//     * @param nif Cliente ha modificar
//     */
//      @Override
//    public void modificarCliente(ClienteBean cliente, String nif) {
//        for(int i=0;i<clientes.size();i++){
//            if(clientes.get(i).getNif().equals(nif)){
//                clientes.set(i, cliente);
//            }
//        }
//        
//    }
//
//    /**
//     * Metodo que filtra los clientes segun los criterios de busqueda.
//     * @param pendiente booleano. True para buscar clientes con facturas pendientes.
//     * @param email String. 
//     * @param nif String.
//     * @return Colección de clientes que cumplen con los criterios de busqueda
//     */
//    @Override
//    public Collection buscarClientes(Boolean pendiente, String email, String nif) {
//        Collection clientesFiltro = clientes;
//        ArrayList<ClienteBean> clientesFiltroAux;
//        if(pendiente){
//            clientesFiltro = clientes.stream()
//                            .filter(c -> c.getNif().substring(0, 1).equals("M"))
//                            .map(c -> c).collect(Collectors.toList());
//        }
//        if(email.length()!=0){
//            clientesFiltroAux =(ArrayList<ClienteBean>) clientesFiltro; 
//            clientesFiltro=clientesFiltroAux.stream().filter(c -> c.getEmail().contains(email))
//                                .map(c -> c).collect(Collectors.toList());
//                
//        } 
//        if(nif.length()!=0){
//            clientesFiltroAux=(ArrayList<ClienteBean>) clientesFiltro;
//            clientesFiltro=clientesFiltroAux.stream().filter(c -> c.getNif().contains(nif))
//                                .map(c -> c).collect(Collectors.toList());
//            
//        }
//    return clientesFiltro;
//    }
//    /**
//     * Meodo que comprueba si eciste un cliente.
//     * @param nif String. Cliente a buscar
//     * @return True si existe un cliente y false en caso contrario
//     */
//    @Override
//    public boolean clienteExiste(String nif) {
//        return clientes.stream().filter(c -> c.getNif().equals(nif)).count()!=0;
//    }
//    /**
//     * Metodo que confirma si el formato del email es correcto
//     * @param email String
//     * @return boolean. True si el email es correcto
//     */
//    @Override
//    public boolean emailValido(String email) {
//        String patternEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        Pattern pattern = Pattern.compile(patternEmail);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    @Override
//    public void agnadirContacto(PersonaDeContactoBean contacto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void modificarContacto(PersonaDeContactoBean contacto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void eliminarContacto(PersonaDeContactoBean contacto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Collection<ClienteBean> getClientesMorosos() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//}
