package poc.async.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import poc.async.client.infrastructure.JmsTemplateApiClient;
import poc.async.server.api.model.impl.AntragImpl;

import javax.jms.JMSException;
import java.util.UUID;

@Component
public class Sender {

    private static final Logger log = LoggerFactory.getLogger(Sender.class);
    private final JmsTemplateApiClient client;

    public Sender(JmsTemplateApiClient client) {
        this.client = client;
    }


    @Scheduled(fixedDelay = 1000L)
    public void sendContinuously() throws JMSException {
        log.info("Sending");
        var res = client.createAntrag(new AntragImpl(UUID.randomUUID()));
        log.info("Got it {}", res);
    }
}