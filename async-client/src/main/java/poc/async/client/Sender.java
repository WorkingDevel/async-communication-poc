package poc.async.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import poc.async.server.api.JmsConfigurations;

import java.time.OffsetDateTime;

@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(fixedDelay = 1000L)
    public void sendContinously() {
        LOGGER.info("Sending");
        jmsTemplate.convertAndSend(JmsConfigurations.QUEUE_ANTRAG_REQUEST, "Hello World" + OffsetDateTime.now());
    }

    public void send(String message) {
        LOGGER.info("sending message='{}'", message);
        jmsTemplate.convertAndSend("helloworld.q", message);
    }
}