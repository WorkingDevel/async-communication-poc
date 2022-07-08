package poc.async.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringApplicationClient {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplicationClient.class, args);
    }

}
