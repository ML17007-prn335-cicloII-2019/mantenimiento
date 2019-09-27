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
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Asiento;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.AsientoFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "asientoBean")
@ViewScoped
public class AsientoBean implements Serializable{
    
    @Inject
    AsientoFacade af;
    Asiento asiento;
    LazyDataModel<Asiento> lazy;

    /**
     * Creates a new instance of AsientoBean
     */
    public AsientoBean() {
    }
    
    @PostConstruct
    public void llenarlazy(){
    lazy=new LazyDataModel<Asiento>() {
        @Override
        public Asiento getRowData(String rowKey) {
            try {
                            Integer buscado = new Integer(rowKey);
                            for (Asiento reg : (List<Asiento>) getWrappedData()) {
                                if (reg.getIdAsiento().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
            return null;
                    }


        @Override
        public Object getRowKey(Asiento object) {
            if(object!=null){
            return object.getIdAsiento();
            }
            return null;
                    }

        @Override
        public List<Asiento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            this.setRowCount(af.count());
            return af.findRange(first, pageSize);
        }
    };
    }
    
    public void crear(){
    if(asiento!=null){
        int tamanio=af.count();
        asiento.setIdAsiento(tamanio+1);
        af.create(asiento);
        limpiar();
    }
    }
    
    public void actualizar(){
    if(asiento!=null){
        af.edit(asiento);
        limpiar();
    }
    }
    
    public void eliminar(){
    if(asiento!=null){
        af.remove(asiento);
        limpiar();
    }
    }
    
    public void limpiar(){
    asiento=new Asiento();
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public LazyDataModel<Asiento> getLazy() {
        return lazy;
    }

    public void setLazy(LazyDataModel<Asiento> lazy) {
        this.lazy = lazy;
    }
    
    
    
}
