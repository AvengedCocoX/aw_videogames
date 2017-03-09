/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author AvengedCocoX
 */

@Named
@SessionScoped
public class DataScrollerBean implements Serializable{
    
    private List<Game> gameList = null;
    
    
    
    @PostConstruct
    public void init(){
        gameList = new ArrayList<>();
        
        
    }
    
}
