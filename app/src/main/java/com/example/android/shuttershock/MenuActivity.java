package com.example.android.shuttershock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 2/29/2016.
 */
public class MenuActivity extends Activity {

    Contact contact;
    ImageView imageView;
    ListView dataList;
    ArrayList<Album> albumArry = new ArrayList<Album>();

    AlbumAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        adapter = new AlbumAdapter(this, R.layout.activity_album_list,
                albumArry);
        dataList = (ListView) findViewById(R.id.albumList);



        readAlbums();
        dataList.setAdapter(adapter);

        Intent intent = getIntent();
        contact = (Contact) intent.getExtras().getSerializable("picture");


    }


    public void readAlbums() {
        albumArry.clear();
        DataBaseHandler db = new DataBaseHandler(this);
        List<Album> albums = db.getAllAlbums();
        for (Album al : albums) {
            String log = "ID:" + al.getAlbum_Id()
                    + " ,Name: " + al.getAlbum_name();

            // Writing Contacts to log
            Log.d("Result: ", log);
            //add contacts data in arrayList
            albumArry.add(al);

        }
        db.close();
    }




}
