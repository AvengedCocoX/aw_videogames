/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.entities.User;
import com.chenmonedero.aw_videogames.entities.Valoration;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AvengedCocoX
 */
@Named
@ViewScoped
public class ValorationBean implements Serializable {

    @Inject
    private DataScrollerBean dataScrollerBean;
    
    

    private Game game;
    private User user;
    private Valoration valoration;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void init() {
        //Game value is selected from the index page
        this.game = dataScrollerBean.getGame();
    }

    public void asignValoration() {

    }

}
