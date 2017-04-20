/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;

import com.chenmonedero.aw_videogames.entities.Game;
import com.chenmonedero.aw_videogames.sessionBeans.GameFacadeLocal;
import com.chenmonedero.aw_videogames.sessionBeans.PlatformFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author an
 */
@Named
@SessionScoped
public class UploadGames implements Serializable {

    @EJB
    private GameFacadeLocal gameEJB;
    @EJB
    private PlatformFacadeLocal pfEJB;
    private Game game;
    private UploadedFile image;
    private boolean uploaded;
    private String path;
    private String title;
    private String selectedCategory;
    private String description;
    private String platform_name;
    private String developer;
    private String voice_language;
    private String text_language;
    private String players;
    private String year;
    private String month;
    private String day;
    private Date release_date;

    @PostConstruct
    public void init() {
        game = new Game();
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelectedCategory() {
        return null;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getDescription() {
        return "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlatform_name() {
        return null;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getDeveloper() {
        return null;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getVoice_language() {
        return null;
    }

    public void setVoice_language(String voice_language) {
        this.voice_language = voice_language;
    }

    public String getText_language() {
        return null;
    }

    public void setText_language(String text_language) {
        this.text_language = text_language;
    }

    public String getPlayers() {
        return null;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getYear() {
        return null;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return null;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return null;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void upload() {
        saveImage();

        game = new Game();
        game.setTitle(title);
        game.setCategory(selectedCategory);
        game.setDescription(description);
        game.setPlatformName(pfEJB.findPlatformByName(platform_name));
        game.setProfileImage(path);
        game.setDeveloper(developer);
        game.setReleaseDate(getSqlDate());
        game.setVoiceLanguage(voice_language);
        game.setTextLanguage(text_language);
        game.setPlayers(players);
        
        /*System.out.println("***********************************************************" + selectedCategory);
        System.out.println("***********************************************************" + description);
        System.out.println("***********************************************************" + developer);
        System.out.println("***********************************************************" + title);
        System.out.println("***********************************************************" + pfEJB.findPlatformByName(platform_name).getName());
        System.out.println("***********************************************************" + players);
        System.out.println("***********************************************************" + path);
        System.out.println("***********************************************************" + text_language);
        System.out.println("***********************************************************" + voice_language);
        System.out.println("***********************************************************" + getSqlDate());*/

        gameEJB.create(game);
        toIndex();
    }

    public java.sql.Date getSqlDate() {
        java.sql.Date sqlDate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            release_date = formatter.parse(year + "-" + month + "-" + day);
            sqlDate = new java.sql.Date(release_date.getTime());

        } catch (ParseException ex) {
            Logger.getLogger(UploadGames.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlDate;
    }

    private void saveImage() {
        String edge = "";
        try {

            String tmp = image.getFileName();
            Path p = Paths.get(tmp);
            path = "game_images/profile_image/" + p.getFileName().toString();

            InputStream in = image.getInputstream();
            File f = new File("/Users/AvengedCocoX/NetBeansProjects/aw_videogames/src/main/webapp/" + path);
            //File f = new File("C:/Users/an/Documents/NetBeansProjects/aw_videogames/src/main/webapp/" + path);
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();
            in.close();
            uploaded = true;

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    //redigiré a la página de inicio
    public void toIndex() 
    {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        try 
        {
            ec.redirect(ec.getRequestContextPath());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
