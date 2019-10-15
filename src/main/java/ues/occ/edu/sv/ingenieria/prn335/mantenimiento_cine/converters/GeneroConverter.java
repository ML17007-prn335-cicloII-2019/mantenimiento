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
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Genero;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.GeneroFacade;

/**
 *
 * @author jonathan
 */
@FacesConverter("generoconverter")
public class GeneroConverter implements Converter{
    
    private GeneroFacade gfacade;

   
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return this.getgfacade().find(getKey(value));
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
        if (object instanceof Genero) {
            Genero o = (Genero) object;
            return getStringKey(o.getIdGenero());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Genero.class.getName()});
            return null;
        }
    }

    private GeneroFacade getgfacade() {
        this.gfacade = CDI.current().select(GeneroFacade.class).get();
        return this.gfacade;
    }
    
    
}
