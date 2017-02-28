/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AvengedCocoX
 */
@Entity
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findByIdGame", query = "SELECT g FROM Game g WHERE g.idGame = :idGame"),
    @NamedQuery(name = "Game.findByTitle", query = "SELECT g FROM Game g WHERE g.title = :title"),
    @NamedQuery(name = "Game.findByCategory", query = "SELECT g FROM Game g WHERE g.category = :category"),
    @NamedQuery(name = "Game.findByProfileImage", query = "SELECT g FROM Game g WHERE g.profileImage = :profileImage"),
    @NamedQuery(name = "Game.findByDeveloper", query = "SELECT g FROM Game g WHERE g.developer = :developer"),
    @NamedQuery(name = "Game.findByReleaseDate", query = "SELECT g FROM Game g WHERE g.releaseDate = :releaseDate"),
    @NamedQuery(name = "Game.findByVoiceLanguage", query = "SELECT g FROM Game g WHERE g.voiceLanguage = :voiceLanguage"),
    @NamedQuery(name = "Game.findByTextLanguage", query = "SELECT g FROM Game g WHERE g.textLanguage = :textLanguage"),
    @NamedQuery(name = "Game.findByPlayers", query = "SELECT g FROM Game g WHERE g.players = :players")})
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_game")
    private Integer idGame;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "profile_image")
    private String profileImage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "developer")
    private String developer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "voice_language")
    private String voiceLanguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "text_language")
    private String textLanguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "players")
    private String players;
    @OneToMany(mappedBy = "idGame")
    private Collection<News> newsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGame")
    private Collection<Valoration> valorationCollection;
    @JoinColumn(name = "id_platform", referencedColumnName = "id_platform")
    @ManyToOne(optional = false)
    private Platform idPlatform;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGame")
    private Collection<GameVideo> gameVideoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGame")
    private Collection<GameImage> gameImageCollection;

    public Game() {
    }

    public Game(Integer idGame) {
        this.idGame = idGame;
    }

    public Game(Integer idGame, String title, String category, String description, String profileImage, String developer, Date releaseDate, String voiceLanguage, String textLanguage, String players) {
        this.idGame = idGame;
        this.title = title;
        this.category = category;
        this.description = description;
        this.profileImage = profileImage;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.voiceLanguage = voiceLanguage;
        this.textLanguage = textLanguage;
        this.players = players;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoiceLanguage() {
        return voiceLanguage;
    }

    public void setVoiceLanguage(String voiceLanguage) {
        this.voiceLanguage = voiceLanguage;
    }

    public String getTextLanguage() {
        return textLanguage;
    }

    public void setTextLanguage(String textLanguage) {
        this.textLanguage = textLanguage;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    @XmlTransient
    public Collection<News> getNewsCollection() {
        return newsCollection;
    }

    public void setNewsCollection(Collection<News> newsCollection) {
        this.newsCollection = newsCollection;
    }

    @XmlTransient
    public Collection<Valoration> getValorationCollection() {
        return valorationCollection;
    }

    public void setValorationCollection(Collection<Valoration> valorationCollection) {
        this.valorationCollection = valorationCollection;
    }

    public Platform getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Platform idPlatform) {
        this.idPlatform = idPlatform;
    }

    @XmlTransient
    public Collection<GameVideo> getGameVideoCollection() {
        return gameVideoCollection;
    }

    public void setGameVideoCollection(Collection<GameVideo> gameVideoCollection) {
        this.gameVideoCollection = gameVideoCollection;
    }

    @XmlTransient
    public Collection<GameImage> getGameImageCollection() {
        return gameImageCollection;
    }

    public void setGameImageCollection(Collection<GameImage> gameImageCollection) {
        this.gameImageCollection = gameImageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGame != null ? idGame.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.idGame == null && other.idGame != null) || (this.idGame != null && !this.idGame.equals(other.idGame))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chenmonedero.aw_videogames.entities.Game[ idGame=" + idGame + " ]";
    }
    
}
