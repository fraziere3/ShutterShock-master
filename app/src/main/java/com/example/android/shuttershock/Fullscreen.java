package com.example.android.shuttershock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by shortn1 on 2/15/2016.
 */
public class Fullscreen extends Activity {
    private ImageView imageView;
    Contact contact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fullscreen);

        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        contact = (Contact) intent.getExtras().getSerializable("picture");

        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/ShutterShockFolder/" + contact.getImage() + ".png");
        imageView.setImageBitmap(bitmap);
    }
}
