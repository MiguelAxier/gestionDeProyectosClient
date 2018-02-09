
package gestiondeproyectos.ui.controller;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import java.math.BigInteger;
import javafx.scene.control.MenuItem;
import gestiondeproyectos.logic.ClientesManager;
import gestiondeproyectos.logic.FacturasManager;
import gestiondeproyectos.logic.ProyectosManager;
import javafx.stage.WindowEvent;




/**
 * Clase que controla la ventana clientes
 * @author Miguel Axier Lafuente Peñas
 */
public class VentanaClientesController {
    
    private static final Logger logger =  Logger.getLogger("gestiondeproyectos.ui.controller");
    private ClientesManager clientesManager;
    private ProyectosManager proyectosManager;
    private FacturasManager facturasManager;
    private ObservableList<ClienteBean> clientes;
    
    private Stage stage;

    @FXML
    private Button btnAgnadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnSalir;
    @FXML
    private TextField txtNif;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtWeb;
    @FXML
    private TextField txtNombrePC;
    @FXML
    private TextField txtTelefonoPC;
    @FXML
    private TextField txtEmailPC;
    @FXML
    private CheckBox cbClientesFacturasPendientes;
    @FXML
    private TextField txtBusquedaNIF;
    @FXML
    private TextField txtBusquedaEmail;
    @FXML
    private Menu menuProyectos;
    @FXML
    private Menu menuFacturas;
    @FXML
    private Menu menuServicios;
    @FXML
    private TableView tbClientes;
    @FXML
    private TableColumn tbColumDni;
    @FXML
    private TableColumn tbColumNombre;
    @FXML
    private TableColumn tbColumDireccion; 
    @FXML
    private TableColumn tbColumTelefono;
    @FXML
    private TableColumn tbColumEmail; 
    @FXML
    private TableColumn tbColumWeb; 
    
    /**
     * Metodo que establece el escenario
     * @param stage Stage
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }
    /**
     * Metono que establece los managers
     * @param facturasManager manager que controla las facturas
     * @param proyectosManager manager que controla los proyectos
     * @param clientesManager manager que controla los clientes
     */
    public void setManager(FacturasManager facturasManager,
            ProyectosManager proyectosManager,ClientesManager clientesManager){
        this.facturasManager = facturasManager;
        this.clientesManager = clientesManager;
        this.proyectosManager = proyectosManager;
    }
    /**
     * Metodo que inicia la ventana
     * @param root Parent
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Clientes");
            stage.setResizable(false);
            //handleStageShowing();
            stage.setOnShowing(this::handleWindowShowing);
            stage.show();
   
    }
    /**
     * Metodo que controla el evento que se lanza mientras se muestra la ventana
     * 
     * @param e evento de ventana
     */
    public void handleWindowShowing(WindowEvent e){
        logger.info("En el evento windows Showing");
        tbColumDni.setCellValueFactory(new PropertyValueFactory<>("nif"));
        tbColumNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbColumDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tbColumTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbColumEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbColumWeb.setCellValueFactory(new PropertyValueFactory<>("web"));
           
        clientes =FXCollections.observableArrayList(clientesManager.getAllClientes());
        tbClientes.setItems(clientes);
        tbClientes.getSelectionModel().selectedItemProperty()
                .addListener(this::handleTablaClientesSelectionChanged);
        logger.info("Se carga la tabla correctamente");
        txtNif.focusedProperty().addListener(this::focusChanged);
        txtNombre.focusedProperty().addListener(this::focusChanged);
        txtDireccion.focusedProperty().addListener(this::focusChanged);
        txtTelefono.focusedProperty().addListener(this::focusChanged);
        txtEmail.focusedProperty().addListener(this::focusChanged);
        txtWeb.focusedProperty().addListener(this::focusChanged);
        txtNombrePC.focusedProperty().addListener(this::focusChanged);
        txtTelefonoPC.focusedProperty().addListener(this::focusChanged);
        txtEmailPC.focusedProperty().addListener(this::focusChanged);
       
        MenuItem menuItem = new MenuItem();
        menuFacturas.getItems().add(menuItem);
        MenuItem menuItem2 = new MenuItem();
        menuServicios.getItems().add(menuItem2);
        MenuItem menuItem3=new MenuItem();
        menuProyectos.getItems().add(menuItem3);
        
     
 
    }
    /**
     * Metodo que controla el cambio de selección de la tabla
     * @param observableValue ObservableValue
     * @param oldValue Object
     * @param newValue Object
     */  
    public void handleTablaClientesSelectionChanged (ObservableValue observableValue,
                                                     Object oldValue,
                                                     Object newValue){
        if(newValue!=null){
            ClienteBean cliente=(ClienteBean)newValue;
                txtNif.setText(cliente.getNif());
                txtDireccion.setText(cliente.getDireccion());
                txtEmail.setText(cliente.getEmail());
                txtNombre.setText(cliente.getNombre());
                txtTelefono.setText(cliente.getTelefono().toString());
                txtWeb.setText(cliente.getWeb());
                txtNombrePC.setText(cliente.getContacto().getNombre());
                txtTelefonoPC.setText(cliente.getContacto().getTelefono().toString());
                txtEmailPC.setText(cliente.getContacto().getEmail());


                btnAgnadir.setDisable(true);
                btnModificar.setDisable(false);
                btnEliminar.setDisable(false);
        } else {
                btnAgnadir.setDisable(false);
                btnModificar.setDisable(true);
                btnEliminar.setDisable(true);
                limpiarCampos();
            
            
        }
        
        
    }
    /**
     * Metodo que controla el evento que lanza el botón añadir. Añade un nuevo cliente.
     * @param e ActionEvent
     */
    @FXML
    public void handleOnActionAgnadir (ActionEvent e){
        logger.info("En el evento del boton añadir");
           if(txtNif.getText().trim().equals("")||txtTelefono.getText().trim().equals("")||
                    txtNombre.getText().trim().equals("")||txtEmail.getText().trim().equals("")||
                    txtDireccion.getText().trim().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR, 
                            "Todos los campos menos la web son obligatorios");
                alert.showAndWait();
                 
            } else {
                if(clientesManager.clienteExiste(txtNif.getText().trim())){
                    logger.info("El cliente añadido ya existe");
                    Alert alert = new Alert(Alert.AlertType.ERROR, 
                        "Ya existe un cliente con ese Nif");
                    alert.showAndWait();
                    txtNif.requestFocus();
                } else {
                    PersonaDeContactoBean contacto = new PersonaDeContactoBean(txtNombrePC.getText(),
                                    txtEmailPC.getText(),
                                    BigInteger.valueOf(Long.parseLong(txtTelefonoPC.getText())));
                    
                    ClienteBean cliente = new ClienteBean(txtNif.getText(), txtNombre.getText()
                                    ,txtDireccion.getText(), Integer.parseInt(txtTelefono.getText())
                                    ,txtEmail.getText(), txtWeb.getText(),contacto);
                    
                clientesManager.agnadirCliente(cliente);
                clientes=FXCollections.observableArrayList(clientesManager.getAllClientes());
                tbClientes.setItems(clientes);
                tbClientes.refresh();
                limpiarCampos(); 
                }

                
            } 
    }
    /**
     * Metodo que controla el evento que lanza el botón de salir. Cierra la ventana.
     * @param e ActionEvent
     * @throws IOException 
     */
    @FXML
    public void handleOnActionSalir (ActionEvent e) throws IOException{
        Platform.exit();
    }
    /**
     * Metodo que controla el evento que lanza el botón de modificar.
     * Modifica el objeto seleccionado de la tabla.
     * @param e ActionEvent
     */
    @FXML
    public void handleOnActionModificar (ActionEvent e){
        logger.info("En el evento del boton modificar");
       if(txtNif.getText().trim().equals("")||txtTelefono.getText().trim().equals("")||
                    txtNombre.getText().trim().equals("")||txtEmail.getText().trim().equals("")||
                    txtDireccion.getText().trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, 
                    "Todos los campos menos la web son obligatorios");
            alert.showAndWait();
        } else {
            ClienteBean clienteSelec=(ClienteBean) tbClientes.getSelectionModel().getSelectedItem();
            String oldNif = clienteSelec.getNif().trim();
            Boolean hacer = false;
            if(txtNif.getText().equals(oldNif)){
                logger.info("No modifica el campo NIF");
                hacer = true;
            } else {
                if(clientesManager.clienteExiste(txtNif.getText().trim())){
                    logger.info("Modifica el campo NIF pero el nuevo NIF ya existe");
                    Alert alert = new Alert(Alert.AlertType.ERROR, 
                        "Ya existe un cliente con ese Nif");
                    alert.showAndWait();
                    txtNif.requestFocus();
                } else {
                    logger.info("Modifica el campo NIF");
                    hacer = true;
                }
            }
            if(hacer){
                clienteSelec.setNombre(txtNombre.getText());
                clienteSelec.setNif(txtNif.getText());
                clienteSelec.setDireccion(txtDireccion.getText());
                clienteSelec.setEmail(txtEmail.getText());
                clienteSelec.setTelefono(BigInteger.valueOf(Long.parseLong(txtTelefono.getText())));
                clienteSelec.setWeb(txtWeb.getText());
                
                clienteSelec.getContacto().setNombre(txtNombrePC.getText());
                clienteSelec.getContacto().setEmail(txtEmailPC.getText());
                clienteSelec.getContacto().setTelefono(BigInteger.valueOf(Long.parseLong(txtTelefonoPC.getText())));

                clientesManager.modificarCliente(clienteSelec,oldNif);
                clientes=FXCollections.observableArrayList(clientesManager.getAllClientes());
                tbClientes.setItems(clientes);
                tbClientes.refresh();
                txtNif.requestFocus();
                btnAgnadir.setDisable(false);
                btnModificar.setDisable(true);
                btnEliminar.setDisable(true);
                limpiarCampos();
            }
        }
    }
    /**
     * Metodo que controla el evento que lanza el botón buscar. Llama al manager 
     * para obtener una lista de clientes que cumplen con los requisitos de busqeda.
     * @param e ActionEvent
     */
    @FXML
    public void handleOnActionBuscar (ActionEvent e){
        logger.info("En el evento del boton buscar");
        boolean check = false;
        if(cbClientesFacturasPendientes.isSelected()){
            check = true;
        }
        clientes = FXCollections.observableArrayList(clientesManager.buscarClientes(check,
                           txtBusquedaEmail.getText().trim(), txtBusquedaNIF.getText().trim()));
        if(clientes.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "No hay clientes que cumplan las condiciones de búsqueda");
                    alert.showAndWait();
        }
        tbClientes.setItems(clientes);
        tbClientes.refresh();
    }
    /**
     * Metodo que controla el evento que lanza el botón eliminar. 
     * Elimina el objeto seleccionado de la tabla.
     * @param e ActionEvent
     */
    @FXML
    public void handleOnActionEliminar (ActionEvent e){
        logger.info("En el evento del boton eliminar");
        Optional<ButtonType> result;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "No podrás recuperar la información. ¿Estas seguro?");
            alert.setTitle("¡OJO!");
            alert.setHeaderText("Presta mucha atención!");
            result = alert.showAndWait();
            if (result.get() == ButtonType.OK){      
               cbClientesFacturasPendientes.setSelected(false);
               
               
               clientesManager.eliminarCliente((ClienteBean) tbClientes.getSelectionModel().getSelectedItem()); 
               clientes=FXCollections.observableArrayList(clientesManager.getAllClientes());
               tbClientes.setItems(clientes);
               tbClientes.refresh();
               
               
               tbClientes.getSelectionModel().clearSelection();//Quita la selección de la tabla.
               txtNif.requestFocus();
               btnAgnadir.setDisable(false);
               btnModificar.setDisable(true);
               btnEliminar.setDisable(true);
               limpiarCampos();  
            }           
    }
    /**
     * Metodo que controla el evento que lanza el Menu facturas. Abre la ventana facturas
     * @param e Event
     */
    @FXML
    public void handleMenuFacturas (Event e){
        logger.info("En el evento del menu facturas");
      
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaFacturas.fxml"));
            Parent root;
        try {
            root = (Parent) loader.load();
            VentanaFacturasController controlador = loader.getController();
            controlador.setManager(facturasManager,proyectosManager,clientesManager);
            controlador.setStage(stage);
            ((Stage)btnSalir.getScene().getWindow()).close();   
            controlador.initStage(root);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(VentanaFacturasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    /**
    * Metodo que controla el evento que lanza el Menu servicios. Abre la ventana servicios
    * @param e Event
    */
    @FXML
    public void handleMenuServicios (Event e){
        logger.info("En el evento del menu servicios");

          FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaServiciosObras.fxml"));
            Parent root;
        try {
            root = (Parent) loader.load();
            VentanaServiciosController controlador = loader.getController();
            controlador.setManager(facturasManager,proyectosManager,clientesManager);
            controlador.setStage(stage);
            ((Stage)btnSalir.getScene().getWindow()).close();
            controlador.initStage(root);
            
            
        } catch (IOException ex) {
            Logger.getLogger(VentanaClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
    * Metodo que controla el evento que lanza el Menu Proyectos. Abre la ventana proyectos
    * @param e Event
    */
    @FXML
    public void handleMenuProyectos (Event e){
        logger.info("En el evento del menu proyectos");
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaProyectos.fxml"));
            Parent root;
        try {
            root = (Parent) loader.load();
            VentanaProyectosController controlador = loader.getController();
            controlador.setManager(facturasManager,proyectosManager,clientesManager);
            controlador.setStage(stage);
            ((Stage)btnSalir.getScene().getWindow()).close();
            controlador.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(VentanaProyectosController.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }


    /**
     * Metodo que controla el cambio de foco.
     * @param observable 
     * @param oldValue Boolean, True si pierde el foco
     * @param newValue Boolean True si gana el foco
     */
    public void focusChanged(ObservableValue observable, Boolean oldValue,
                            Boolean newValue){           
        if(oldValue){
            //Comprueba que los campos esten vacios
            if(txtNif.getText().trim().equals("")&&txtNombre.getText().trim().equals("")&&
                    txtDireccion.getText().trim().equals("")&&txtEmail.getText().trim().equals("")&&
                    txtTelefono.getText().trim().equals("")&&txtWeb.getText().trim().equals("")&&
                    txtNombrePC.getText().trim().equals("")&&txtEmailPC.getText().trim().equals("")&&
                    txtTelefonoPC.getText().trim().equals("")){
                btnAgnadir.setDisable(false);
                btnModificar.setDisable(true);
                btnEliminar.setDisable(true);
                tbClientes.getSelectionModel().clearSelection();
            }
            //Si escriben en el campo de texto del email comprueba que el email introducido sea correcto
            if(!txtEmail.getText().trim().equals("")&&!clientesManager.emailValido(txtEmail.getText().trim())){
                logger.info("Introduce Email del cliente con formato erroneo");
                Alert alert = new Alert(Alert.AlertType.ERROR, 
                         "Email incorrecto. Ej: felipe@gmail.com");
                         
                    alert.showAndWait();
                    txtEmail.clear();
                    txtEmail.requestFocus();
            }
            //Si escriben en el campo de texto del email de la persona de contacto 
            //comprueba que el email introducido sea correcto
            if(!txtEmailPC.getText().trim().equals("")&&!clientesManager.emailValido(txtEmailPC.getText().trim())){
                logger.info("Introduce Email de la persona de contacto con formato erroneo");
                Alert alert = new Alert(Alert.AlertType.ERROR, 
                         "Email incorrecto. Ej: felipe@gmail.com");
                    alert.showAndWait();
                    txtEmailPC.clear();
                    txtEmailPC.requestFocus();
            }
            //Comprueba que solo se introduzcan numero en el campo del telefono
            if(!txtTelefono.getText().trim().equals("")){
                try{
                    Integer.parseInt(txtTelefono.getText().trim());
                } catch (NumberFormatException e){
                    logger.info("Ha introducido letras en el campo del telefono");
                    Alert alert = new Alert(Alert.AlertType.ERROR, 
                         "El telefono está compuesto solo de números");
                    alert.showAndWait();
                    txtTelefono.clear();
                    txtTelefono.requestFocus();
                }
            }
            //Comprueba que solo se introduzcan numero en el campo del telefono de la persona de contacto
            if(!txtTelefonoPC.getText().trim().equals("")){
                try{
                    Integer.parseInt(txtTelefonoPC.getText().trim());
                } catch (NumberFormatException e){
                    logger.info("Ha introducido letras en el campo del telefono "
                            + "de la persona de contacto");
                    Alert alert = new Alert(Alert.AlertType.ERROR, 
                         "El telefono está compuesto solo de números");
                    alert.showAndWait();
                    txtTelefonoPC.clear();
                    txtTelefonoPC.requestFocus();
                }
            }
            
        }
         
    }
    
    /**
     * Metodo que limpia todos los campos
     */
    public void limpiarCampos(){
        txtDireccion.clear();
        txtEmail.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtWeb.clear();
        txtNif.clear();
        txtNombrePC.clear();
        txtTelefonoPC.clear();
        txtEmailPC.clear();
        
        
    }

}
