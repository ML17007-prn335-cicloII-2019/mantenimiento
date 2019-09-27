/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.boundary;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.SucursalFacade;

/**
 *
 * @author jonathan
 */
@Named(value = "sucursalBean")
@ViewScoped
public class SucursalBean implements Serializable{

    @Inject
    SucursalFacade tpb;
    
    /**
     * Creates a new instance of SucursalBean
     */
    public SucursalBean() {
    }
    
}
