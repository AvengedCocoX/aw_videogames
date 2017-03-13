/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.sessionBeans.GameFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AvengedCocoX
 */
@Named
@ViewScoped
public class DataScrollerBean implements Serializable {

    @EJB
    private GameFacadeLocal gameEJB;

    private List<Game> gameListFull;

    private List<Game> gameList;

    private int i;

    //G&S
    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @PostConstruct
    public void init() {
        gameListFull = gameEJB.findAll();
        gameList = new ArrayList<>();

        for (i = 0; i < 3; i++) {
            gameList.add(gameListFull.get(i));
        }
    }

    public void loadData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DataScrollerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (i < gameListFull.size()-1) {
            int temp = i;            
            //System.out.println("temp: "+temp);
            
            for (i = i; i < temp + 2; i++) {
                //System.out.println("i: "+i);
                gameList.add(gameListFull.get(i));
                
            }
        }
    }
}
