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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author AvengedCocoX
 */
@Named
@SessionScoped
public class DataScrollerBean implements Serializable {

    @EJB
    private GameFacadeLocal gameEJB;    

    private List<Game> gameListFull;

    private List<Game> gameList;

    private int i;

    private int countTotal, countLoaded;

    private String platformLoaded;
    
    //Asignar
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //G&S
    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public int getCountLoaded() {
        return countLoaded;
    }

    public void setCountLoaded(int countLoaded) {
        this.countLoaded = countLoaded;
    }

    @PostConstruct
    public void init() {

        gameList = new ArrayList<>();

    }

    public List<Game> loadData(String platform) {
        if (platform != platformLoaded) {
            getGamesListWithPlatform(platform);
        }

        
        if (i + 2 <= countTotal) {
            int tmp = i;
            for (i = i; i < tmp + 2; i++) {
                gameList.add(gameListFull.get(i));
            }
        }

        countLoaded = gameList.size();
        return gameList;
    }

    public void getGamesListWithPlatform(String platform) {
        i = 0;
        platformLoaded = platform;
        gameListFull = gameEJB.getGameByPlatform(platform);
        countTotal = gameListFull.size();
    }

    public void loadData() {

    }
    
    //Recibe como parámetro un usuario
    public void asignar(Game game)
    {
        this.game = game;
    }
}
