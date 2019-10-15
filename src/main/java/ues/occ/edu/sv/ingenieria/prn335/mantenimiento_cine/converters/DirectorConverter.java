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
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Director;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.DirectorFacade;

/**
 *
 * @author jonathan
 */
@FacesConverter("directorconverter")
public class DirectorConverter implements Converter{
    
     private DirectorFacade dfacade;

   
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return this.getdfacade().find(getKey(value));
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
        if (object instanceof Director) {
            Director o = (Director) object;
            return getStringKey(o.getIdDirector());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Director.class.getName()});
            return null;
        }
    }

    private DirectorFacade getdfacade() {
        this.dfacade = CDI.current().select(DirectorFacade.class).get();
        return this.dfacade;
    }
    
    
    
}
