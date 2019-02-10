package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import java.io.File;
import java.io.FileNotFoundException; 

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    List<DoctorPosition> doctorPositionList = new ArrayList<DoctorPosition>();
    List<PatientAlert> patientAlertList = new ArrayList<PatientAlert>();
    List<Doctor> doctorList = new ArrayList<Doctor>();
    List<Patient> patientList = new ArrayList<Patient>();
    String ibmspeechusername = "heartbeatcrowdhackathon";
	String ibmspeechuserpassword = "insuretech";
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/doctorgeolocation")
    public Ackresponse doctorgeolocation(@RequestParam(value="id", defaultValue="1") String id,
    		@RequestParam(value="latitude" ) String latitude,
    		@RequestParam(value="longtitude") String longtitude	) {
    	doctorPositionList.add(new DoctorPosition(id, longtitude, latitude));
    	System.out.println("id= " + id );
    	
        return new Ackresponse("thankyou");
    }
    
    @RequestMapping("/patientgeolocation")
    public Ackresponse patientgeolocation(@RequestParam(value="id") String id,
    		@RequestParam(value="latitude" ) String latitude,
    		@RequestParam(value="longtitude") String longtitude,
    		@RequestParam(value="problem") String problem) {
    	patientAlertList.add(new PatientAlert(id, latitude, longtitude, problem));
    	
    	
    	
    	System.out.println("id= " + id );
    	
        return new Ackresponse("thankyou");
    }
    
    private List<String> findDoctorsAround(PatientAlert patientAlert) {
    	List<String> listOfdoctorsIds = new ArrayList<String>();
    	for (DoctorPosition doctorPosition : doctorPositionList) {
    		double distance = Math.sqrt( Math.pow( (Double.parseDouble(patientAlert.latitude) - Double.parseDouble(doctorPosition.latitude)),2) + Math.pow((Double.parseDouble(patientAlert.latitude) - Double.parseDouble(doctorPosition.latitude)),2) );
			if (distance < 20 ) {
				listOfdoctorsIds.add(doctorPosition.id);
			}
		}
    	
		return listOfdoctorsIds;
    	
    }
    
    @RequestMapping("/doctorInfo")
    public Ackresponse doctorInfo(@RequestParam(value="name" ) String name,
    		@RequestParam(value="surname" ) String surname,
    		@RequestParam(value="specialty" ) String specialty,
    		@RequestParam(value="experience") String experience	) {
    	
    	doctorList.add(new Doctor(name, surname, specialty));
 
    	System.out.println("name= " + name );
    	
        return new Ackresponse("thankyou");
    }
    
    
    @RequestMapping("/patientInfo")
    public Ackresponse patientInfo(@RequestParam(value="name" ) String name,
    		@RequestParam(value="surname" ) String surname,
    		@RequestParam(value="yearOfBirth" ) String yearOfBirth,
    		@RequestParam(value="gender") String gender	,
    		@RequestParam(value="language") String language	,
    		@RequestParam(value="weight") String weight) {
    	
    	
    	patientList.add(new Patient(name, surname, yearOfBirth, gender, language));
    	System.out.println("name= " + name );
    	
        return new Ackresponse("thankyou");
    }
    
    public String speechtotext() throws FileNotFoundException {
    SpeechToText service = new SpeechToText();

	service.setUsernameAndPassword(ibmspeechusername, ibmspeechuserpassword);

    File audio = new File("src/test/resources/sample1.wav");

    RecognizeOptions options = new RecognizeOptions.Builder()
      .audio(audio)
      .contentType(HttpMediaType.AUDIO_WAV)
      .build();

    SpeechRecognitionResults transcript = service.recognize(options).execute();
    System.out.println(transcript);
	return "heart";
    }
}
