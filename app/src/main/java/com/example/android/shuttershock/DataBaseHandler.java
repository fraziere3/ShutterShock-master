package com.example.android.shuttershock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "imagedb";

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	//private static final String KEY_NAME = "name";
	private static final String DATE = "date";
	private static final String KEY_IMAGE = "image";
	private static final String CITY = "city";
	private static final String ZIPCODE = "zipcode";
	private static final String COUNTRY = "country";

	// Album table name
	private static final String TABLE_ALBUM = "album";

	//Album Table Columns names
	private static final String ALBUM_ID = "albumId";
	private static final String ALBUM_IMAGE = "albumImage";
	private static final String ALBUM_NAME = "albumName";
	private static final String PICTURE_ID = "picture_id";

	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_IMAGE + " TEXT," + DATE + " TEXT," + CITY + " TEXT," +
				ZIPCODE + " TEXT," + COUNTRY + " TEXT" +");";

		String CREATE_ALBUM_TABLE = "CREATE TABLE " + TABLE_ALBUM + "("
				+ ALBUM_ID + " INTEGER PRIMARY KEY,"
				+ PICTURE_ID + " TEXT," + ALBUM_IMAGE + " TEXT," + ALBUM_NAME + " TEXT" +");";

		db.execSQL(CREATE_CONTACTS_TABLE);
		db.execSQL(CREATE_ALBUM_TABLE);
	}


	public void deleteAll()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete("contacts", null, null);
		db.delete("album", null, null);

	}
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALBUM);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	public// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//values.put(KEY_NAME, contact._name); // Contact Name
		values.put(KEY_IMAGE, contact.imagePath); // Contact Phone
		values.put(DATE, contact.date);
		values.put(CITY, contact.cityW);
		values.put(ZIPCODE, contact.zipCodeW);
		values.put(COUNTRY, contact.getCountry());
		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	public// Adding new album
	void addAlbum(Album album) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//values.put(KEY_NAME, contact._name); // Contact Name
		values.put(ALBUM_ID, album.getAlbum_Id()); // Contact Phone
		values.put(PICTURE_ID, album.getPicture_id());
		values.put(ALBUM_NAME, album.getAlbum_name());
		values.put(ALBUM_IMAGE, album.getPicture_path());
		// Inserting Row
		db.insert(TABLE_ALBUM, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	Contact getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
						KEY_IMAGE, DATE}, KEY_ID + "=?",
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5));

		// return contact
		return contact;

	}

	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM contacts ORDER BY image";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				//contact.setName(cursor.getString(1));
				contact.setImage(cursor.getString(1));
				// Adding contact to list
				contact.setDate(cursor.getString(2));
				contact.setCity(cursor.getString(3));
				contact.setZipCode(cursor.getString(4));
				contact.setCountry(cursor.getString(5));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}


		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}

	public List<Contact> getContactsByDate() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM contacts ORDER BY date";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				//contact.setName(cursor.getString(1));
				contact.setImage(cursor.getString(1));
				// Adding contact to list
				contact.setDate(cursor.getString(2));
				contact.setCity(cursor.getString(3));
				contact.setZipCode(cursor.getString(4));
				contact.setCountry(cursor.getString(5));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}


		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}

	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//values.put(KEY_NAME, contact.getName());
		values.put(KEY_IMAGE, contact.getImage());
		values.put(DATE, contact.getDate());
		values.put(CITY, contact.cityW);
		values.put(ZIPCODE, contact.zipCodeW);
		values.put(COUNTRY, contact.countryW);

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });

	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}

	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
