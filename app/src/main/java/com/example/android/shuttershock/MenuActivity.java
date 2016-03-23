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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 2/29/2016.
 */

//This activity is when the user wants to move an image to another folder
//They can see which folder they moved it to
public class MenuActivity extends Activity {

    Contact contact; //Class that is received fro intent
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



        readAlbums();   //Gets all ablums
        dataList.setAdapter(adapter);  //Puts the albums into the List View called datalist

        Intent intent = getIntent();  //Receives Contact from Main Activity when the user longclicked on a photo
        contact = (Contact) intent.getExtras().getSerializable("picture");


        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(getApplicationContext(), "Image" + id + "clicked",
                        Toast.LENGTH_LONG).show();

            DataBaseHandler db = new DataBaseHandler(getApplicationContext());
                final Album albumm = (Album) parent.getAdapter().getItem(position);
                contact.setCompany_id(albumm.getAlbum_Id());
                db.updateContact(contact);
                finish();
            }
        });

    }


    public void readAlbums() {

        int counter = 0;
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
            counter++;

        }

        db.close();
    }




}
