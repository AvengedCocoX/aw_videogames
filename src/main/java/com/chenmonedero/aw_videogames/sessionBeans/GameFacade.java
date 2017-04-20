/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.sessionBeans;

import com.chenmonedero.aw_videogames.entities.Game;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AvengedCocoX
 */
@Stateless
public class GameFacade extends AbstractFacade<Game> implements GameFacadeLocal {
    @PersistenceContext(unitName = "com.chenmonedero_aw_videogames_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GameFacade() {
        super(Game.class);
    }
    
    
    @Override
    public List<Game> getGameByPlatform(String platform){
        List<Game> gamesOfPlatform = null;
        Query query = em.createNamedQuery("Game.findByPlatform");
        query.setParameter("platformName", platform);
        
        gamesOfPlatform = (List<Game>) query.getResultList();
                
        return gamesOfPlatform;
    }
    
    @Override
    public List<Game> getGameByPlatformAndTitle(String platform, String title){
        List<Game> gamesOfPlatform = null;
        Query query = em.createNamedQuery("Game.findByPlatformAndTitle");
        query.setParameter("platformName", platform);
        query.setParameter("title", title);
        
        gamesOfPlatform = (List<Game>) query.getResultList();
                
        return gamesOfPlatform;
    }
}
