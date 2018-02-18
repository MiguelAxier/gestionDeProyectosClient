/*
 * MÉTODOS DE LÓGICA DE NEGOCIO FACTURAS MANAGER
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.ui.controller.FacturaBean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Arantzazu Azkona Villaverde
 */
public class FacturasManagerTestDataGenerator implements FacturasManager{
    
    private ArrayList<FacturaBean>facturas;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
/**
 * MÉTODO GENERADOR DE DATOS
 */
    public FacturasManagerTestDataGenerator(){
        facturas=new ArrayList();
        for(int i=0;i<10;i++){
            if (i<7){
                facturas.add(new FacturaBean(i+1,"0"+(i+1)+"-11-2017",(i+1)*10000f,"no pagada","0"+(i+3)+"-11-2017","B9556450"));
            }
            else{
                if(i==9){
                    facturas.add(new FacturaBean(i+1,(i+1)+"-11-2017",i*10000f,"pagada",(i+3)+"-11-2017","B778899"));
                }
                else{
                    facturas.add(new FacturaBean(i+1,"0"+(i+1)+"-11-2017",i*10000f,"pagada",(i+3)+"-11-2017","B952747"));
                }
            }
            
        }
    }
    /**
     * MÉTODO getFacturas
     * @return facturas devuelve una colección de facturas
     */
    @Override
    public Collection getFacturas() {
        return facturas; 
    }
    /**
     * MÉTODO BUSCADOR DE FACTURAS
     * @param check define el estado de la factura, "pagada" o "no pagada"
     * @param nif define el nif del cliente
     * @param fechaDesde define la fecha de vencimiento de inicio de la búsqueda de facturas
     * @param fechaHasta define la fecha de vencimiento tope de la búsqueda de facturas
     * @return facturas devuelve una colección de facturas que cumplan con los criterios de búsqueda
     */
    
    @Override
    public List<FacturaBean> getFacturasPendientes() {
        List<FacturaBean> facturasFiltro = facturas;
        facturasFiltro = facturas.stream()
                            .filter(f -> f.getEstado().equals("no pagada"))
                .map(f -> f).collect(Collectors.toList());
        
        return facturasFiltro;
    }
    
    @Override
    public Collection buscarFacturas(Boolean check, String nif, LocalDate fechaDesde, LocalDate fechaHasta) {
        Collection facturasFiltro = null;
        facturasFiltro=facturas;
        
        if(check){
            this.facturas=(ArrayList<FacturaBean>) facturasFiltro;
            facturasFiltro=facturas.stream().filter(c -> c.getEstado().equals("pagada"))
                                .collect(Collectors.toList());
        } 
        if(nif.length()!=0){
            this.facturas=(ArrayList<FacturaBean>) facturasFiltro;
            facturasFiltro=facturas.stream().filter(c -> c.getNif().equalsIgnoreCase(nif))
                                .collect(Collectors.toList());
        }
        if(fechaDesde!=null){
            this.facturas=(ArrayList<FacturaBean>) facturasFiltro;
            facturasFiltro=facturas.stream().filter(c -> LocalDate.parse(c.getFechaVencimiento(), formatter).compareTo(fechaDesde)>=0)
                               .collect(Collectors.toList());
        } 
        if(fechaHasta!=null){
            this.facturas=(ArrayList<FacturaBean>) facturasFiltro;
            facturasFiltro=facturas.stream().filter(c -> LocalDate.parse(c.getFechaVencimiento(), formatter).compareTo(fechaHasta)<=0)
                                .collect(Collectors.toList());
        }
        return facturasFiltro;
    }
    /**
     * MÉTODO PARA AÑADIR FACTURA NUEVA
     * @param factura define un objeto factura que se añade a las facturas
     */
    @Override
    public void anyadirFactura(FacturaBean factura) {
        facturas.add(factura);

    }
    /**
     * MÉTODO PARA BORRAR FACTURA
     * @param factura define un objeto factura que se eliminará de las facturas
     */
    public void borrarFactura(FacturaBean factura){
        facturas.remove(factura);    
    }
    /**
     * MÉTODO PARA BUSCAR UN NÚMERO DE FACTURA
     * @param numero
     * @return resultado devuelve una variable de tipo booleano que indica si el número de factura existe
     */
    public boolean buscarNFactura(int numero){
        boolean resultado=false;
        for(FacturaBean factura: facturas){
             if(factura.getNFactura()==numero){
                 resultado=true;                 
            }
        }   
        return resultado;
    }
   /**
    * MÉTODO PARA MODIFICAR UNA FACTURA
    * @param factura1 define un objeto de tipo factura  
    * @param numero define un número de factura
    */
   public void modificarFactura(FacturaBean factura1,int numero){
       
         for(FacturaBean factura: facturas){
            if(factura.getNFactura()==numero){
                factura.setNFactura(factura1.getNFactura());
                factura.setFechaEmision(factura1.getFechaEmision());
                factura.setImporte(factura1.getImporte());
                factura.setEstado(factura1.getEstado());
                factura.setFechaVencimiento(factura1.getFechaVencimiento());
                factura.setNif(factura1.getNif());
            }
            else{            
            }
        }
    }
/**
 * MÉTODO PARA COMPROBAR EL ESTADO DE LA FACTURA
 */   
   public boolean comprobarEstado(int numero){
       boolean resultado=false;
       for(FacturaBean factura: facturas){
             if(factura.getNFactura()==numero){
                 if(factura.getEstado().equals("pagada")){
                         resultado=true;                 }
            }
        }   
       return resultado;
   }

    
}
