/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.converters;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Funcion;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.FuncionFacade;

/**
 *
 * @author jonathan
 */
@FacesConverter("funcionconverter")
public class FuncionConverter implements Converter{
    
    
    private FuncionFacade ffacade;

   
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return this.getffacade().find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Funcion) {
            Funcion o = (Funcion) object;
            return getStringKey(o.getIdFuncion());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Funcion.class.getName()});
            return null;
        }
    }

    private FuncionFacade getffacade() {
        this.ffacade = CDI.current().select(FuncionFacade.class).get();
        return this.ffacade;
    }
    
    
}
