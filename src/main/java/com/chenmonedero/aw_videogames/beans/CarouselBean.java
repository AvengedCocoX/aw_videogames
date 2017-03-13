/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.sessionBeans.GameFacadeLocal;
import com.chenmonedero.aw_videogames.sessionBeans.PlatformFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author AvengedCocoX
 */
@Named
@ApplicationScoped
public class CarouselBean implements Serializable{
    @EJB
    private GameFacadeLocal gameEJB;
    
    private List<String> images;

    //G&S
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    
    
    @PostConstruct
    public void init() {
        this.images = getGamesImages();
    }
    
    private List<String> getGamesImages(){
        List<Game> listGames = gameEJB.findAll();
        List<String> images = new ArrayList<>();
        
        for(Game g : listGames){
            images.add(g.getProfileImage());
        }
        
        return images;
    }
}
