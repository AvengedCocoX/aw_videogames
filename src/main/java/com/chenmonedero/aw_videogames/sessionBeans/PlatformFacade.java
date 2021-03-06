/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.sessionBeans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.entities.Platform;
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
public class PlatformFacade extends AbstractFacade<Platform> implements PlatformFacadeLocal {
    @PersistenceContext(unitName = "com.chenmonedero_aw_videogames_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlatformFacade() {
        super(Platform.class);
    }
    
    @Override
    public Platform findPlatformByName(String name){
        List<Platform> platform = null;
        Query query = em.createNamedQuery("Platform.findByName");
        query.setParameter("name", name);
        
        platform = (List<Platform>) query.getResultList();
        return platform.get(0);
    }
    
}
