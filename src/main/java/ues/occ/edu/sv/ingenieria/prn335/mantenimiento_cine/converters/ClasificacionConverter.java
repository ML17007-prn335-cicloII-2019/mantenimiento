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
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Clasificacion;
import ues.occ.edu.sv.ingenieria.prn335.mantenimiento_cine.controller.ClasificacionFacade;

/**
 *
 * @author jonathan
 */
@FacesConverter("clasificacionconverter")
public class ClasificacionConverter implements Converter{
    
    private ClasificacionFacade cfacade;

   
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return this.getcfacade().find(getKey(value));
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
        if (object instanceof Clasificacion) {
            Clasificacion o = (Clasificacion) object;
            return getStringKey(o.getIdClasificacion());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Clasificacion.class.getName()});
            return null;
        }
    }

    private ClasificacionFacade getcfacade() {
        this.cfacade = CDI.current().select(ClasificacionFacade.class).get();
        return this.cfacade;
    }
}
