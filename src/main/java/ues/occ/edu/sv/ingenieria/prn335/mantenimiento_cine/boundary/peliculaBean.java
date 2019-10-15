/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.boundary;

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
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Clasificacion;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Director;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Funcion;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Genero;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.ClasificacionFacade;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.DirectorFacade;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.FuncionFacade;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.GeneroFacade;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.PeliculaFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "peliculaBean")
@ViewScoped
public class peliculaBean implements Serializable {

    @Inject
    PeliculaFacade pf;
    Pelicula peli=new Pelicula();
    LazyDataModel<Pelicula> lazy;
    @Inject
    ClasificacionFacade cf;
    @Inject
    DirectorFacade df;
    List<Director> listadirector = new ArrayList<>();
    List<Clasificacion> listaclasificacion = new ArrayList<>();
    @Inject 
    GeneroFacade gf;
    @Inject
    FuncionFacade ff;
    List<Genero> listagenero = new ArrayList<>();
    List<Funcion> listafuncion = new ArrayList<>();
    Genero gener;
    Funcion f;
    Boolean visible=false;
    String valor="Crear";
    /**
     * Creates a new instance of peliculaBean
     */
    public peliculaBean() {
    }

    @PostConstruct
    public void llenarlazy() {
        this.listagenero=gf.findAll();
        this.listafuncion=ff.findAll();
        this.listadirector = df.findAll();
        this.listaclasificacion = cf.findAll();
        lazy = new LazyDataModel<Pelicula>() {
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
                if (object != null) {
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

    public void crear() {
        if (peli != null) {
            int tamanio = pf.count();
            peli.setIdPelicula(tamanio + 1);
            pf.create(peli);
        }
    }

    public void actualizar() {
        if (peli != null) {
            pf.edit(peli);
        }
    }

    public void eliminar() {
        if (peli != null) {
            pf.remove(peli);
        }
    }

    public void limpiar() {
        peli = new Pelicula();
    }

    public List<Director> getListadirector() {
        return listadirector;
    }

    public void setListadirector(List<Director> listadirector) {
        this.listadirector = listadirector;
    }

    public List<Clasificacion> getListaclasificacion() {
        return listaclasificacion;
    }

    public void setListaclasificacion(List<Clasificacion> listaclasificacion) {
        this.listaclasificacion = listaclasificacion;
    }

    public List<Genero> getListagenero() {
        return listagenero;
    }

    public void setListagenero(List<Genero> listagenero) {
        this.listagenero = listagenero;
    }

    public List<Funcion> getListafuncion() {
        return listafuncion;
    }

    public void setListafuncion(List<Funcion> listafuncion) {
        this.listafuncion = listafuncion;
    }

    public Genero getGener() {
        return gener;
    }

    public void setGener(Genero gener) {
        this.gener = gener;
    }

    public Funcion getF() {
        return f;
    }

    public void setF(Funcion f) {
        this.f = f;
    }

    public void guardarfuncion(){
    this.peli.getFuncionList().add(f);
    this.actualizar();
    }
    
    public void guardargenero(){
    this.peli.getGeneroList().add(gener);
    this.actualizar();
    }
    
    public void cambiar(){
     if(this.visible==false){
     this.valor="Cancelar";   
     this.visible=true;
     this.limpiar();
     }else{
         this.limpiar();
     this.valor="Crear";    
     this.visible=false;
     }
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void mostrar(){
    this.visible=true;
    this.valor="Cancelar";
    }
    
    
}
