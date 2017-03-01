
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Platform;
import com.chenmonedero.aw_videogames.sessionBeans.PlatformFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named
@RequestScoped
public class ListPlatformsBean implements Serializable{
    
    @EJB
    private PlatformFacadeLocal platFac;
    
    //Lista para almacenar
    private List<Platform> platformList;
    private List<String> platformListString;

    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }

    public List<String> getPlatformListString() {
        return platformListString;
    }

    public void setPlatformListString(List<String> platformListString) {
        this.platformListString = platformListString;
    }
    
    
    @PostConstruct
    public void init() 
    {
        platformListString = new ArrayList<>();
        platformList = new ArrayList<>();
        platformList = platFac.findAll();
        
        for(Platform p : platformList){
            platformListString.add(p.getName());
        }
    }
    
}
