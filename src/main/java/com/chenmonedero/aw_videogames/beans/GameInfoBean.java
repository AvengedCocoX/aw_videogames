/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.entities.GameVideo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AvengedCocoX
 */
@Named
@ViewScoped
public class GameInfoBean implements Serializable{
    
    @Inject
    private DataScrollerBean dataScrollerBean;
    
    private Game game;
    
    private String video_url;
    
    //G&S
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
    
    
    
    @PostConstruct
    public void init() {
        //El valor del juego es el seleccionado desde dataScrollerBean
        this.game = dataScrollerBean.getGame();
        for(GameVideo gv : game.getGameVideoCollection()){
            video_url = gv.getUrl();
        }
    }
    
}
