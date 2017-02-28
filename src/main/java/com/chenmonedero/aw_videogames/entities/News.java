/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AvengedCocoX
 */
@Entity
@Table(name = "news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n"),
    @NamedQuery(name = "News.findByIdNews", query = "SELECT n FROM News n WHERE n.idNews = :idNews"),
    @NamedQuery(name = "News.findByNewsTittle", query = "SELECT n FROM News n WHERE n.newsTittle = :newsTittle"),
    @NamedQuery(name = "News.findByNewsImage", query = "SELECT n FROM News n WHERE n.newsImage = :newsImage"),
    @NamedQuery(name = "News.findByNewsVideo", query = "SELECT n FROM News n WHERE n.newsVideo = :newsVideo")})
public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_news")
    private Integer idNews;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "news_tittle")
    private String newsTittle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "news_text")
    private String newsText;
    @Size(max = 250)
    @Column(name = "news_image")
    private String newsImage;
    @Size(max = 250)
    @Column(name = "news_video")
    private String newsVideo;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;
    @JoinColumn(name = "id_game", referencedColumnName = "id_game")
    @ManyToOne
    private Game idGame;

    public News() {
    }

    public News(Integer idNews) {
        this.idNews = idNews;
    }

    public News(Integer idNews, String newsTittle, String newsText) {
        this.idNews = idNews;
        this.newsTittle = newsTittle;
        this.newsText = newsText;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public String getNewsTittle() {
        return newsTittle;
    }

    public void setNewsTittle(String newsTittle) {
        this.newsTittle = newsTittle;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsVideo() {
        return newsVideo;
    }

    public void setNewsVideo(String newsVideo) {
        this.newsVideo = newsVideo;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Game getIdGame() {
        return idGame;
    }

    public void setIdGame(Game idGame) {
        this.idGame = idGame;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNews != null ? idNews.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if ((this.idNews == null && other.idNews != null) || (this.idNews != null && !this.idNews.equals(other.idNews))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chenmonedero.aw_videogames.entities.News[ idNews=" + idNews + " ]";
    }
    
}
