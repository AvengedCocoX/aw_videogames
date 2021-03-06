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
import com.chenmonedero.aw_videogames.sessionBeans.GameFacadeLocal;
import com.chenmonedero.aw_videogames.sessionBeans.UserFacadeLocal;
import com.chenmonedero.aw_videogames.sessionBeans.ValorationFacadeLocal;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
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
    @EJB
    private GameFacadeLocal gameEJB;

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
    private boolean userCommented;

    public int getValoration() {
        return valoration;
    }

    public void setValoration(int valoration) {
        this.valoration = valoration;
    }

    public int getScore() {
        return 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return "";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isUserCommented() {
        return userCommented;
    }

    public void setUserCommented(boolean userCommented) {
        this.userCommented = userCommented;
    }


    //Sección comentarios
    private List<Valoration> valorationList;

    public List<Valoration> getValorationList() {
        return valorationList;
    }

    public void setValorationList(List<Valoration> valorationList) {
        this.valorationList = valorationList;
    }
    
    

    @PostConstruct
    public void init() {
        //Game value is selected from the index page
        this.game = dataScrollerBean.getGame();

        //Video url
        for (GameVideo gv : game.getGameVideoCollection()) {
            video_url = gv.getUrl();
        }

        updateValoration();

        valorationObj = new Valoration();
        user = new User();
        userCommented = false;
        updateUserCommented();
    }
    
    private void updateUserCommented(){
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
            List<User> listaUsuarios = userEJB.findAll();
            for (User u : listaUsuarios) {
                if (u.getUsername().equals(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString())) {
                    user = u;
                }
            }
            userCommented = userCommented(game.getValorationCollection(), user);
        }
    }
    
    private void updateValoration(){
        valoration_count = 0;
        valoration = 0;
        //Valoration
        for (Valoration v : game.getValorationCollection()) {
            valoration = (int) (valoration + v.getScore());
            valoration_count++;
        }

        
        //Valoration list
        valorationList = (List<Valoration>) game.getValorationCollection();
        

        if (valoration_count == 0) {
            valoration_count = 1;
        }
        valoration = valoration / valoration_count;
    }

    private boolean userCommented(Collection<Valoration> valorations, User user) {
        boolean b = false;
        ArrayList<Valoration> vs = new ArrayList<>();
        vs.addAll(valorations);
        for (Valoration v : vs) {
            if (v.getUsername().getId() == user.getId()) {
                b = true;
            }
        }
        return b;
    }

    public void rate() throws ParseException {

        //Date for valoration date
        //Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        Date myDate = formatter.parse(dtf.format(localDate));
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Date: " + sqlDate);
        System.out.println("Game: " + this.game.getTitle());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Score: " + score);
        System.out.println("Comment: " + comment);
        if (comment == null || comment.equals("")) {
            comment = "-";
        }

        this.valorationObj.setUsername(user);
        this.valorationObj.setGameTitle(this.game);
        this.valorationObj.setScore(score);
        this.valorationObj.setComment(comment);
        this.valorationObj.setValorationDate(sqlDate);

        valorationEJB.create(valorationObj);

        game.getValorationCollection().add(valorationEJB.find(valorationObj.getIdValoration()));

        gameEJB.edit(game);
        updateValoration();
        updateUserCommented();
        //Mensaje de exito
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias!", "Valoración enviada"));
    }

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void removeRate(){
        Valoration valoration = null;
        Collection valorations = game.getValorationCollection();
        ArrayList<Valoration> vs = new ArrayList<>();
        vs.addAll(valorations);
        for (Valoration v : vs) {
            if (v.getUsername().getId() == user.getId()) {
                valoration = v;
            }
        }
        valorationEJB.remove(valoration);
        game.getValorationCollection().remove(valoration);
        gameEJB.edit(game);
        updateValoration();
        updateUserCommented();
        //Mensaje de exito
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Valoración eliminada"));
        
    }
}
