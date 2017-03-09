/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import javax.ejb.EJB;

import com.chenmonedero.aw_videogames.entities.Platform;
import com.chenmonedero.aw_videogames.sessionBeans.PlatformFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
/**
 *
 * @author an
 */
@Named
@RequestScoped
public class MenuNavPlataformBean {
    @EJB
    private PlatformFacadeLocal platformEJB;
    
    private List<String> names;
    
    //G&S
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
    
    
    @PostConstruct
    public void init() {
        this.names = getPlatformsNames();
    }
    
    private List<String> getPlatformsNames(){
        List<Platform> listPlatform = platformEJB.findAll();
        List<String> names = new ArrayList<>();
        for(Platform p: listPlatform){
            names.add(p.getName());
        }
        return names;
    }
    
}
