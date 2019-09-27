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
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.CaracteristicaFuncion;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.CaracteristicaFuncionFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "caracteristicaFuncionBean")
@ViewScoped
public class CaracteristicaFuncionBean implements Serializable{

    @Inject
    CaracteristicaFuncionFacade cff;
    CaracteristicaFuncion funcionc;
    LazyDataModel<CaracteristicaFuncion> lazy;
    
    
    /**
     * Creates a new instance of CaracteristicaFuncionBean
     */
    public CaracteristicaFuncionBean() {
    }
    
    @PostConstruct
    public void llenarlazy(){
    lazy=new LazyDataModel<CaracteristicaFuncion>() {
        @Override
        public CaracteristicaFuncion getRowData(String rowKey) {
            try {
                            Integer buscado = new Integer(rowKey);
                            for (CaracteristicaFuncion reg : (List<CaracteristicaFuncion>) getWrappedData()) {
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
        public Object getRowKey(CaracteristicaFuncion object) {
            if(object!=null){
            return object.getIdCaracteristica();
            }
            return null;
                    }

        @Override
        public List<CaracteristicaFuncion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            this.setRowCount(cff.count());
            return cff.findRange(first, pageSize);
        }
    };
    }
    
    public void crear(){
    if(funcionc!=null){
        int tamanio=cff.count();
        funcionc.setIdCaracteristica(tamanio+1);
        cff.create(funcionc);
        limpiar();
    }
    }
    
    public void actualizar(){
    if(funcionc!=null){
        cff.edit(funcionc);
        limpiar();
    }
    }
    
    public void eliminar(){
    if(funcionc!=null){
        cff.remove(funcionc);
        limpiar();
    }
    }
    
    public void limpiar(){
    funcionc=new CaracteristicaFuncion();
    }

    public CaracteristicaFuncion getFuncionc() {
        return funcionc;
    }

    public void setFuncionc(CaracteristicaFuncion funcionc) {
        this.funcionc = funcionc;
    }

    public LazyDataModel<CaracteristicaFuncion> getLazy() {
        return lazy;
    }

    public void setLazy(LazyDataModel<CaracteristicaFuncion> lazy) {
        this.lazy = lazy;
    }
    
    
}
