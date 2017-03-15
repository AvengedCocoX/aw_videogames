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
    
    //The size that is be increment each time data is loaded
    private final int incrementSize = 2;
    
    //The initial size of game list
    private final int gameListInitSize = 0;
    
    private int sizeGamesList = 0;
    

    private int countTotal, countLoaded;


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
        getGamesListWithPlatform("Xbox One");
    }

    public List<Game> loadData() {

        for (i = i; i < sizeGamesList; i++) {
            gameList.add(gameListFull.get(i));
        }

        countLoaded = gameList.size();
        return gameList;
    }

    public void getGamesListWithPlatform(String platform) {
        i = 0;
        gameListFull = gameEJB.getGameByPlatform(platform);
        countTotal = gameListFull.size();
    }

    public void incrementGamesList() {
        if (sizeGamesList + incrementSize <= countTotal) {
            sizeGamesList += incrementSize;
        } else {
            sizeGamesList = countTotal;
        }
    }

    //Recibe como parámetro un usuario
    public void asignar(Game game) {
        this.game = game;
    }
    
    //Change the loaded list of platform
    public void changePlatform(String platform){
        i=0;
        sizeGamesList = gameListInitSize;
        gameList.clear();
        getGamesListWithPlatform(platform);
    }
}
