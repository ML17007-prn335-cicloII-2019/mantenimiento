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
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.CaracteristicaSala;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.CaracteristicaSalaFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "caracteristicaSalaBean")
@ViewScoped
public class CaracteristicaSalaBean implements Serializable{

    @Inject
    CaracteristicaSalaFacade csf;
    CaracteristicaSala salac;
    LazyDataModel<CaracteristicaSala> lazy;
    
    /**
     * Creates a new instance of CaracteristicaSala
     */
    public CaracteristicaSalaBean() {
    }
    
    @PostConstruct
    public void llenarlazy(){
    lazy=new LazyDataModel<CaracteristicaSala>() {
        @Override
        public CaracteristicaSala getRowData(String rowKey) {
            try {
                            Integer buscado = new Integer(rowKey);
                            for (CaracteristicaSala reg : (List<CaracteristicaSala>) getWrappedData()) {
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
        public Object getRowKey(CaracteristicaSala object) {
            if(object!=null){
            return object.getIdCaracteristica();
            }
            return null;
                    }

        @Override
        public List<CaracteristicaSala> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            this.setRowCount(csf.count());
            return csf.findRange(first, pageSize);
        }
    };
    }
    
    public void crear(){
    if(salac!=null){
        int tamanio=csf.count();
        salac.setIdCaracteristica(tamanio+1);
        csf.create(salac);
        limpiar();
    }
    }
    
    public void actualizar(){
    if(salac!=null){
        csf.edit(salac);
        limpiar();
    }
    }
    
    public void eliminar(){
    if(salac!=null){
        csf.remove(salac);
        limpiar();
    }
    }
    
    public void limpiar(){
    salac=new CaracteristicaSala();
    }

    public CaracteristicaSala getSalac() {
        return salac;
    }

    public void setSalac(CaracteristicaSala salac) {
        this.salac = salac;
    }

    public LazyDataModel<CaracteristicaSala> getLazy() {
        return lazy;
    }

    public void setLazy(LazyDataModel<CaracteristicaSala> lazy) {
        this.lazy = lazy;
    }
    
    
}
