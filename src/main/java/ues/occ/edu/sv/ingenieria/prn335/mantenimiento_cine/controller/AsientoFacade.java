/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Asiento;

/**
 *
 * @author jonathan
 */
@Stateless
@LocalBean       
public class AsientoFacade extends AbstractFacade<Asiento> {

    @PersistenceContext(unitName = "cinePU")
    private EntityManager em;


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    public AsientoFacade() {
        super(Asiento.class);
    }
    
}
