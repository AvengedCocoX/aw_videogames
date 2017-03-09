/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.sessionBeans.GameFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author AvengedCocoX
 */
@Named
@RequestScoped
public class GamesBean implements Serializable{
    
    @EJB
    private GameFacadeLocal gameEJB;
    
    private List<Game> gameList;
    
    //G&S
    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
    
    
    @PostConstruct
    public void init() {
        this.gameList = getGames();
    }
    
    
    private List<Game> getGames(){
        List<Game> gameList = gameEJB.findAll();
        
        return gameList;
    }
}
