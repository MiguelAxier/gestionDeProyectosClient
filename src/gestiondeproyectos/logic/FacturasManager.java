/*
 * INTERFAZ FACTURAS MANAGER
 */
package gestiondeproyectos.logic;

import gestiondeproyectos.ui.controller.FacturaBean;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Arantzazu Azkona Villaverde
 */
public interface FacturasManager {
    public Collection getFacturas();
    public Collection buscarFacturas(Boolean check, String nif, LocalDate fechaDesde, LocalDate fechaHasta);
    public void anyadirFactura(FacturaBean factura);
    public void borrarFactura(FacturaBean factura);
    public boolean buscarNFactura(int num);
    public void modificarFactura(FacturaBean factura,int numero);
    public boolean comprobarEstado(int numero);
    public List<FacturaBean> getFacturasPendientes();
}
