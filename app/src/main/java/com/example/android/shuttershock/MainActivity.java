/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.shuttershock;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
//import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemLongClickListener {
   //Uncomment
   // private DrawerLayout mDrawerLayout;
    //private ListView mDrawerList;
   // private ActionBarDrawerToggle mDrawerToggle;
    //Uncomment

    ////////////Location Instance Variables///////////////
    AppLocationService appLocationService;
    /////////////////End of Location Instance Variables////////////////

   // private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mfilterTitles;
    Bitmap bmp2;
    private boolean picTaken = false;
    ArrayList<Contact> imageArry = new ArrayList<Contact>();                                        //Array list that holds pictures

    ContactImageAdapter adapter;
    //TextView textView;


    ///Drawer Test////////////////////////////////////////////
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    //Drawer Test/////////////////////////////////////////////////////
    int i = 12;
    private static final int SELECT_PICTURE = 1; //intent code to select a picture
    String picPathData = "";

    ListView dataList;  //where the pictures and text are populated

    Contact contact = new Contact();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///////////////////Location Things//////////////////////////////////////

        appLocationService = new AppLocationService(MainActivity.this);

        getCurrentLocation();
        //////////////////////////////////////////////////////////////////////
        adapter = new ContactImageAdapter(this, R.layout.screen_list,
                imageArry);
        dataList = (ListView) findViewById(R.id.list);
        dataList.setAdapter(adapter);

        if (adapter.isEmpty()) {
           /* Toast.makeText(getApplicationContext(), "Adapter Empty",
                    Toast.LENGTH_LONG).show(); */
        }
        if (!adapter.isEmpty()) {
            adapter.clear();
            readContacts();
        }
        /**  mTitle = mDrawerTitle = getTitle();
        mfilterTitles = getResources().getStringArray(R.array.filters_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mfilterTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle   (   */
       //       this,                  /* host Activity */
        //        mDrawerLayout,         /* DrawerLayout object */
          //      R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
            //    R.string.drawer_open,  /* "open drawer" description for accessibility */
              //  R.string.drawer_close  /* "close drawer" description for accessibility */
        /* ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                //drawerView.bringToFront();
                mDrawerList.bringToFront();
                mDrawerLayout.requestLayout();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
*/
       /* if (savedInstanceState == null) {
            selectItem(0);
        }
*/
        dataList.setAdapter(adapter);

        readContacts();
        // textView = (TextView)findViewById(R.id.textView);



        dataList.setOnItemLongClickListener(this);





        ////Drawer Test///////////////////////

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();


        final String[] osArray = { "Name", "Date", "Location", "Album" };
            mAdapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item, osArray);
            mDrawerList.setAdapter(mAdapter);

        setupDrawer();

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ///Drawer Test//////////////



        //When an item is clicked it in the listview an intent is sent to Fullscreen.class
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), Fullscreen.class);

                final Contact contact = (Contact) parent.getAdapter().getItem(position);

                i.putExtra("picture", contact);
                startActivity(i);

            }
        });

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this, "Name", Toast.LENGTH_SHORT).show();
                        readContacts();
                        //getSupportActionBar().setTitle(osArray[position]);
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Date", Toast.LENGTH_SHORT).show();
                        readDates();
                        //getSupportActionBar().setTitle(osArray[position]);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Location", Toast.LENGTH_SHORT).show();
                        readLocation();
                        //getSupportActionBar().setTitle(osArray[position]);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Album", Toast.LENGTH_SHORT).show();
                        //getSupportActionBar().setTitle(osArray[position]);
                        break;

                }

            }
        });
    }


    //When the user longclicks a dialog box is openened to let the user know to either delete or move the picture
    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                  final int pos, long id) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(),
                "Pos:" + pos, Toast.LENGTH_SHORT).show();
        //casts listview at current location as a contact
       final Contact contact4 = (Contact)arg0.getAdapter().getItem(pos);


        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);


        alertDialog.setMessage("Do you want to delete this photo?");

        //When user clicks yes, they will delete contact from database
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                DataBaseHandler db = new DataBaseHandler(alertDialog.getContext());
                db.deleteContact(contact4);
                db.close();
                adapter.remove(contact4);
                //imageArry.remove(pos);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog2, int which) {


                final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MainActivity.this);


                alertDialog2.setMessage("Would you like to move this photo?");

                alertDialog2.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                        //Contact contact4 = (Contact)arg0.getAdapter().getItem(pos);
                        intent.putExtra("picture", contact4);
                        startActivity(intent);
                    }
                });

                alertDialog2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog2.show();

            }
        });

       alertDialog.show();

       /* Intent intent = new Intent(this, MenuActivity.class);
        //Contact contact4 = (Contact)arg0.getAdapter().getItem(pos);
        intent.putExtra("picture", contact4);
        startActivity(intent);*/
        return true;
    }






/////////////////////Drawer Test////////////////////



    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                mDrawerList.bringToFront();
                mDrawerLayout.requestLayout();
                 }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
 ///////Drawer Test////////////////////////////////


///////////////////////////////////////////////////////
    //When button is pressed it calls takePic to take the picture using an intent
    private void takePic() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 2);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            //Converts photo taken into a bitmap
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            //Creates a new instance of a database
            DataBaseHandler db = new DataBaseHandler(this);


            String randomStringForPic = ""; //Name for picture, subject to change
            int length = 12;
             //Creates a randomString of characters for image name
            randomStringForPic = randomString(length);
            picPathData = randomStringForPic;


            String y = makeDate(); //Gets current date
            getCurrentLocation();
            contact.setImage(picPathData);
            contact.setDate(y);
            contact.setCompany_id(1);
            //contact = new Contact(picPathData, y);
            db.addContact(contact);

            db.close();

            //Create Folder
            File folder = new File(Environment.getExternalStorageDirectory().toString() + "/ShutterShockFolder");
            folder.mkdirs();

            //Save the path as a string value
            String extStorageDirectory = folder.toString();

            //Create New file and name it Image2.PNG
            File image = new File(extStorageDirectory, picPathData + ".PNG");

            Uri imageUri = Uri.fromFile(image);

            //Send a broadcast so that the image that was just taken is saved to the users SD card
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imageUri));


            boolean success = false;

            FileOutputStream outStream;
            try {

                //COnverts the image into a smaller form while still trying to keep the quality of the image
                outStream = new FileOutputStream(image);
                photo.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        /* 100 to keep full quality of the image */

                outStream.flush();
                outStream.close();
                success = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //If image is saved tell the user and if not then tell the user
            if (success) {
                Toast.makeText(getApplicationContext(), "Image Saved",
                        Toast.LENGTH_LONG).show();
                picTaken = true;
            } else {
                Toast.makeText(getApplicationContext(),
                        "Photo not saved", Toast.LENGTH_LONG).show();
            }


            readContacts();
            // textView = (TextView)findViewById(R.id.textView);

            //dataList.setAdapter(adapter);

        } else if (requestCode == SELECT_PICTURE) {

            if (data != null && resultCode == RESULT_OK) {

                Uri selectedImage = data.getData();

                if (selectedImage == null) {
                    Log.d("Status", "data is null");
                } else {
                    Log.d("Status", "data is not null");
                }
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                if (bmp2 != null && !bmp2.isRecycled()) {
                    bmp2 = null;
                }

                Log.d("Status:", filePath);
                // textView.setText(filePath);
                bmp2 = BitmapFactory.decodeFile(filePath);
                //ivGalImg.setBackgroundResource(0);
                //ivGalImg.setImageBitmap(bmp);
                DataBaseHandler db = new DataBaseHandler(this);

                String randomStringForPic = "";
                int length = 12;
                randomString(length);
                randomStringForPic = randomString(length);
                picPathData = randomStringForPic;

                //Adds the picture into the contact class
                // db.addContact(new Contact(picPathData);
                String y = makeDate();
                getCurrentLocation();
                contact.setImage(picPathData);
                contact.setDate(y);
                contact.setCompany_id(1);
                //contact = new Contact(picPathData, y);
                db.addContact(contact);

                db.close();
                //Create Folder
                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/ShutterShockFolder");
                folder.mkdirs();

                //Save the path as a string value
                String extStorageDirectory = folder.toString();

                //Create New file and name it Image2.PNG
                File image = new File(extStorageDirectory, picPathData + ".PNG");


                boolean success = false;

                FileOutputStream outStream;
                try {

                    //COnverts the image into a smaller form while still trying to keep the quality of the image
                    outStream = new FileOutputStream(image);
                    bmp2.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        /* 100 to keep full quality of the image */

                    outStream.flush();
                    outStream.close();
                    success = true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //If image is saved tell the user and if not then tell the user
                if (success) {
                    Toast.makeText(getApplicationContext(), "Image Saved",
                            Toast.LENGTH_LONG).show();
                    picTaken = true;
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Photo not saved", Toast.LENGTH_LONG).show();
                }

                readContacts();
                // textView = (TextView)findViewById(R.id.textView);

                //dataList.setAdapter(adapter);
            }
        }


    }

    //Gets all Images and adds them to the imageArray
    public void readContacts() {
        adapter.clear();
        imageArry.clear();
        DataBaseHandler db = new DataBaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            String log = "ID:" + cn.getID()
                    + " ,Image: " + cn.getImage();

            // Writing Contacts to log
            Log.d("Result: ", log);
            //add contacts data in arrayList
            imageArry.add(cn);

        }
        adapter.addAll(imageArry);

        db.close();
    }

    //Reads the date of images
    public void readDates() {
        adapter.clear();
        imageArry.clear();
        DataBaseHandler db = new DataBaseHandler(this);
        List<Contact> contacts = db.getContactsByDate();
        for (Contact cn : contacts) {
            String log = "ID:" + cn.getID()
                    + " ,Image: " + cn.getDate();

            // Writing Contacts to log
            Log.d("Result: ", log);
            //add contacts data in arrayList
            imageArry.add(cn);


        }
        adapter.addAll(imageArry);
        db.close();
    }

    public void readLocation() {
        adapter.clear();
        imageArry.clear();
        DataBaseHandler db = new DataBaseHandler(this);
        List<Contact> contacts = db.getContactsByDate();
        for (Contact cn : contacts) {
            String log = "ID:" + cn.getID()
                    + " ,Image: " + cn.getCity();

            // Writing Contacts to log
            Log.d("Result: ", log);
            //add contacts data in arrayList
            imageArry.add(cn);


        }
        adapter.addAll(imageArry);
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.take_pic:

                takePic();
                //galleryAddPic();
                return true;
            case R.id.upload:
           /* Intent intentx = new Intent();
            intentx.setType("image/*");
            intentx.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(intentx, "Select Picture"),
                    SELECT_PICTURE);*/

                final Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, SELECT_PICTURE);
                //galleryAddPic();
                return true;
            case R.id.look:

                return true;
            case R.id.action_websearch:
                // create intent to perform web search for this filter
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.createfolder:
                createFolder();
                return true;
            case R.id.delete:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            //selectItem(position);
            Toast.makeText(MainActivity.this, ((TextView) view).getText(), Toast.LENGTH_LONG).show();
            mDrawerLayout.closeDrawer(mDrawerLayout);
        }
    }


    private void selectItem(int position) {


        switch (position) {
            case 0:
                //TODO: ascending vs descending ??

                Toast.makeText(getApplicationContext(), "Date Selected",
                        Toast.LENGTH_LONG).show();
                if (!adapter.isEmpty()) {
                    readDates();
                    adapter = new ContactImageAdapter(this, R.layout.screen_list,
                            imageArry);
                    dataList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                break;

            case 1:
        	/*TODO: JB -- retrieve location data and organize imageArry
        	 * to reflect such data. create the new instance of the adapter
        	 * and set it to the datalist (for viewing)
        	 */
                Toast.makeText(getApplicationContext(), "Location",
                        Toast.LENGTH_LONG).show();
                break;
            case 2:
                //TODO: NS -- get album data, sort by name, set adapter

                Toast.makeText(getApplicationContext(), "Album",
                        Toast.LENGTH_LONG).show();
                break;
            case 3:

                Toast.makeText(getApplicationContext(), "File Size",
                        Toast.LENGTH_LONG).show();
                //TODO: Unknown -- get file size, sort in numerical order, set adaptrt
                break;
            default:
                //TODO: return error?
                break;


        }
        // update the main content by replacing fragments
       /* Fragment fragment = new filterFragment();
        Bundle args = new Bundle();
        args.putInt(filterFragment.ARG_filter_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mfilterTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
*/
/*
            if (position == 0) {
                readDates();
                dataList.setAdapter(adapter);
            } */

    }

/*
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

  */  /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a filter
     */
    public static class filterFragment extends Fragment {
        public static final String ARG_filter_NUMBER = "filter_number";

        public filterFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
            int i = getArguments().getInt(ARG_filter_NUMBER);
            String filter = getResources().getStringArray(R.array.filters_array)[i];

            int imageId = getResources().getIdentifier(filter.toLowerCase(Locale.getDefault()),
                    "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(filter);
            return rootView;
        }
    }


    public void createFolder(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        final DataBaseHandler db = new DataBaseHandler(this);

        alertDialog.setMessage("Folder Name");
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);


        alertDialog.setPositiveButton("Finish",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                      // DataBaseHandler db = new DataBaseHandler();
                        Album album = new Album();
                        album.setAlbum_name(input.getText().toString());
                        db.addAlbum(album);
                        }

                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        db.close();
        alertDialog.show();
    }



    public String randomString(int length) {

        //Generates a random name for the image that the user has taken

        //THis is the alphabet that I will be using for the name
        final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();


        //Use a string buiolder to build a sequence of random numbers based on the length that is passed in the parameter
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public String makeDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        String x = dateFormat.format(date).toString();


        return x;
    }

    //Uses geocoding to get current location
    private void getCurrentLocation() {


        Location location = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getApplicationContext(), new GeocoderHandler());

        }
        else{
            Log.e("tag", "error");
        }

    }
    //Takes location data and casts it as city, zipCode, and country
    private class GeocoderHandler extends Handler {
        private String city;
        private String zipCode;
        private String country;
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            String[] lines = locationAddress.toString().split("\\n");
                // process the line
            Scanner scan = new Scanner(locationAddress);
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
            city = scan.nextLine();
            Log.d("city", city);
            zipCode = scan.nextLine();
            Log.d("zipcode", zipCode);
            country = scan.nextLine();
            Log.d("country",country);
         Log.d("tags", locationAddress);
            contact.setCountry(country);
            contact.setZipCode(zipCode);
            contact.setCity(city);
        }

        public String getCountry() {
            return this.country;
        }
        public void setCountry(String country){
            this.country = country;
        }

        public String getZipCode() {
            return this.zipCode;
        }
        public void setZipCode(String zipCode){
            this.zipCode = zipCode;
        }
        public String getCity(){
            return this.city;
        }
        public void setCity(String city){
            this.city = city;
        }
    }
}

