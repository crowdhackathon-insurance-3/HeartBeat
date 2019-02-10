package hello;

public class DoctorPosition {

	
	String id;
	String longtitude;
String latitude;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLongtitude() {
	return longtitude;
}
public void setLongtitude(String longtitude) {
	this.longtitude = longtitude;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public DoctorPosition(String id, String longtitude, String latitude) {
	super();
	this.id = id;
	this.longtitude = longtitude;
	this.latitude = latitude;
}	
	


}
