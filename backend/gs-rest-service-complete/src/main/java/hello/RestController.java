package hello;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/")
public class RestController {

    @GetMapping(value = "sendMessageToTopic", produces = "application/json")
    ResponseEntity ResponsendMessagesToTopic(){


        init();

        // The topic name can be optionally prefixed with "/topics/".
        String topic = "testTopic";

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("test", "20")
                .putData("time", "2:45")
                .setTopic(topic)
                .build();

        // Send a message to the devices subscribed to the provided topic.
        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);


        return new ResponseEntity(HttpStatus.OK);
    }

    private void init() {
        if(Util.isInit)
            return;
        else{
            FileInputStream serviceAccount = null;
            try {
                serviceAccount = new FileInputStream("C:\\hackathon\\insurance3\\HeartBeat\\backend\\hackathon-97594-firebase-adminsdk-t0wx2-45cfe02efd.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            FirebaseOptions options = null;
            try {
                options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://hackathon-97594.firebaseio.com")
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FirebaseApp.initializeApp(options);

            Util.isInit = true;
        }
    }
}
