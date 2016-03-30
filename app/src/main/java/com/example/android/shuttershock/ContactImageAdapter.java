package com.example.android.shuttershock;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ContactImageAdapter extends ArrayAdapter<Contact>{
	 Context context;
ImageHolder holder = null;
	    int layoutResourceId;   
	   // BcardImage data[] = null;
	    ArrayList<Contact> data=new ArrayList<Contact>();
	    public ContactImageAdapter(Context context, int layoutResourceId, ArrayList<Contact> data) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.data = data;
	    }

	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        //ImageHolder holder = null;
	       
	        if(row == null)
	        {
	            final LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);

	            holder = new ImageHolder();
	            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
	            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
				holder.textView = (TextView)row.findViewById(R.id.textView);
				holder.textView2 = (TextView)row.findViewById(R.id.textView2);
				holder.checkBox = (CheckBox)row.findViewById(R.id.checkBox);
				holder.textView3 = (TextView)row.findViewById(R.id.textView3);

	            row.setTag(holder);


				holder.txtTitle.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(inflater.getContext(), "txtClick" + position, Toast.LENGTH_SHORT).show();

					}

				});

				holder.checkBox.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

					}
				});
				holder.imgIcon.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(inflater.getContext(), "ImageClick" + position, Toast.LENGTH_SHORT).show();

						holder.imgIcon.setScaleType(ImageView.ScaleType.FIT_XY);
					}

				});




	        }
	        else
	        {
	            holder = (ImageHolder)row.getTag();
	        }
	       
	        Contact picture = data.get(position);
	        holder.txtTitle.setText(picture.imagePath);
			holder.textView.setText(picture.date);
			holder.textView2.setText(picture.cityW + "\n" + picture.zipCodeW + "\n" + picture.countryW);
			DataBaseHandler db = new DataBaseHandler((Activity)context);
			 Album abs = db.getAlbum(picture.getCompany_id());
			holder.textView3.setText("album: " + abs.getAlbum_name());
			db.close();
	        //convert byte to bitmap take from contact class
	        
	       /* byte[] outImage=picture._image;
	        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
	        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
	        holder.imgIcon.setImageBitmap(theImage); */

			Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/ShutterShockFolder/" + picture.getImage() +".png");
			holder.imgIcon.setImageBitmap(bitmap);




	       return row;
	       
	    }
	   
	    public class ImageHolder
	    {
	        ImageView imgIcon;
	        TextView txtTitle;
			TextView textView;
			TextView textView2;
			CheckBox checkBox;
			TextView textView3;


		}
	}

