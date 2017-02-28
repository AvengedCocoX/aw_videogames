/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.sessionBeans;

import com.chenmonedero.aw_videogames.entities.User2group;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AvengedCocoX
 */
@Local
public interface User2groupFacadeLocal {

    void create(User2group user2group);

    void edit(User2group user2group);

    void remove(User2group user2group);

    User2group find(Object id);

    List<User2group> findAll();

    List<User2group> findRange(int[] range);

    int count();
    
}
