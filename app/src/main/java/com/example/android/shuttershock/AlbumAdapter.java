package com.example.android.shuttershock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eric on 3/15/2016.
 */
public class AlbumAdapter extends ArrayAdapter<Album>{


  //  private final String[] values;

    Context context;

    int layoutResourceId;

    ArrayList<Album> data =new ArrayList<Album>();
            public AlbumAdapter(Context context, int layoutResourceId, ArrayList<Album> data) {
                super(context, layoutResourceId, data);
                this.layoutResourceId = layoutResourceId;
                this.context = context;
                this.data = data;
            }

            @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_album_list, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.albumText);

                Album album = data.get(position);
                textView.setText(album.getAlbum_name());

            return rowView;
        }
    }