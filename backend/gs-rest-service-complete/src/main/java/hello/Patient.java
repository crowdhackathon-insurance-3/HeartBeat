package hello;

public class Patient {
	
	
	String name;
	String surname;
	String yearofbirth;
	String gender;
	String language;
	String mobileId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getYearofbirth() {
		return yearofbirth;
	}
	public void setYearofbirth(String yearofbirth) {
		this.yearofbirth = yearofbirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getMobileId() {
		return mobileId;
	}
	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}
	public Patient(String name, String surname, String yearofbirth, String gender, String language) {
		super();
		this.name = name;
		this.surname = surname;
		this.yearofbirth = yearofbirth;
		this.gender = gender;
		this.language = language;
	}
	
	

}
