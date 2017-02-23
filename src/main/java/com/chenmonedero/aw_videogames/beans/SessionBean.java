package com.chenmonedero.aw_videogames.beans;


import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@Named("sessionBean")
@SessionScoped
public class SessionBean implements Serializable
{

    private String nombre;

    public String getNombre() {
        if (nombre == null) 
        {
            getDatosUsuario();
        }
        
        return nombre == null ? "" : nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Obtiene el nombre del usuario de la sesión
    private void getDatosUsuario() 
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object objPeticion = context.getRequest();
        
        if (!(objPeticion instanceof HttpServletRequest)) 
        {
            System.out.println("Error objeto es: "+ objPeticion.getClass().toString());
            return;
        }
        
        HttpServletRequest peticion = (HttpServletRequest) objPeticion;
        
        //nombre = peticion.getRemoteUser();
        nombre = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
        
        if (nombre == null) 
        {
            logout();
        }
    }
    //Invalida la Sesión y redigiré a la página de inicio
    public void logout() 
    {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        
        try 
        {
            ec.redirect(ec.getRequestContextPath());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}