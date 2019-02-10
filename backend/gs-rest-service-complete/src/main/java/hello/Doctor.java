package hello;

public class Doctor {

	String name;
	String surname;
	String specialty;
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
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getMobileId() {
		return mobileId;
	}
	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}
	public Doctor(String name, String surname, String specialty) {
		super();
		this.name = name;
		this.surname = surname;
		this.specialty = specialty;
	}
	
	
	
}
