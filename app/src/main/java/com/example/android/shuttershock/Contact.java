package com.example.android.shuttershock;

//this class holds the data for a single picture
public class Contact {

	// private variables
	int _id;
//	String _name;
	String imagePath;
	String date;
	String zipCodeW;
	String countryW;
	String cityW;
	private int company_id;
	// Empty constructor
	public Contact() {

	}
	//comment test

	// constructor
	/*public Contact(int keyId, String name, byte[] image) {
		this._id = keyId;
		this._name = name;
		this._image = image;

	}*/

	public Contact(int keyId, String imagePath, String date, String cityW, String zipCodeW, String countryW, int company_id) {
		this._id = keyId;
		this.imagePath = imagePath;
		this.date = date;
		this.cityW = cityW;
		this.zipCodeW = zipCodeW;
		this.countryW = countryW;
		this.company_id = company_id;

	}
/*
	// constructor
	public Contact(String contactID, String imagePath) {
		this.imagePath = imagePath;

	}

*/	// constructor
	public Contact(String imagePath, String date, String cityW, String zipCodeW, String countryW, int company_id) {
		this.imagePath = imagePath;
		this.date = date;
		this.countryW = countryW;
		this.zipCodeW = zipCodeW;
		this.cityW = cityW;
		this.company_id = company_id;
	}


	//getting date

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}
	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int keyId) {
		this._id = keyId;
	}
/*
	// getting name
	public String getName() {
		return this._name;
	}

	// setting name
	public void setName(String name) {
		this._name = name;
	}
*/
	// getting phone number
	public String getImage() {
		return this.imagePath;
	}

	// setting phone number
	public void setImage(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getCountry() {
		return this.countryW;
	}
	public void setCountry(String countryW){
		this.countryW = countryW;
	}

	public String getZipCode() {
		return this.zipCodeW;
	}
	public void setZipCode(String zipCodeW){
		this.zipCodeW = zipCodeW;
	}
	public String getCity(){
		return this.cityW;
	}
	public void setCity(String cityW){
		this.cityW = cityW;
	}
	public int getCompany_id(){
		return this.company_id;
	}
	public void setCompany_id(int company_id){
		this.company_id = company_id;
	}

}
