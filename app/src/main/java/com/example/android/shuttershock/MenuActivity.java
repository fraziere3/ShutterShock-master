package com.example.android.shuttershock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by eric on 2/29/2016.
 */
public class MenuActivity extends Activity implements View.OnClickListener{

    Button buttonMove;
    Button buttonDelete;
    Contact contact;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        buttonMove = (Button) findViewById(R.id.button2);
        buttonDelete = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView2);

        buttonDelete.setOnClickListener(this);
        buttonMove.setOnClickListener(this);

        Intent intent = getIntent();
        contact = (Contact) intent.getExtras().getSerializable("picture");


        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/ShutterShockFolder/" + contact.getImage() + ".png");
        //holder.imgIcon.setImageBitmap(bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button:

                DataBaseHandler db = new DataBaseHandler(this);
                db.deleteContact(contact);
                db.close();
                Toast.makeText(getApplicationContext(),
                        "Picture Deleted", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button2:
                Toast.makeText(getApplicationContext(),
                        "move", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

}
