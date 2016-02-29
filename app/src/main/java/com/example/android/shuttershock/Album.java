package com.example.android.shuttershock;

/**
 * Created by eric on 2/17/2016.
 */
//this class holds the data for an album
public class Album {


    // private variables
    int album_id;
    //	String _name;

    String album_name;


    // Empty constructor
    public Album() {
    }
    public Album(String album_name){
        this.album_name = album_name;
    }

    public Album(int album_id,  String album_name) {
        this.album_id = album_id;
        this.album_name = album_name;
    }

    public int getAlbum_Id(){return album_id;}
    public void setAlbum_id(int album_id){
        this.album_id = album_id;
    }

    public String getAlbum_name(){
        return album_name;
    }
    public void setAlbum_name(String album_name){
        this.album_name = album_name;
    }
}