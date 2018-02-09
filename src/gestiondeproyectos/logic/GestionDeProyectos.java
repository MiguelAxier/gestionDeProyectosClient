
package gestiondeproyectos.logic;

import gestiondeproyectos.ui.controller.VentanaFacturasController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Miguel Axier Lafuente Peñas
 */
public class GestionDeProyectos extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        FacturasManager facturasManager = new FacturasManagerTestDataGenerator();
        ProyectosManager proyectosManager = new ProyectosManagerTestDataGenerator();
        ClientesManager clientesManager = new ClienteManagerImplementation();
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gestiondeproyectos/ui/view/ventanaFacturas.fxml"));
        Parent root = (Parent) loader.load();
        VentanaFacturasController controlador = loader.getController();
       
        controlador.setManager(facturasManager,proyectosManager,clientesManager);
        controlador.setStage(stage);
        controlador.initStage(root);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
