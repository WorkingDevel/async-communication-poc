package poc.async.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringApplicationServer {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplicationServer.class, args);
    }

}
