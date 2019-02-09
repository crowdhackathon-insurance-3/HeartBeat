package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/doctorgeolocation")
    public Ackresponse doctorgeolocation(@RequestParam(value="id", defaultValue="1") String id,
    		@RequestParam(value="latitude" ) String latitude,
    		@RequestParam(value="longtitude") String longtitude	) {
    	
    	System.out.println("id= " + id );
    	
        return new Ackresponse("thankyou");
    }
    
    @RequestMapping("/patientgeolocation")
    public Ackresponse patientgeolocation(@RequestParam(value="id", defaultValue="1") String id,
    		@RequestParam(value="latitude" ) String latitude,
    		@RequestParam(value="longtitude") String longtitude,
    		@RequestParam(value="problem") String problem) {
    	
    	System.out.println("id= " + id );
    	
        return new Ackresponse("thankyou");
    }
    
    
}
