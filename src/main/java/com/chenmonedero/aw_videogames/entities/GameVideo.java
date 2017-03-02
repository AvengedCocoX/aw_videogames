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
@Table(name = "game_video")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GameVideo.findAll", query = "SELECT g FROM GameVideo g"),
    @NamedQuery(name = "GameVideo.findByIdvideoGallery", query = "SELECT g FROM GameVideo g WHERE g.idvideoGallery = :idvideoGallery"),
    @NamedQuery(name = "GameVideo.findByUrl", query = "SELECT g FROM GameVideo g WHERE g.url = :url")})
public class GameVideo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_videoGallery")
    private Integer idvideoGallery;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "game_title", referencedColumnName = "title")
    @ManyToOne(optional = false)
    private Game gameTitle;

    public GameVideo() {
    }

    public GameVideo(Integer idvideoGallery) {
        this.idvideoGallery = idvideoGallery;
    }

    public GameVideo(Integer idvideoGallery, String url) {
        this.idvideoGallery = idvideoGallery;
        this.url = url;
    }

    public Integer getIdvideoGallery() {
        return idvideoGallery;
    }

    public void setIdvideoGallery(Integer idvideoGallery) {
        this.idvideoGallery = idvideoGallery;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Game getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(Game gameTitle) {
        this.gameTitle = gameTitle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvideoGallery != null ? idvideoGallery.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GameVideo)) {
            return false;
        }
        GameVideo other = (GameVideo) object;
        if ((this.idvideoGallery == null && other.idvideoGallery != null) || (this.idvideoGallery != null && !this.idvideoGallery.equals(other.idvideoGallery))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chenmonedero.aw_videogames.entities.GameVideo[ idvideoGallery=" + idvideoGallery + " ]";
    }
    
}
