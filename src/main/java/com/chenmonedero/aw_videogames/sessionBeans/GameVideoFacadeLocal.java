/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.sessionBeans;

import com.chenmonedero.aw_videogames.entities.GameVideo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AvengedCocoX
 */
@Local
public interface GameVideoFacadeLocal {

    void create(GameVideo gameVideo);

    void edit(GameVideo gameVideo);

    void remove(GameVideo gameVideo);

    GameVideo find(Object id);

    List<GameVideo> findAll();

    List<GameVideo> findRange(int[] range);

    int count();
    
}
