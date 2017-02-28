package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.User;
import com.chenmonedero.aw_videogames.entities.User2group;
import com.chenmonedero.aw_videogames.sessionBeans.User2groupFacadeLocal;
import com.chenmonedero.aw_videogames.sessionBeans.UserFacadeLocal;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RegistrarUsuarioBean implements Serializable {

    @EJB
    private UserFacadeLocal userEJB;
    @EJB
    private User2groupFacadeLocal groupEJB;

    private User user;
    private User2group group;

    @PostConstruct
    public void init() {
        user = new User();
        group = new User2group();
    }

    //G&S
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User2group getGroup() {
        return group;
    }

    public void setGroup(User2group group) {
        this.group = group;
    }

    //Registrar usuario
    public void registrar() {
        List<User> listaUsuarios;

        try {
            listaUsuarios = userEJB.findAll();
            boolean usuarioExiste = false;

            for (User us : listaUsuarios) 
            {
                if (us.getUsername().equals(this.user.getUsername())) 
                {
                    //Mensaje de error
                    usuarioExiste = true;
                }
            }

            if (usuarioExiste == true) 
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Nombre de usuario existente en la aplicación"));
            } 
            else 
            {
                this.user.setPassword(Hashing.sha256().hashString(user.getPassword(), Charsets.UTF_8).toString());
                userEJB.create(user);
                this.group.setUsername(user);
                groupEJB.create(group);
                
                clear();

                //Mensaje de exito
                if(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() == null)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto", "Ya puede iniciar sesión en AA VideoGames"));
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario registrado correctamente"));
                }
            }

        } catch (Exception e) {
            //Mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error en el registro de usuario"));
        }
    }

   //Dejar campos vacíos al registrar un usuario
    public void clear() 
    {
        user.setUsername(null);
        user.setName(null);
    }

}