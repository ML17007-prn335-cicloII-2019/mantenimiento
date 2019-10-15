/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.boundary;

import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Director;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Pelicula;
import java.io.Serializable;
import java.util.ArrayList;
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
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.DirectorFacade;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.PeliculaFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "directorBean")
@ViewScoped
public class DirectorBean implements Serializable{

    @Inject
    DirectorFacade df;
    LazyDataModel<Director> lazy;
    Director d=new Director();
    
    /**
     * Creates a new instance of DirectorBean
     */
    public DirectorBean() {
    }
    
    @PostConstruct
    public void llenarlazy(){
    lazy=new LazyDataModel<Director>() {
        @Override
        public Director getRowData(String rowKey) {
            try {
                            Integer buscado = new Integer(rowKey);
                            for (Director reg : (List<Director>) getWrappedData()) {
                                if (reg.getIdDirector().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
            return null;
                    }


        @Override
        public Object getRowKey(Director object) {
            if(object!=null){
            return object.getIdDirector();
            }
            return null;
                    }

        @Override
        public List<Director> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            this.setRowCount(df.count());
            return df.findRange(first, pageSize);
        }
    };
    }


    public LazyDataModel<Director> getLazy() {
        return lazy;
    }

    public void setLazy(LazyDataModel<Director> lazy) {
        this.lazy = lazy;
    }

    public Director getD() {
        return d;
    }

    public void setD(Director d) {
        this.d = d;
    }
    
    public void crear(){
    if(d!=null){
        int tamanio=df.count();
        d.setIdDirector(tamanio+1);
        df.create(d);
    }
    }
    
    public void actualizar(){
    if(d!=null){
        df.edit(d);
    }
    }
    
    public void eliminar(){
    if(d!=null){
        df.remove(d);
        limpiar();
    }
    }
    
    public void limpiar(){
    d=new Director();
    }

    
    
    
    
}
