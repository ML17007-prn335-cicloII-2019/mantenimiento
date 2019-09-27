/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.boundary;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.CaracteristicaAsiento;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.CaracteristicaAsientoFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "caracteristicaAsientoBean")
@ViewScoped
public class CaracteristicaAsientoBean implements Serializable{
    
    @Inject
    CaracteristicaAsientoFacade caf;
    CaracteristicaAsiento asientoc;
    LazyDataModel<CaracteristicaAsiento> lazy;
    
    /**
     * Creates a new instance of CaracteristicaAsientoBean
     */
    public CaracteristicaAsientoBean() {
    }
    
    @PostConstruct
    public void llenarlazy(){
    lazy=new LazyDataModel<CaracteristicaAsiento>() {
        @Override
        public CaracteristicaAsiento getRowData(String rowKey) {
            try {
                            Integer buscado = new Integer(rowKey);
                            for (CaracteristicaAsiento reg : (List<CaracteristicaAsiento>) getWrappedData()) {
                                if (reg.getIdCaracteristica().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
            return null;
                    }


        @Override
        public Object getRowKey(CaracteristicaAsiento object) {
            if(object!=null){
            return object.getIdCaracteristica();
            }
            return null;
                    }

        @Override
        public List<CaracteristicaAsiento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            this.setRowCount(caf.count());
            return caf.findRange(first, pageSize);
        }
    };
    }
    
    public void crear(){
    if(asientoc!=null){
        int tamanio=caf.count();
        asientoc.setIdCaracteristica(tamanio+1);
        caf.create(asientoc);
        limpiar();
    }
    }
    
    public void actualizar(){
    if(asientoc!=null){
        caf.edit(asientoc);
        limpiar();
    }
    }
    
    public void eliminar(){
    if(asientoc!=null){
        caf.remove(asientoc);
        limpiar();
    }
    }
    
    public void limpiar(){
    asientoc=new CaracteristicaAsiento();
    }

    public CaracteristicaAsiento getAsientoc() {
        return asientoc;
    }

    public void setAsientoc(CaracteristicaAsiento asientoc) {
        this.asientoc = asientoc;
    }

    public LazyDataModel<CaracteristicaAsiento> getLazy() {
        return lazy;
    }

    public void setLazy(LazyDataModel<CaracteristicaAsiento> lazy) {
        this.lazy = lazy;
    }
    
    
}
