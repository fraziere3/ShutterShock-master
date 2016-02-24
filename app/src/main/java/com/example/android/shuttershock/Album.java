package com.example.android.shuttershock;

/**
 * Created by eric on 2/17/2016.
 */
public class Album {


    // private variables
    int album_id;
    //	String _name;
    String picture_path;
    String album_name;
    int picture_id;

    // Empty constructor
    public Album() {
    }

    public Album(int album_id, String picture_path, int picture_id, String album_name) {
        this.album_id = album_id;
        this.picture_path = picture_path;
        this.picture_id = picture_id;
        this.album_name = album_name;
    }

    public int getAlbum_Id(){return album_id;}
    public void setAlbum_id(int album_id){
        this.album_id = album_id;
    }
    public String getPicture_path(){
        return  picture_path;
    }
    public void setPicture_path(String picture_path){
        this.picture_path = picture_path;
    }
    public int getPicture_id(){
        return picture_id;
    }
    public void setPicture_id(int picture_id){
        this.picture_id = picture_id;
    }
    public String getAlbum_name(){
        return album_name;
    }
    public void setAlbum_name(String album_name){
        this.album_name = album_name;
    }
}