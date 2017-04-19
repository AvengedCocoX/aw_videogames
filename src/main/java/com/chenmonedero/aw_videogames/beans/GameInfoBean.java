/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.entities.GameVideo;
import com.chenmonedero.aw_videogames.entities.User;
import com.chenmonedero.aw_videogames.entities.Valoration;
import com.chenmonedero.aw_videogames.sessionBeans.UserFacadeLocal;
import com.chenmonedero.aw_videogames.sessionBeans.ValorationFacadeLocal;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RateEvent;

/**
 *
 * @author AvengedCocoX
 */
@Named
@ViewScoped
public class GameInfoBean implements Serializable {

    @Inject
    private DataScrollerBean dataScrollerBean;

    @EJB
    private ValorationFacadeLocal valorationEJB;
    @EJB
    private UserFacadeLocal userEJB;

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

    private int valoration;
    private int valoration_count;
    private int score;
    private String comment;
    private Valoration valorationObj;
    private User user;

    public int getValoration() {
        return valoration;
    }

    public void setValoration(int valoration) {
        this.valoration = valoration;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @PostConstruct
    public void init() {
        //Game value is selected from the index page
        this.game = dataScrollerBean.getGame();

        //Video url
        for (GameVideo gv : game.getGameVideoCollection()) {
            video_url = gv.getUrl();
        }

        //Valoration
        for (Valoration v : game.getValorationCollection()) {
            valoration = (int) (valoration + v.getScore());

            valoration_count++;
        }
        
        if(valoration_count == 0){
            valoration_count = 1;
        }
        valoration = valoration / valoration_count;

        valorationObj = new Valoration();
        user = new User();
    }

    public void rate() throws ParseException {
        
        List<User> listaUsuarios = userEJB.findAll();
        for(User u : listaUsuarios){
            if(u.getUsername().equals(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString())){
                user = u;
            }
        }
        
        //Date for valoration date
        //Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2017-03-03";
        Date myDate = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Date: " + sqlDate);
        System.out.println("Game: " + this.game.getTitle());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Score: " + score);
        System.out.println("Comment: " + comment);

        this.valorationObj.setUsername(user);
        this.valorationObj.setGameTitle(this.game);
        this.valorationObj.setScore(score);
        this.valorationObj.setComment(comment);
        this.valorationObj.setValorationDate(sqlDate);
        
        valorationEJB.create(valorationObj);

        //Mensaje de exito
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Valoración enviada"));
    }

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
