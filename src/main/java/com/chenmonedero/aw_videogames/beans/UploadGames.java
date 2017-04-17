/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chenmonedero.aw_videogames.beans;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author an
 */
@ManagedBean
@SessionScoped
public class UploadGames {
    private Part image;
    private boolean uploaded;
    private String path;
    
    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
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
    
    public void doUpload(){
        try{
            uploaded=false;
            InputStream in = image.getInputStream();
            File f = new File("/Users/an/Documents/NetBeansProjects/aw_videogames/src/main/webapp/game_images/profile_image/"+image.getSubmittedFileName());
            System.out.println(f.getAbsolutePath());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            System.out.println("0");
            path = f.getAbsolutePath();
            System.out.println("1");
            uploaded=true;
            
            
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    
    
}
