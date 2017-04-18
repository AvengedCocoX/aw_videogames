/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.sessionBeans;

import com.chenmonedero.aw_videogames.entities.Platform;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AvengedCocoX
 */
@Local
public interface PlatformFacadeLocal {

    void create(Platform platform);

    void edit(Platform platform);

    void remove(Platform platform);

    Platform find(Object id);

    List<Platform> findAll();

    List<Platform> findRange(int[] range);

    int count();
    
    public Platform findPlatformByName(String name);
}
