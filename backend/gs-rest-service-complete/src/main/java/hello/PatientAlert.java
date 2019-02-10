package hello;

public class PatientAlert {
	
	String id;
	String latitude;
	String longtitude;
	String problem;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public PatientAlert(String id, String latitude, String longtitude, String problem) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.problem = problem;
	}
	
	

}
