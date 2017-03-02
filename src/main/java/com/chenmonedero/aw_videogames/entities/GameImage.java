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
@Table(name = "game_image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GameImage.findAll", query = "SELECT g FROM GameImage g"),
    @NamedQuery(name = "GameImage.findByIdimageGallery", query = "SELECT g FROM GameImage g WHERE g.idimageGallery = :idimageGallery"),
    @NamedQuery(name = "GameImage.findByUrl", query = "SELECT g FROM GameImage g WHERE g.url = :url")})
public class GameImage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imageGallery")
    private Integer idimageGallery;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "game_title", referencedColumnName = "title")
    @ManyToOne(optional = false)
    private Game gameTitle;

    public GameImage() {
    }

    public GameImage(Integer idimageGallery) {
        this.idimageGallery = idimageGallery;
    }

    public GameImage(Integer idimageGallery, String url) {
        this.idimageGallery = idimageGallery;
        this.url = url;
    }

    public Integer getIdimageGallery() {
        return idimageGallery;
    }

    public void setIdimageGallery(Integer idimageGallery) {
        this.idimageGallery = idimageGallery;
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
        hash += (idimageGallery != null ? idimageGallery.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GameImage)) {
            return false;
        }
        GameImage other = (GameImage) object;
        if ((this.idimageGallery == null && other.idimageGallery != null) || (this.idimageGallery != null && !this.idimageGallery.equals(other.idimageGallery))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chenmonedero.aw_videogames.entities.GameImage[ idimageGallery=" + idimageGallery + " ]";
    }
    
}
