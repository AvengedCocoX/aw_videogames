/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.sessionBeans;

import com.chenmonedero.aw_videogames.entities.User2group;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AvengedCocoX
 */
@Stateless
public class User2groupFacade extends AbstractFacade<User2group> implements User2groupFacadeLocal {
    @PersistenceContext(unitName = "com.chenmonedero_aw_videogames_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public User2groupFacade() {
        super(User2group.class);
    }
    
}
