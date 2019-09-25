/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.boundary;

import es.occ.edu.sv.ingenieria.prn335.cineData.entity.Pelicula;
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
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.PeliculaFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "peliculaBean")
@ViewScoped
public class peliculaBean implements Serializable{

    @Inject
    PeliculaFacade pf;
    Pelicula peli;
    LazyDataModel<Pelicula> lazy;
    /**
     * Creates a new instance of peliculaBean
     */
    public peliculaBean() {
    }
    
    @PostConstruct
    public void llenarlazy(){
    lazy=new LazyDataModel<Pelicula>() {
        @Override
        public Pelicula getRowData(String rowKey) {
            try {
                            Integer buscado = new Integer(rowKey);
                            for (Pelicula reg : (List<Pelicula>) getWrappedData()) {
                                if (reg.getIdPelicula().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
            return null;
                    }


        @Override
        public Object getRowKey(Pelicula object) {
            if(object!=null){
            return object.getIdPelicula();
            }
            return null;
                    }

        @Override
        public List<Pelicula> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            this.setRowCount(pf.count());
            return pf.findRange(first, pageSize);
        }
    };
    }


    public LazyDataModel<Pelicula> getLazy() {
        return lazy;
    }

    public void setLazy(LazyDataModel<Pelicula> lazy) {
        this.lazy = lazy;
    }

    public Pelicula getPeli() {
        return peli;
    }

    public void setPeli(Pelicula peli) {
        this.peli = peli;
    }
    
    public void crear(){
    if(peli!=null){
        int tamanio=pf.count();
        peli.setIdPelicula(tamanio+1);
        pf.create(peli);
        limpiar();
    }
    }
    
    public void actualizar(){
    if(peli!=null){
        pf.edit(peli);
        limpiar();
    }
    }
    
    public void eliminar(){
    if(peli!=null){
        pf.remove(peli);
        limpiar();
    }
    }
    
    public void limpiar(){
    peli=new Pelicula();
    }
    
}
