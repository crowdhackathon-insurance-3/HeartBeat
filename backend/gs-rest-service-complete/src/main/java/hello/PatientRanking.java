package hello;

public class PatientRanking {

	String DocId;
	String patientId;
	String ranking;
	String message;
	
	public String getDocId() {
		return DocId;
	}
	public void setDocId(String docId) {
		DocId = docId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PatientRanking(String docId, String patientId, String ranking, String message) {
		super();
		DocId = docId;
		this.patientId = patientId;
		this.ranking = ranking;
		this.message = message;
	}

	
	
}
